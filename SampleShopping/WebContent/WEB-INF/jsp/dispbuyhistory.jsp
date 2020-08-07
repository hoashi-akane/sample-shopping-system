<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.*" %>
<%@page import="dto.*" %>

    <%List<BuyHistoryDto> buyHistoryList= (ArrayList<BuyHistoryDto>)request.getAttribute("buyHistoryList");
    int i = 0;
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
				<p id="title2">- BuyHistory -</p>
		</div>

	<%for(BuyHistoryDto buyHistoryDto: buyHistoryList){ %>
	<div class="container">
	    <div class="row mt-4">
	        <div class="col-md-7 mx-auto mt-3 shadow-sm">
	            <h5 class="ml-4"></h5>
	<!--   <div class="ml-5 text-secondary"></div> -->
				<input type="hidden" name="historyNumber" value="<%=i%>">
	           <div class="text-danger ml-5">購入日：<c:out value="<%=buyHistoryDto.getBuyDate()%>"/></div>
	           <div class="text-secondary mt-2 ml-5">購入金額：<c:out value="<%=buyHistoryDto.getTotalPrice() %>"/></div>

           	<button type="button" class="mb-2 float-right btn btn-outline-dark" data-toggle="collapse" data-target="#collapse<%=i%>" aria-expanded="false" aria-controls="collapse<%=i%>">詳細表示</button>
	        </div>
	    </div>
	</div>
	<div class="container">
	<div class="collapse col-md-7 mx-auto" id="collapse<%=i%>">
	    <table class="table mt-0" id="cartTable">
		  <thead>
				<tr>
			      <th scope="col">#</th>
			      <th scope="col">商品名</th>
			      <th scope="col">単価</th>
			      <th scope="col">購入数</th>
			       <th scope="col">小計</th>
				</tr>
			</thead>
			<tbody>
				<% for(int j=0; j < buyHistoryDto.getGoodsDtoList().size(); j++){ %>
				<tr>
					<td><%= j+1 %></td>
					<td><c:out value="<%=buyHistoryDto.getGoodsDtoList().get(j).getGoodsName() %>"/></td>
				<td><c:out value="<%=buyHistoryDto.getDetailDtoList().get(j).getUnitPrice() %>"/></td>
					<td><c:out value="<%=buyHistoryDto.getDetailDtoList().get(j).getVolume() %>"/></td>
					<td><%=buyHistoryDto.getDetailDtoList().get(j).getUnitPrice() * buyHistoryDto.getDetailDtoList().get(j).getVolume() %></td>
				</tr>
				<% } %>
			</tbody>
		 </table>
	</div>	
	</div>
	<% i++; %>
	<%} %>
	
	<div align="center"><a href="menu" class="btn-border-bottom">戻る</a></div>

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
