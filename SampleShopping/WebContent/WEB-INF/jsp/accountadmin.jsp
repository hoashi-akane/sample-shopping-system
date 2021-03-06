<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@page import="java.util.*" %>
<%@page import="dto.*" %>
<%UserDto userDto= (UserDto)session.getAttribute("userDto");%>
<%String action=""; %>
<!DOCTYPE html>
<html lang="jp" dir="ltr">
	<head>
		<meta charset="utf-8">
		<title>SampleShopping</title>
				<%@include file="head.jsp" %>

	</head>

	<body>
				<%@include file="adminheader.jsp" %>


	<div class="mainView">
	<!--ページコンテンツ-->
	</div>
			<!--タイトル-->
	<div align="center">
		<a href="menuadmin" id="title3a"><h3 id="title3">SampleShopping</h3></a>
		<p id="title2">- Account -</p>
	</div>
<form action ="admin/updateadminuser" method ="post">
		<div align="center">
   <div class="asd col-xs-12 col-md-5 md-offset-1">
   <table class="table table-active">
   <tr>
	 <th  class="mr-4" style="width:30%;">お名前</th>
	 <td><c:out value="<%=userDto.getUserName()%>"/></td>
   </tr>
   <tr>
	 <th align="center">ログインID</th>
   <td><c:out value="<%=userDto.getLoginId()%>"/></td>
   </tr>
   <tr>
	 <th align="center">パスワード</th>
	 <td><c:out value="<%=userDto.getPassword()%>"/></td>
   </tr>
   <tr>
	 <th>郵便番号</th>
	 <td><c:out value="<%=userDto.getZipCode()%>"/></td>
   </tr>
      <tr>
	 <th>住所</th>
	 <td><c:out value="<%=userDto.getAddress()%>"/></td>
   </tr>
   <tr>
	 <th>郵便番号(サブ)</th>
	 <td><c:out value="<%=userDto.getZipCodeSub()%>"/></td>
   </tr>
   <tr>
	 <th>住所(サブ)</th>
	 <td><c:out value="<%=userDto.getAddressSub()%>"/></td>
   </tr>
    <tr>
   	 <th>電話番号</th>
   	 <td><c:out value="<%=userDto.getTel()%>"/></td>
    </tr>
	<tr>
	 <th>メールアドレス</th>
	 <td><c:out value="<%=userDto.getMailAddress()%>"/></td>
	</tr>
	 <tr>
	  <th>性別</th>
	  <td><c:out value="<%=userDto.getGender()%>"/></td>
	 </tr>
   </table>
</div>
	<a href="updateadminuser" class="mt-3 btn-border-bottom" id="accountbtn">変更</a>
	<a href="#" class="mt-3 btn-border-bottom" data-toggle="modal" data-target="#drawModal">退会</a><br>
	<a href="createadmin" class="btn btn-outline-dark mt-3">管理者アカウント作成</a>
</div>
<div>

</div>

	<!-- Modal -->
	<div class="modal fade" id="drawModal" tabindex="-1" role="dialog" aria-labelledby="drawModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="drawModalLabel">ユーザー退会</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        本当に退会しますか？
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">戻る</button>
	        <a href="drawadminuser" class="btn btn-outline-danger">退会</a>
	      </div>
	    </div>
	  </div>
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
