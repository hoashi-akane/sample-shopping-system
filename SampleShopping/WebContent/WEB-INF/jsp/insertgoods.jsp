<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
    <%@page import="dto.*" %>
<% 
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
		<a href="menuadmin" id="title3a"><h3 id="title3">SampleShopping</h3></a>
		<p id="title2">- CreateGoods -</p>
	</div>

	<form action="/SampleShopping/insertgoods" method="post" enctype="multipart/form-data">
		<div align="center">
		   <div class="asd">
		   <table class="mt-5" id="gaiyou">
			<tr>
			   	 <th>商品名</th>
			 <td><input class="form-control" type="text" name="goods_name"></td>
		   </tr>
			<tr>
				<th>商品画像</th>
				<td>
					<div id="file" class="input-group">
					    <div class="custom-file">
					        <input type="file" class="custom-file-input" id="customFile" name="file" multiple>
					        <label class="custom-file-label" for="customFile" data-browse="参照">ファイル選択...</label>
					    </div>
					    <div class="input-group-append">
					        <button type="button" class="btn btn-outline-secondary reset"><i class="fas fa-times fa-fw"></i>取消</button>
					    </div>
					</div>
				</td>
		   <tr>
			 <th align="center">値段</th>
		   <td><input class="form-control" type="number" name="price"></td>
		   </tr>
		   <tr>
			 <th align="center">在庫数</th>
			 <td><input class="form-control" type="number" name="stock"></td>
		   </tr>
		   <tr>
			 <th>説明文</th>
			 <td><textarea class="form-control" name="description" cols="50" rows="7" id="textbox"></textarea></td>
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
				 	<option value="<%=brandDto.getId()%>"><c:out value="<%=brandDto.getName() %>"/></option>
				 	<%} %>
			 	</select>
			</td>
		    </tr>
		   </table>
		</div>
			<a href="dispgoodslistadmin" class="btn-border-bottom" id="accountbtn">戻る</a>
			<button type="submit" class="  btn btn-outline-dark">登録</button>
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


		$('.custom-file-input').on('change', handleFileSelect);
		function handleFileSelect(evt) {
		        $('#preview').remove();// 繰り返し実行時の処理
		        $(this).parents('.input-group').after('<div id="preview"></div>');

		  var files = evt.target.files;

		    var output = [];
		    for (var i = 0, f; f = files[i]; i++) {
		        output.push('<li>', escape(f.name), ' (', f.size, ' bytes)</li>');
		    }
		    $('#preview').append('<ul>' + output.join('') + '</ul>');

		    $(this).next('.custom-file-label').html(+ files.length + '個のファイルを選択しました');
		}


		//ファイルの取消
		$('.reset').click(function(){
		    $(this).parent().prev().children('.custom-file-label').html('ファイル選択...');
		    $('#preview').remove();
		    $('.custom-file-input').val('');
		})
		</script>



				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
				<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
			</body>
	</html>
