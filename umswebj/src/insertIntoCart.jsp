<%@page import="java.util.ArrayList"%>
<%@page import="bean.OrderDetail"%>
<%@page import="bean.Product"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import="util.MyFormat"%>
<%
ArrayList<OrderDetail> orderList = (ArrayList<OrderDetail>) session.getAttribute("order_list");
%>



<%
	String error = (String) request.getAttribute("error");
	Product product = (Product) request.getAttribute("product");
	OrderDetail orderDetail = (OrderDetail) request.getAttribute("orderDetail");

	// カートに追加された商品の合計金額を計算
	int totalPrice = orderDetail.getCount() * product.getPrice();


	MyFormat myFormat = new MyFormat();
%>

<html>
<head>
<title>ユニフォーム受注管理システム</title>
<style>
.center {
	margin: 50 auto;
	text-align: center;
}

table {
	border: 1px solid gray;
	width: 1000px;
	height: 100px;
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
	<%@include file="/common/header.jsp"%>


	<div>
		<h2 class="center">カート追加</h2>
		<a href="<%=request.getContextPath()%>/uniformList">[商品一覧]</a>
	</div>
	<hr>

	<h2 class="center">下記の商品をカートに追加しました。</h2>


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
					String formattedPrice = myFormat.moneyFormat(totalPrice);
				%>

				<tr>
					<td style="width: 100px;"><%=product.getProduct()%></td>
					<td><%=orderDetail.getCount()%></td>
					<td><%=formattedPrice%></td>
				</tr>

			</tbody>
		</table>
	</div>
<br>
   <div style="display: flex; justify-content: center;">
  <a href="<%=request.getContextPath()%>/showCart">カート確認</a>
</div>
<br>
<!-- フッター -->
			<%@include file="/common/footer.jsp" %>
</body>
</html>