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
		<!--タイトル-->

		<div align="center">
				<a href="/SampleShopping/login" id="title3a"><h3 id="title3">SampleShopping</h3></a>
				<p id="title2">- Login -</p>
		</div>


	<form action="/SampleShopping/auth" method="POST">
	<div align="center">
		<table id="loginTable">
			<tr>
				<th>ユーザーID</th>
				<td><input class="form-control" type="text" name="login_Id"></input></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input class="form-control" type="password" name="password"></input></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="mt-3 btn btn-outline-dark" type="submit" value="ログイン">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="createuser" class="mt-3 btn-border-bottom">アカウントを作成</a>
				</td>
			</tr>
		</table>
	</div>
	</form>


			<!--フッター-->
	<footer>
	<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
	</footer>
		</body>
</html>
