<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.stripe.model.PaymentIntent" %>
<%@page import="dto.UserDto" %>
<% 
	String clientSecret = (String)request.getAttribute("clientSecret");
	ArrayList<HashMap<String, String>> cardInfoList = (ArrayList<HashMap<String, String>>)request.getAttribute("cardInfoList");
	if(cardInfoList == null){
		cardInfoList = new ArrayList<HashMap<String, String>>();
	}
	UserDto userDto =(UserDto)session.getAttribute("userDto");
	int totalPrice = (int)request.getAttribute("totalPrice");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SampleShopping</title>
	<%@include file="head.jsp" %>
	<script src="https://js.stripe.com/v3/"></script>
	
</head>
<body class="payment-font">
<%@include file="userheader.jsp" %>

<!--タイトル-->
	<div align="center">
			<h3 id="title3">SampleShopping</h3>
			<p id="title2">- Cart -</p>
	</div>
	<div class="col-md-5  mx-auto text-center payment-border">
		<div>
			<h5>氏名</h5>
			<p class="text-secondary"><c:out value="<%= userDto.getUserName() %>"/></p>
		</div>

		<div>
			<h5>住所</h5>
			<p class="text-secondary">〒<c:out value="<%= userDto.getZipCode() %>"/><br>
			<c:out value="<%= userDto.getAddress() %>"/></p>
		</div>
		<div>
			<h5>電話番号</h5>
			<p class="text-secondary"><c:out value="<%= userDto.getTel() %>"/></p>
		</div>
		<div>
			<h5>合計金額</h5>
			<p class="text-secondary"><c:out value="<%= totalPrice %>"/>円</p>
		</div>
		<div class="form-group">
			<label class="w-50" for="exampleSelect1exampleFormControlSelect1" id="cardLabel"><button class="btn btn-light w-100" id="checkBtn">登録されたカードを利用</button></label>
			<form action="/SampleShopping/paymentcharge" method="post" id="cardForm">
			<select class="mt-1 mb-3 form-control d-none" name="cardId" id="cardSelect">
				<option id="defaultCard" value="noSelected">カードを選択</option>
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
			<button id="inputCardBtn" type="submit" class="btn btn-primary d-none w-50" form="cardForm">Pay</button>
			<button id="submit" class="btn btn-primary w-50">Pay</button>
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
			alert('カード情報を正しく入力してください。');
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
	/* ボタン入れ替え */
	stripeBtn.classList.toggle("d-none");
	cardInputBtn.classList.toggle("d-none");
	cardSelect.classList.toggle("d-none");
	cardElement.classList.toggle("d-none");
	/* ボタンの値変更とカードが選択されているか判別 */
	if(button.innerHTML == "登録されたカードを利用"){
		button.innerHTML = "カードの情報を入力";
		if(document.getElementById("cardSelect").value == "noSelected"){
			document.getElementById("inputCardBtn").disabled = true;
		}
	}else{
		button.innerHTML = "登録されたカードを利用";
	}
}
/* カードが選択、変更された場合選ばれたか都度チェックさせる */
document.getElementById("cardSelect").onchange = function(){
	var cardId = this;
	if(cardId.value == "noSelected"){
		document.getElementById("inputCardBtn").disabled = true;
	}else{
		document.getElementById("inputCardBtn").disabled = false;
	}
}

<!--メニュのjQuery-->
<!--CDNでjQuery読み込む-->
	//変数定義
var navigationOpenFlag = false;
var navButtonFlag = true;
var focusFlag = false;

//ハンバーガーメニュー
$(function(){
  $(document).on('click','.el_humburger',function(){
    if(navButtonFlag){
      spNavInOut.switch();
      //一時的にボタンを押せなくする
      setTimeout(function(){
        navButtonFlag = true;
      },200);
      navButtonFlag = false;
    }
  });
  $(document).on('click touchend', function(event) {
    if (!$(event.target).closest('.bl_header,.el_humburger').length && $('body').hasClass('js_humburgerOpen') && focusFlag) {
      focusFlag = false;
      //scrollBlocker(false);
      spNavInOut.switch();
    }
  });
});

//ナビ開く処理
function spNavIn(){
  $('body').removeClass('js_humburgerClose');
  $('body').addClass('js_humburgerOpen');
  setTimeout(function(){
    focusFlag = true;
  },200);
  setTimeout(function(){
    navigationOpenFlag = true;
  },200);
}

//ナビ閉じる処理
function spNavOut(){
  $('body').removeClass('js_humburgerOpen');
  $('body').addClass('js_humburgerClose');
  setTimeout(function(){
    $(".uq_spNavi").removeClass("js_appear");
    focusFlag = false;
  },200);
  navigationOpenFlag = false;
}

//ナビ開閉コントロール
var spNavInOut = {
  switch:function(){
    if($('body.spNavFreez').length){
      return false;
    }
    if($('body').hasClass('js_humburgerOpen')){
     spNavOut();
    } else {
     spNavIn();
    }
  }
};

</script>
</html>