<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
%>

<html>
	<head>
		<title>Error</title>
	</head>
	<body>

		<!-- ヘッダー -->
		<div style="text-align:center">
			<%@include file="/common/header.jsp" %>

			<!-- エラーメッセージ -->
			<p style="color:#A52A2A">■■エラー■■</p>
			<p><%=error%></p>
			<%if(cmd.equals("menu")){%>
				[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ戻る</a>]
				<%}else if(cmd.equals("uniformList")){ %>
					[<a href="<%=request.getContextPath() %>/uniformList">商品一覧に戻る</a>]
				<%}else if(cmd.equals("orderList")){ %>
					[<a href="<%=request.getContextPath() %>/uniformList">受注一覧に戻る</a>]
				<%}else if(cmd.equals("logout")){%>
					[<a href="<%=request.getContextPath() %>/logout">ログイン画面へ</a>]
				<%} %>

			<!-- フッター -->
			<%@include file="/common/footer.jsp" %>
		</div>
	</body>
</html>