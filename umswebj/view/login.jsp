<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>ログイン</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">

		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<div align="center">
			<table style="margin: 120px 0px 0px 0px;">



				<tr>
					<th>ユーザー</th>
					<form action="<%=request.getContextPath()%>/login" method="POST">
					<td><input type="text" size="30" name="user"></input></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" size="30" name="password"></input></td>
				</tr>
			</table>
			<div style="margin: 50px">
				<input type="submit" value="管理者ログイン">
				</form>
				<a href="<%=request.getContextPath()%>/login">ゲストログイン</a>
			</div>

		</div>
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>