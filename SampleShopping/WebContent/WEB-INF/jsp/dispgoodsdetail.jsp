<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="java.util.*" %>
      <%@page import="java.io.File" %>
<%@page import="dto.*" %>
    <%GoodsDto goodsDto = (GoodsDto)request.getAttribute("goodsDto");
    String action="";
    File[] fileList = (File[])request.getAttribute("fileList");
    int i = 0;
    UserDto userDto = (UserDto)session.getAttribute("userDto");
    %>
<!DOCTYPE html>
<html lang="jp" dir="ltr">
	<head>
		<meta charset="utf-8">
		<title>SampleShopping</title>
			<%@include file="head.jsp" %>
	</head>
		<body>
		<%String  transition="";%>
			<% if(userDto.isAdmin()){%>
				<%@include file="adminheader.jsp" %>
				<%transition = "dispgoodslistadmin";%>
			<%}else{ %>
			<%@include file="userheader.jsp" %>
			<%transition ="dispgoodslist";%>
			<%} %>
	<div class="mainView">
	<!--ページコンテンツ-->
	</div>

			<!--タイトル-->
			<div align="center">
					<a href="menu" id="title3a"><h3 id="title3">SampleShopping</h3></a>
					<p id="title2">- GoodsDetail -</p>
			</div>

		<table id="goodsTable" align="center" class="table table-borderless mt-5">
		<form id ='form' name = 'inputForm' action="">
		<tbody>
			<tr>
				<td rowspan="5" colspan="5"><img src='' class="goodsimg" id="mainImg"></td>
				<td colspan="5" id="goodsName"><c:out value="<%=goodsDto.getGoodsName()%>"/></td>
			</tr>
			<tr class="mt-2">
				<td id="goodsName">値段：<c:out value="<%=goodsDto.getPrice()%>"/></td>
				<td id="goodsName">在庫量：<c:out value="<%=goodsDto.getStock()%>"/></td>
			</tr>
			<tr>	
			<td id="goodsName">数量</td>
				<td>
					<select name="cnt">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr >
				<td>ブランド名：<c:out value="<%=goodsDto.getBrandDto().getName()%>"/></td>
			</tr>
			<tr>
				<td>カテゴリ名：<c:out value="<%=goodsDto.getCategoryDto().getName()%>"/></td>
			</tr>
			<tr>
			<% for(File file : fileList){ %>
				<td>
					<img src='dispgoodsimage?imagePath=/output_imgfile/<%=goodsDto.getId() %>/<%= file.getName() %>' class="minimumimg" id="img<%=i%>">
				</td>
				<% i++; } %>
			</tr>
			<tr>
				<td colspan="5" id="goodsName"><p>説明文<br><c:out value="<%=goodsDto.getDescription()%>"/></p></td>
			</tr>
			</tbody>
			</form>
		</table>

							<div align="center"><a href="<%=transition %>" class="btn-border-bottom">戻る</a></div>

					<!--フッター-->
			<footer>
			<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
			</footer>
</div>
				<!--メニュのjQuery-->
				<!--CDNでjQuery読み込む-->
<script type="text/javascript">
	//変数定義
var navigationOpenFlag = false;
var navButtonFlag = true;
var focusFlag = false;


$(document).ready( function(){
	var img = document.getElementById('img0');
	var src = img.getAttribute('src');
	var img2 = document.getElementById('mainImg');
	img2.setAttribute('src', src);
});


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
