<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String message = (String)request.getAttribute("message");
if(message == null){
	message = "";
}
%>
<html lang="jp" dir="ltr">
	<head>
		<meta charset="utf-8">
		<title>SampleShopping</title>
								<%@include file="head.jsp" %>

	</head>

							<%@include file="userheader.jsp" %>


	<div class="mainView">
	<!--ページコンテンツ-->
	</div>

		<!--タイトル-->
		<div align="center">
				<a href="menu" id="title3a"><h3 id="title3">SampleShopping</h3></a>
				<p id="title2">- Create -</p>
		</div>

		<form action ="createuser" method ="post">
			<div align="center">
		   <div class="asd">
				<p><%= message %>
		   <table id="gaiyou">
			   <tr>
				 <th align="center">お名前<font color="red"> *</font></th>
				 <td><input class="form-control" type="text" name="username" value="" maxlength="50" required></td>
			   </tr>
			   <tr>
				 <th align="center">ID<font color="red"> *</font></th>
			   <td><input class="form-control" type="text" name="login_id" value="" maxlength="11" required></td>
			   </tr>
			   <tr>
				 <th align="center">パスワード</th>
				 <td><input class="form-control" type="password" name="user_password" value="" maxlength="128" required></td>
			   </tr>
			   <tr>
				 <th>郵便番号</th>
				 <td><input class="form-control" type="text" name="zip_code" value="" maxlength="7" required></td>
			   </tr>
			   <tr>
				 <th>郵便番号2</th>
				 <td><input class="form-control" type="text" name="zip_code_sub" value="" maxlength="7" required></td>
			   </tr>
			   <tr>
				 <th>住所１</th>
				 <td><input class="form-control" type="text" name="address" value="" maxlength="100" required></td>
			   </tr>
			   <tr>
				 <th>住所２</th>
				 <td><input class="form-control" type="text" name="address_sub" value="" maxlength="100" required></td>
			   </tr>
			    <tr>
			   	 <th>電話番号</th>
			   	 <td><input class="form-control" type="text" name="tel" value="" maxlength="11" required></td>
			    </tr>
				<tr>
				 <th>メールアドレス</th>
				 <td><input class="form-control" type="email" name="mailaddress" value="" id="textbox" maxlength="40" required></td>
				</tr>
				 <tr>
				  <th>性別</th>
				  <td>
				  <input type="radio" name="gender" value="0">秘密
				  <input type="radio" name="gender" value="1">女性
			      <input type="radio" name="gender" value="2">男性
			      <input type="radio" name="gender" value="3">その他
				  </td>
				 </tr>
		   </table>
		</div>
			<a href="/SampleShopping/login" class="btn-border-bottom px-4 mr-4">戻る</a>
			<input type="submit" value="登録" class="btn btn-outline-dark px-4 ml-4">
		</div>
		</form>
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
