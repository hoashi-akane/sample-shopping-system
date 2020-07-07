package servlet.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
import service.PaymentService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		PaymentService paymentService = new PaymentService();
		Optional<ArrayList<HashMap<String, String>>> cardInfoListOpt = paymentService.getCardInfo(userDto);
		cardInfoListOpt.ifPresent(cardInfoList -> request.setAttribute("card_info_list",cardInfoList));
		
		String clientSecret = paymentService.paymentFasade(userDto);
		if(clientSecret != null) {
			request.setAttribute("client_secret", clientSecret);
			request.getRequestDispatcher("WEB-INF/jsp/payment.jsp").forward(request, response);
		}else {
			response.sendRedirect("/test/cart");
		}
//		クライアント側で必要なclient_secretをき出し渡す。
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/completepayment.jsp").forward(request,response);
	}

}
