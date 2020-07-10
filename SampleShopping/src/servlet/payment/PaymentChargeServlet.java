package servlet.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import dao.CartDao;
import dto.UserDto;
import service.BuyHistoryService;
import service.PaymentService;

/**
 * Servlet implementation class PaymentChargeServlet
 * 既に登録されているカードを選択した場合に遷移するサーブレット
 */
@WebServlet("/paymentcharge")
public class PaymentChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PaymentService paymentService = new PaymentService();
	BuyHistoryService buyHistoryService = new BuyHistoryService();
	CartDao cartDao = new CartDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/paymentstatus.jsp");
		HttpSession session = request.getSession();
		String cardId = request.getParameter("cardId");
		UserDto userDto = (UserDto)session.getAttribute("userDto");
//		セッションから値の受取と破棄
		String paymentIntentId = (String)session.getAttribute("paymentIntentId");
		session.removeAttribute("paymentIntentId");

//		セッションに入っていた値と入力された値チェック
		if(cardId == null) {
			request.setAttribute("message", "不正なカードが選択されています。");
			requestDispatcher.forward(request, response);
			return;
		}
		if(paymentIntentId == null) {
			request.setAttribute("message", "不正なアクセスを検知しました。");
			requestDispatcher.forward(request, response);
			return;
		}
//		PaymentIntent取得、カード情報セット
		PaymentIntent paymentIntent = paymentService.getPaymentIntent(paymentIntentId);
		Map<String, Object> params = new HashMap<>();
		params.put("payment_method", cardId);
		try {
			paymentIntent = paymentIntent.confirm(params);
		} catch (StripeException e) {
//			cardIdが書き換えられていた場合
			request.setAttribute("message", "決済に失敗しました。<br>使用できるカードであるか確認してください。");
			requestDispatcher.forward(request, response);
			return;
		}
		
		String status = paymentIntent.getStatus();
		if(status == null || !(status.equals("succeeded"))) {
			request.setAttribute("message", "購入に失敗しました。");
			requestDispatcher.forward(request, response);
			return;
		}
		
		if(buyHistoryService.insertBuyHistoryFasade(userDto) && cartDao.deleteCarts(userDto.getId())) {
			request.setAttribute("message", "購入が完了しました。");
		}else {
			request.setAttribute("message", "購入処理後にエラーが発生しました。");
		}
		requestDispatcher.forward(request, response);
	}
}
