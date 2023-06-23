<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User" %>

<html>
	<head>
		<title>メニュー画面</title>
	</head>
	<body>
		<div style="text-align:center">
			<!-- ヘッダー -->
			<%@include file="/common/header.jsp" %>
			<!-- ユーザー情報 -->
			<%@include file="/common/userInfo.jsp" %>


			<hr style="height:5; background-color:#32CD32"/>

			<h2 style="color:#A52A2A">MENU</h2>

			<hr style="height:5; background-color:#32CD32"/>

			<!-- メニュー一覧 -->
			<!-- 管理者 -->
			<%if(user.getAuthority().equals("1")){ %>
				<table style="align:center; margin:auto; border-spacing:20px">
					<tr><td><a href="<%=request.getContextPath() %>/list">[受注一覧]</a></td></tr>
					<tr><td><a href="<%=request.getContextPath() %>/logout">[ログアウト]</a></td></tr>
				</table>
			<%} %>

			<!-- ゲスト -->
			<%if(user.getAuthority().equals("2")){ %>
				<table style="align:center; margin:auto; border-spacing:20px">
					<tr><td><a href="<%=request.getContextPath() %>/uniformList">[商品一覧]</a></td></tr>
					<tr><td><a href="<%=request.getContextPath() %>/showCart">[カート確認]</a></td></tr>
					<tr><td><a href="<%=request.getContextPath() %>/logout">[ログアウト]</a></td></tr>
				</table>
			<%} %>


			<!-- フッター -->
			<%@include file="/common/footer.jsp" %>
		</div>
	</body>
</html>