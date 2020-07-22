<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp" dir="ltr">
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
				<link rel="stylesheet" href="../css/style.css">
	</head>

		<body>
				<!--メニュ-->
	 <div class="el_humburger"><!--ハンバーガーボタン-->
	    <div class="el_humburger_wrapper">
	      <span class="el_humburger_bar top"></span>
	      <span class="el_humburger_bar middle"></span>
	      <span class="el_humburger_bar bottom"></span>
	    </div>
	  </div>

			<header class="navi">
		     <div class="navi_inner">
		       <div class="navi_item"><a href="topadmin.html">Home</a></div>
		       <div class="navi_item"><a href="goods.html">Goods</a></div>
			   <div class="navi_item"><a href="accountadmin.html">Account</a></div>
		     </div>
		 	</header>

	<div class="mainView">
	<!--ページコンテンツ-->
	</div>

		<!--タイトル-->
		<div align="center">
				<a href="top.html" id="title3a"><h3 id="title3">SampleShopping</h3></a>
				<p id="title2">- Correction -</p>
		</div>
	<form action="updateuser" method="post">
			<div align="center">
		   <div class="asd">
			   <table id="gaiyou">
		   <tr>
			 <th align="center">お名前<p><font color="red"> *</font></p></th>
			 <td><input type="text" name="username" value=""></td>
		   </tr>
		   <tr>
			 <th align="center">ID<p><font color="red"> *</font></p></th>
		   <td><input type="text" name="login_id" value=""></td>
		   </tr>
		   <tr>
			 <th align="center">パスワード</th>
			 <td><input type="password" name="user_password" value=""></td>
		   </tr>
		   <tr>
			 <th>郵便番号1</th>
			 <td><input type="text" name="zip_code" value=""></td>
		   </tr>
		   <tr>
			 <th>郵便番号2</th>
			 <td><input type="text" name="zip_code_sub" value=""></td>
		   </tr>
		   <tr>
			 <th>住所１</th>
			 <td><input type="text" name="address" value=""></td>
		   </tr>
		   <tr>
			 <th>住所２</th>
			 <td><input type="text" name="address_sub" value=""></td>
		   </tr>
		    <tr>
		   	 <th>電話番号</th>
		   	 <td><input type="text" name="tel" value=""></td>
		    </tr>
			<tr>
			 <th>メールアドレス</th>
			 <td><input type="email" name="mailaddress" value="" id="textbox"></td>
			</tr>
			 <tr>
			  <th>性別</th>
			  <td><input type="radio" name="gender" value="1">女性
		        <input type="radio" name="gender" value="2">男性
			  </td>
			 </tr>
		   </table>
	</div>
		<input type="submit" value="変更" class="btn-border-bottom">
	</form>

		<form action="dispadminuserinfo" method="post">
			<input type="submit" value="戻る" class="btn-border-bottom" id="accountbtn">
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
