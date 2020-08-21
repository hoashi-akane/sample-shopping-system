package servlet.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stripe.exception.StripeException;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodListParams;

import dao.CartDao;
import dto.UserDto;
import other.StripeSecretKey;
import service.BuyHistoryService;
import service.PaymentService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PaymentService paymentService = new PaymentService();
	BuyHistoryService buyHistoryService = new BuyHistoryService();
	CartDao cartDao = new CartDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Stripe.apiKey = StripeSecretKey.secretKey;
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		int totalPrice = cartDao.getCartTotalPrice(userDto.getId());
		Optional<ArrayList<HashMap<String, String>>> cardInfoListOpt = paymentService.getCardInfo(userDto);
//		cardInfoListがnullでなければリクエストスコープに値をセット
		cardInfoListOpt.ifPresent(cardInfoList -> request.setAttribute("cardInfoList",cardInfoList));

		PaymentIntent paymentIntent = paymentService.paymentFasade(userDto);
		if(paymentIntent != null) {
//			サーバで確認するためにpaymentIntentIdをセッションに格納
			session.setAttribute("paymentIntentId", paymentIntent.getId());
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("clientSecret", paymentIntent.getClientSecret());
			request.getRequestDispatcher("/WEB-INF/jsp/payment.jsp").forward(request, response);
		}else {
			session.setAttribute("message", "商品をカートに追加してください");
			response.sendRedirect("/SampleShopping/dispcart");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		String paymentIntentId = (String)session.getAttribute("paymentIntentId");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/paymentstatus.jsp");
//		セッションの値チェック
		if(paymentIntentId == null) {
			request.setAttribute("message", "不正なアクセスを検知しました。");
			requestDispatcher.forward(request, response);
			return;
		}
		String status = paymentService.checkPayment(paymentIntentId);
//		webhookで実装するともう少し楽
		if(status == null  || !(status.equals("succeeded"))) {
			request.setAttribute("message", "購入に失敗しました。");
			requestDispatcher.forward(request, response);
			return;
		}
//		購入履歴登録ファサードとカート情報削除処理を行う
		if( buyHistoryService.insertBuyHistoryFasade(userDto) && cartDao.deleteCarts(userDto.getId())) {
			request.setAttribute("message", "購入が完了しました。");
		}else {
			request.setAttribute("message", "購入処理後にエラーが発生しました。");
		}
		requestDispatcher.forward(request, response);
	}
}
