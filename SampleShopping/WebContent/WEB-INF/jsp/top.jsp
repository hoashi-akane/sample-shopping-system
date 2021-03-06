<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import = "dto.*" %>
<% String message = (String)request.getAttribute("message"); %>
<%
if(message == null){
	message="";
}
%>
<!DOCTYPE html>
<html lang=jp dir="ltr">
<head>
	<meta charset="utf-8">
	<title>SampleShopping</title>
			<%@include file="head.jsp" %>
	</head>
	<% UserDto userDto = (UserDto)session.getAttribute("userDto"); %>
	<% String name = userDto.getUserName(); %>
<body>
	<%@include file="userheader.jsp" %>

<div class="mainView">
<!--ページコンテンツ-->
</div>

<h3>ようこそ<c:out value="<%=name%>"/>さん</h3>
		<!--タイトル-->
		<div align="center">
				<h1 id="title1">SampleShopping</h1>
				<p id="title2">if you can dreamit, you can do it.</p>
		</div>
		<div class="mx-auto text-center text-danger"><%=message %></div>

<!--画像のスライド-->
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="2500" data-pause="hover">
  <div class="carousel-inner" align="center">
    <div class="carousel-item active">
<img src="./img/wneY5p08HEnlKBU1596511651_1596511708.jpg" alt="" id="headerimg1" class = "img-fluid" style="width:60%;height:auto;">
    </div>
    <div class="carousel-item" align="center">
<img src="./img/shikee-4YcltBUgMFI-unsplash.jpg" alt="" id="headerimg1" class = "img-fluid" style="width:60%;height:auto;">
    </div>
    <div class="carousel-item" align="center">
<img src="./img/j1Ai9aBQhSI0jt11596590270_1596590313.jpg" alt="" id="headerimg1" class = "img-fluid" style="width:60%;height:auto;">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
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
