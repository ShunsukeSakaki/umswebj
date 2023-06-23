<%@page import="java.util.ArrayList"%>
<%@page import="bean.OrderedItem"%>
<%@page import="bean.Product"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>
<%
	String error = (String) request.getAttribute("error");
%>

<html>
<head>
<title>ユニフォーム受注管理システム</title>
<style>
.center {
	margin: 100 auto;
	text-align: center;
}

table {
	border: 1px solid gray;
	width: 1000px;
	height: 500px;
}

td {
	border: 1px solid gray;
}

.linkFont {
	color: blue
}
</style>
</head>
<body>
	<%@include file="/common/header.jsp" %>

	<div>
		<h2 class="center">カート追加</h2>
		<p class="linkFont">商品一覧</p>
	</div>
	<hr>

	<h2 class="center">下記の商品をカートに追加しました。</h2>

	<%
		ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) session.getAttribute("order_list");
	%>
<div style="display: flex; justify-content: center;">
	<table>
		<thead>
			<tr>
				<th>Product</th>
				<th>Amount</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (OrderedItem orderedItem : list) {
			%>
			<tr>
				<td width="35%"><%=orderedItem.getProduct()%></td>
				<td><%=orderedItem.getAmount()%></td>
				<td><%=orderedItem.getAmount() * orderedItem.getPrice()%></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</div>


	<p align="center">カート確認</p>


</html>