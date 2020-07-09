<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.stripe.model.PaymentIntent" %>
<%@page import="dto.UserDto" %>
<% 
	String clientSecret = (String)request.getAttribute("clientSecret");
	ArrayList<HashMap<String, String>> cardInfoList = (ArrayList<HashMap<String, String>>)request.getAttribute("cardInfoList");
	UserDto userDto =(UserDto)session.getAttribute("userDto");
	int totalPrice = (int)request.getAttribute("totalPrice");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SampleShopping</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	
	<link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@300&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Satisfy&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@200&display=swap" rel="stylesheet">
	<script
	src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<script src="https://js.stripe.com/v3/"></script>
	<style>
		<%@ include file = "../../css/style.css"%>
	</style>
</head>
<body class="payment-font">
<!--タイトル-->
	<div align="center">
			<a href="top.html" id="title3a"><h3 id="title3">SampleShopping</h3></a>
			<p id="title2">- Cart -</p>
	</div>
	<div class="col-md-5  mx-auto text-center payment-border">
		<div>
			<h5>氏名</h5>
			<p class="text-secondary"><%= userDto.getUserName() %></p>
		</div>

		<div>
			<h5>住所</h5>
			<p class="text-secondary">〒<%= userDto.getZipCode() %><br>
			<%= userDto.getAddress() %></p>
		</div>
		<div>
			<h5>電話番号</h5>
			<p class="text-secondary"><%= userDto.getTel() %></p>
		</div>
		<div>
			<h5>合計金額</h5>
			<p class="text-secondary"><%= totalPrice %>円</p>
		</div>
		<div class="form-group">
			<label class="w-50" for="exampleSelect1exampleFormControlSelect1" id="cardLabel"><button class="btn w-100" id="checkBtn">登録されたカードを利用する方</button></label>
			<form action="/SampleShopping/paymentcharge" method="post" id="cardForm">
			<select class="mt-1 mb-3 form-control d-none" name="cardId" id="cardSelect">
				<option>カードを選択</option>
					<% for(HashMap<String,String> cardInfo : cardInfoList){ %>
					<option value="<%= cardInfo.get("id") %>">
					発行元：<%= cardInfo.get("cardBrand") %>　下４桁：<%= cardInfo.get("numberLastFour") %>
					</option>
					<% } %>
			</select>
			</form>
			<div id="card-element" class="mt-1 mb-3">
	  <!-- Elements will create input elements here -->		  
			</div>
			
			<!-- We'll put the error messages in this element -->
			<div id="card-errors" role="alert"></div>
			<button id="inputCardBtn" type="submit" class="btn d-none w-50" form="cardForm">Pay</button>
			<button id="submit" class="btn w-50">Pay</button>
		</div>
	</div>

		<!--フッター-->
<footer>
<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
</footer>
</body>
<script>
var stripe = Stripe('pk_test_zGzWn6p0dAzslSHD4XsI9om500tt01r2Tw');
var elements = stripe.elements();
var style = {
	  base: {
	    color: "#32325d",
	    fontSize: '18px',
	  }
};
var card = elements.create("card", { style: style });
card.mount("#card-element");
//Set up Stripe.js and Elements to use in checkout form

card.on('change', function(event) {
 var displayError = document.getElementById('card-errors');
 if (event.error) {
   displayError.textContent = event.error.message;
 } else {
   displayError.textContent = '';
 }
});
	
	
document.getElementById("submit").onclick= function() {
	stripe.confirmCardPayment("<%= clientSecret %>", {
		  payment_method: {
		    card: card
		  },
		  setup_future_usage: 'off_session'
		}).then(function(result) {
		  if (result.error) {
		    // Show error to your customer
			alert('エラー');
		    console.log(result.error.message);
		  } else {
		    if (result.paymentIntent.status === 'succeeded') {
		    	var form = document.createElement("form");
		    	document.body.appendChild( form );
		    	 form.setAttribute("action", "/SampleShopping/payment");
		    	 form.setAttribute("method", "post");
		    	 form.submit();
	     // Show a success message to your customer
	     // There's a risk of the customer closing the window before callback execution
	     // Set up a webhook or plugin to listen for the payment_intent.succeeded event
	     // to save the card to a Customer
	
	     // The PaymentMethod ID can be found on result.paymentIntent.payment_method
	   		}
		}
	}
)};

document.getElementById("cardLabel").onclick= function(){
	var cardSelect = document.getElementById("cardSelect");
	var cardElement = document.getElementById("card-element");
	var button = document.getElementById("checkBtn");
	var stripeBtn = document.getElementById("submit");
	var cardInputBtn = document.getElementById("inputCardBtn");
	stripeBtn.classList.toggle("d-none");
	cardInputBtn.classList.toggle("d-none");
	cardSelect.classList.toggle("d-none");
	cardElement.classList.toggle("d-none");
	if(button.innerHTML == "登録されたカードを利用する方"){
		button.innerHTML = "カードの情報を入力する方";
	}else{
		button.innerHTML = "登録されたカードを利用する方";
	}
}

</script>
</html>