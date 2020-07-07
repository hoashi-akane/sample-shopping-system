<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.stripe.model.PaymentIntent" %>
<!DOCTYPE html>
<html>
<head>
<% 
String client_secret = (String)request.getAttribute("client_secret");
%>
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
<script src="https://js.stripe.com/v3/"></script>
<style>
	<%@ include file = "../../css/style.css"%>
</style>
</head>
<body>
<div id="card-element">
  <!-- Elements will create input elements here -->
  
</div>

<!-- We'll put the error messages in this element -->
<div id="card-errors" role="alert"></div>

<button id="submit">Pay</button>
</body>
<script>
var stripe = Stripe('pk_test_zGzWn6p0dAzslSHD4XsI9om500tt01r2Tw');
var elements = stripe.elements();
var style = {
	  base: {
	    color: "#32325d",
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
	stripe.confirmCardPayment("<%= client_secret %>", {
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
</script>
</html>