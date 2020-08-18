<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<%@include file="head.jsp" %>
<%
String message = (String)request.getAttribute("message");
%>
<meta charset="UTF-8">
<title>SampleShopping</title>
</head>
<body>
	<%@include file="userheader.jsp" %>
	<div align="center" class="pb-2">
				<a href="menu" id="title3a"><h1 id="title1">SampleShopping</h1></a>
				<p id="title2">if you can dreamit, you can do it.</p>
		</div>
<div class="container">
	<div class="row mt-5">
		<div class="col-md-12">
			<div class="mx-auto text-center mt-5" >
				<%= message %>
			</div>
		</div>
	</div>
</div>


<div class="mx-auto text-center mt-5" >
<a href="menu" class="btn-border-bottom mr-3">戻る</a>
<a href="dispbuyhistory" class="btn-border-bottom ml-3">購入履歴へ</a>
</div>




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
</body>
</html>