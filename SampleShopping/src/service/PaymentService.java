package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodListParams;

import dao.CartDao;
import dao.UserDao;
import dto.UserDto;

public class PaymentService {

//	戻り値 clientSecret
	public String paymentFasade(UserDto userDto){
		CartDao cartDao = new CartDao();
//		userの合計金額取得・カスタマーid取得
		int totalPrice = cartDao.getCartTotalPrice(userDto.getId());
		String customerId = checkCustomer(userDto);
		if(customerId == null) {
			customerId = createCustomer(userDto);
		};
		return createPaymentIntent(customerId, totalPrice);
	}
	
//	顧客のカードの下４桁とそのpaymentIdを取得
	public Optional<ArrayList<HashMap<String, String>>> getCardInfo(UserDto userDto){
//		顧客がカードで支払った時のカード情報取得
		String customerId = checkCustomer(userDto);
		if(customerId == null) {return Optional.empty();}
		PaymentMethodCollection paymentMethods = getPaymentMethodList(customerId);
		if(paymentMethods == null){return Optional.empty();}
		ArrayList<HashMap<String, String>> cardInfoList = null;
		try {
			cardInfoList = new ArrayList<HashMap<String, String>>();
			for(int i=0; i<paymentMethods.getData().size(); i++) {
				HashMap<String, String> cardInfo = new HashMap<String, String>();
//				paymentMethodのid
				cardInfo.put("id", paymentMethods.getData().get(i).getId());
//				cardの下４桁
				cardInfo.put("numberLastFour", paymentMethods.getData().get(i).getCard().getLast4());
				cardInfoList.add(cardInfo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(cardInfoList);
	}

	//	customerを生成
	private String createCustomer(UserDto userDto) {
		String customerId = null;
		UserDao userDao = new UserDao();
		CustomerCreateParams params =
				  CustomerCreateParams.builder()
				  	.setName(userDto.getUserName())
				  	.setPhone(userDto.getTel())
				    .build();
		try {
			Customer customer = Customer.create(params);
			userDao.updatePaymentCustomerId(userDto.getId(), customer.getId());
			customerId = customer.getId();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customerId;
	}
		
//	CustomerのidからpaymentMethodの一覧を取得するメソッド
	private PaymentMethodCollection getPaymentMethodList(String customerId) {
//		(条件指定：顧客、カード限定)
		PaymentMethodCollection paymentMethods = null;
		PaymentMethodListParams params =
				PaymentMethodListParams.builder()
				.setCustomer(customerId)
				.setType(PaymentMethodListParams.Type.CARD)
				.build();
		try {
			paymentMethods = PaymentMethod.list(params);
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return paymentMethods;
	}
	
//	dbにユーザーの決済用顧客idがあるか確認するメソッド　登録されてなければ戻り値にnullが入る
	private String checkCustomer(UserDto userDto) {
		UserDao userDao = new UserDao();
		String customerId = userDao.getPaymentCustomerId(userDto.getId());
		return customerId;
	}
	

//　合計金額を設定したpaymentIntntを生成、クライアントに渡すclientSecretを返すメソッド
	private String createPaymentIntent(String customerId, int totalPrice) {
		String clientSecret = null;
		PaymentIntentCreateParams params =
				  PaymentIntentCreateParams.builder()
				    .setCurrency("jpy")
				    .setAmount((long)totalPrice)
				    .setCustomer(customerId)
				    .build();
		try {
			PaymentIntent paymentIntent = PaymentIntent.create(params);
			clientSecret = paymentIntent.getClientSecret();
		}catch(StripeException e) {
			e.printStackTrace();
		}
		return clientSecret;
	}
}