<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
<%@page import="dto.*" %>
    <%List<GoodsDto> goodsDtoList= (ArrayList<GoodsDto>)request.getAttribute("goodsDtoList");%>
    <%String action=""; %>
<!DOCTYPE html>
<html lang="jp" dir="ltr">
	<head>
		<meta charset="utf-8">
				<title>SampleShopping</title>
				<%@include file="head.jsp" %>
	</head>
	<script>


			function goCart(){
				document.getElementById('form').action = 'insertcart';
				document.getElementById('form').method = 'post';

			}
		</script>

		<body>
							<%@include file="userheader.jsp" %>


	<div class="mainView">
	<!--ページコンテンツ-->
	</div>
			<!--タイトル-->
			<div align="center">
					<a href="menu" id="title3a"><h3 id="title3">SampleShopping</h3></a>
					<p id="title2">- Goods -</p>
			</div>

<form id ='form' name = 'inputForm' action="">
<div class="container">
		<%for(GoodsDto goodsDto:goodsDtoList){ %>
		<input type="hidden" name="id" value="<%=goodsDto.getId() %>">
    <div class="row mt-4 mx-auto" style="width:600px">
        <div class="col-md-1"></div>
        <div class="p-0 col-md-4 shadow-sm" style="height:200px;">
            <img src='dispgoodsimage?imagePath=/output_imgfile/<%=goodsDto.getId() %>/first' alt=""  style="width:100%; height:100%;">
        </div>
        <div class="col-md-6 mt-3 shadow-sm">
            <h5 class="ml-4"><%=goodsDto.getGoodsName()%></h5>
            <div class="ml-5 text-secondary">価格：¥<%=goodsDto.getPrice()%></div>
            <div class="text-danger ml-5">在庫数：<%=goodsDto.getStock()%>個</div>
                        <div class="text-danger ml-5"><select name="cnt">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select></div>
                    <div class="form-group col-md-3 mb-0">
                    </div>
                    <a href="dispgoodsdetail?id=<%=goodsDto.getId() %>" id="detail" class="mb-2 float-right btn btn-primary">詳細</a>
                	<button type="submit" value ="cart" onclick="goCart();" id="cart"class="mb-2 mr-3 float-right btn btn-primary">カートへ追加</button>
		</div>
    </div>
  <%} %>
</div>
</form>

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
