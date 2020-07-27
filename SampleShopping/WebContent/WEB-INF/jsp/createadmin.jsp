<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<p id="title2">- Insert -</p>
		</div>
		<form action="createadmin" method="post">
			<div align="center">
		   <div class="asd">
			   <table id="gaiyou">
		   <tr>
			 <th align="center">お名前<font color="red"> *</font></th>
			 <td><input class="form-control" type="text" name="username" value=""></td>
		   </tr>
		   <tr>
			 <th align="center">ID<font color="red"> *</font></th>
		   <td><input class="form-control" type="text" name="loginid" value=""></td>
		   </tr>
		   <tr>
			 <th align="center">パスワード</th>
			 <td><input class="form-control" type="password" name="userpassword" value=""></td>
		   </tr>
		   <tr>
			 <th>郵便番号1</th>
			 <td><input class="form-control" type="text" name="zipcode" value=""></td>
		   </tr>
		   <tr>
			 <th>郵便番号2</th>
			 <td><input class="form-control" type="text" name="zipcodesub" value=""></td>
		   </tr>
		   <tr>
			 <th>住所１</th>
			 <td><input class="form-control" type="text" name="address" value=""></td>
		   </tr>
		   <tr>
			 <th>住所２</th>
			 <td><input class="form-control" type="text" name="addresssub" value=""></td>
		   </tr>
		    <tr>
		   	 <th>電話番号</th>
		   	 <td><input class="form-control" type="text" name="tel" value=""></td>
		    </tr>
			<tr>
			 <th>メールアドレス</th>
			 <td><input class="form-control" type="email" name="mailaddress" value="" id="textbox"></td>
			</tr>
			 <tr>
			  <th>性別</th>
			  <td>
			  <input type="radio" name="gender" value="0">秘密
			  <input type="radio" name="gender" value="1">女性
		      <input type="radio" name="gender" value="2">男性
		      <input type="radio" name="gender" value="3">その他
			  </td>
			 </tr>
		   </table>
		</div>
			<a href="/SampleShopping/loginadmin" class="btn-border-bottom px-4 mr-4">戻る</a>
			<input type="submit" value="登録" class="btn-border-bottom px-4 ml-4">
		</div>
		</form>
	<footer>
	<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
	</footer>

			<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		</body>
</html>
