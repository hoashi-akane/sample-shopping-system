<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>

<%@page import="dto.*" %>
<% List<CartDto> cartDtoList = (ArrayList<CartDto>)request.getAttribute("cartDtoList");
String message = (String)request.getAttribute("message");
int i =1;
int totalPrice = 0;
%>
<!DOCTYPE html>
<html lang="jp" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>SampleShopping</title>
<%@include file="head.jsp" %>
</head>
<body>
	<%@include file="userheader.jsp" %>


	<div class="mainView">
	<!--ページコンテンツ-->
	</div>
			<!--タイトル-->
			<div align="center">
					<a href="menu" id="title3a"><h3 id="title3">SampleShopping</h3></a>
					<p id="title2">- Cart -</p>
			</div>

	<div align="center">
		<div class="text-danger">
			<%if(message != null){ %>
			<%= message %>
			<%} %>
		</div>
		<table border="1" id="cartTable">
			<tr>
				<th id="cartName">NO.</th>
				<th id="cartGoodsName">商品名</th>
				<th id="cartName">商品コード</th>
				<th id="cartName">価格</th>
				<th id="cartName">数量</th>
				<th id="cartName">小計</th>
			</tr>
			<% for(CartDto cartDto:cartDtoList){ %>
			<form action="#" method="POST">
			<tr>
				<input type="hidden" name="cart_id" value="<%= cartDto.getId() %>"></input>
				<td id="cartTabletd"><%= i %></td>
				<td id="cartTabletd"><%= cartDto.getGoodsDto().getGoodsName() %></td>
				<td id="cartTabletd"><%= cartDto.getGoodsDto().getId() %></td>
				<td id="cartTabletd"><%= cartDto.getGoodsDto().getPrice() %></td>
				<td id="cartTabletd">
							<select name="volume">
								<option value="<%=cartDto.getVolume() %>" selected><%= cartDto.getVolume() %></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select></td>
				<td id="cartTabletd"><%= (cartDto.getGoodsDto().getPrice() * cartDto.getVolume()) %></td>
				<% totalPrice += cartDto.getGoodsDto().getPrice() * cartDto.getVolume(); %>
				<td>
				<button type="submit" class="btn" formaction="updatecart">変更</button>
				<button type="submit" class="btn" formaction="deletecart">削除</button>
				</td>
			</tr>
			</form>
			<%} %>
			<tr>
				<td colspan="6" id="cartTotal"><div align="center">合計 <%= totalPrice %>円</div></td>
			</tr>
		</table>
		<a href="/SampleShopping/payment" class="btn-border-bottom">決済画面へ</a>
	</div>

			<!--フッター-->
	<footer>
	<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
	</footer>

	<!--メニュのjQuery-->
	<!--CDNでjQuery読み込む-->
	<script type="text/javascript">
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



			<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		</body>
</html>
