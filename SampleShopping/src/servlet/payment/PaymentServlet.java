package servlet.payment;

import java.io.IOException;
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
		CartDao cartDao = new CartDao();
		int totalPrice = cartDao.getCartTotalPrice(userDto.getId());
		request.setAttribute("total_price", totalPrice);
		
		try {
		CustomerCreateParams params =
				  CustomerCreateParams.builder()
				    .build();
		
		PaymentMethodListParams paramsae = 
				PaymentMethodListParams.builder()
				.setCustomer("cus_Hb7t3gsQLszGaR")
				.setType(PaymentMethodListParams.Type.CARD)
				.build();
		PaymentMethodCollection paymentMethods = PaymentMethod.list(paramsae);
		System.out.println(paymentMethods.getData().size());
//		Customer customer = Customer.create(params);
		PaymentIntentCreateParams paramsa =
				  PaymentIntentCreateParams.builder()
				    .setCurrency("jpy")
				    .setAmount((long)totalPrice)
				    .build();

		
		PaymentIntent paymentIntent = PaymentIntent.create(paramsa);
		String clientSecret = paymentIntent.getClientSecret();
//		クライアント側で必要なclient_secretを抜き出し渡す。
		request.setAttribute("client_secret", clientSecret);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("111");
			System.out.println("エラー");
		}

		
		request.getRequestDispatcher("WEB-INF/jsp/payment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/completepayment.jsp").forward(request,response);
	}

}
