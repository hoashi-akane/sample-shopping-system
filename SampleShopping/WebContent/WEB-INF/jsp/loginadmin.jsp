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
				<a href="menuadmin" id="title3a"><h3 id="title3">SampleShopping</h3></a>
				<p id="title2">- Admin Login -</p>
		</div>

	<form action="/SampleShopping/authadmin" method="POST">
	<div align="center">
		<table id="loginTable">
			<tr>
				<th>ユーザーID</th>
				<td><input class="form-control" type="text" name="userid" value=""></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input class="form-control" type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="mt-3 btn btn-outline-dark" type="submit" value="ログイン">
				</td>
			</tr>
		</table>
	</div>
	</form>


			<!--フッター-->
	<footer>
	<small id="footer">Copyright&copy;Kadai Website,all rightsreserved.</small>
	</footer>





			<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		</body>
</html>
