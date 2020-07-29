<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="el_humburger">
	    <div class="el_humburger_wrapper">
	      <span class="el_humburger_bar top"></span>
	      <span class="el_humburger_bar middle"></span>
	      <span class="el_humburger_bar bottom"></span>
	    </div>
	  </div>

	<header class="navi">
     <div class="navi_inner">
       <div class="navi_item"><a href="menu">Home</a></div>
       <div class="navi_item"><a href="dispgoodslist">Goods</a></div>
	   <div class="navi_item"><a href="dispcart">Cart</a></div>
	   	   <div class="navi_item"><a href="dispbuyhistory">BuyHistory</a></div>
	   <div class="navi_item"><a href="#" data-toggle="modal" data-target="#accountModal">Account</a></div>
     </div>
 	</header>

<div class="modal fade" id="accountModal" tabindex="-1" role="dialog" aria-labelledby="accountModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="accountModalLabel">会員情報閲覧</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="checkpassword" method="POST">
      <div class="modal-body">
        会員情報を閲覧するにはパスワードを入力してください。
        <input type="password" name="input_password">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
        <button type="submit" class="btn btn-primary">閲覧</button>
      </div>
      </form>
    </div>
  </div>
</div>
