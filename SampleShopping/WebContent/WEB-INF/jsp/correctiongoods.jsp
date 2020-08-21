<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.*" %>
<%@page import="dto.*" %>
<%
GoodsDto goodsDto= (GoodsDto)request.getAttribute("goodsDto");
String action="";
ArrayList<BrandDto> brandDtoList = (ArrayList<BrandDto>)request.getAttribute("brandDtoList");
ArrayList<CategoryDto> categoryDtoList = (ArrayList<CategoryDto>)request.getAttribute("categoryDtoList");
%>
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
		<a href="top.html" id="title3a"><h3 id="title3">SampleShopping</h3></a>
		<p id="title2">- CreateGoods -</p>
	</div>
	<form class="mt-5" action ="updategoods" method="post">
		<input class="form-control" type="hidden" name="stock" value="0" maxlength="7" required>		   
		<div align="center">
	   		<div class="asd">
		   		<table id="gaiyou">
					<tr>
					 <th>商品名</th>
					 <td><input class="form-control" type="text" name="goods_name" value="<%=goodsDto.getGoodsName()%>" maxlength="100" required></td>
				   </tr>
				   <tr>
					 <th align="center">値段</th>
				   <td><input class="form-control" type="number" name="price" value="<%=goodsDto.getPrice()%>" maxlength="8" required></td>
				   </tr>
				   <tr>
					 <th>説明文</th>
					 <td><textarea class="form-control" name="description" cols="50" rows="7" id="textbox" maxlength="300" required><%=goodsDto.getDescription()%></textarea></td>
				   </tr>
				   <tr>
					 <th>カテゴリー</th>
					 <td>
					 	<select class="form-control" name="category_id">
					 	<% for(CategoryDto categoryDto: categoryDtoList){ %>
					 	<option value="<%=categoryDto.getId()%>"><c:out value="<%=categoryDto.getName() %>"/></option>
					 	<%} %>
					 	</select>
					 </td>
				   </tr>
				    <tr>
				   	 <th>ブランド</th>
				   	 <td>
				   	 	<select class="form-control" name="brand_id">
						<% for(BrandDto brandDto: brandDtoList){ %>
					 	<option value="<%=brandDto.getId()%>"><c:out value="<%=brandDto.getName() %>"></c:out></option>
					 	<%} %>
					 	</select>
					</td>
				    </tr>
				    <tr>
				     	<th>セール</th>
				   	 	<td><input type="Checkbox" name="sale" value="">セール中</td>
				    </tr>
		   		</table>
		</div>
			<a href="dispgoodslistadmin" class="btn-border-bottom" id="accountbtn">戻る</a>
			<button type="submit" class="btn btn-outline-dark">登録</button>
		</div>
     </form>

	<!--フッター-->

	<footer>
	<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
	</footer>
	</body>

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
