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
				<a href="top.html" id="title3a"><h3 id="title3">SampleShopping</h3></a>
				<p id="title2">- Login -</p>
		</div>


	<form action="/SampleShopping/auth" method="POST">
	<div align="center">
		<table id="loginTable">
			<tr>
				<th>UserId</th>
				<td><input type="text" name="login_Id"></input></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="password"></input></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="送信">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="insert.html" class="btn-border-bottom">アカウントを作成</a>
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
