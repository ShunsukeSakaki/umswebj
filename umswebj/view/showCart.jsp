<%@page import="bean.OrderStatus"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderStatus"%>
<%@page import="java.util.ArrayList,util.MyFormat"%>
<%
	String error = (String) request.getAttribute("error");
	String cmd = (String) request.getAttribute("cmd");
	MyFormat format = new MyFormat();
%>
<html>
<head>
<title>showCart</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">

		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション  -->
				<div id="nav">

					<ul>
						<li><a href="<%=request.getContextPath()%>/view/list.jsp">[商品一覧]</a></li>
					</ul>
				</div>

				<!-- ページタイトル -->
				<div id="page_title">
					<h2>カート内商品</h2>
				</div>
			</div>
		</div>

		<div align="center">
			<table style="margin: 100px 0px 0px 0px">
				<tr>
					<th>商品名</th>
					<th>数量</th>
					<th>削除</th>
				</tr>
				<%
					/*受け取ったカートデータの数だけ画面に表示を行う*/
					ArrayList<OrderStatus> list = (ArrayList<OrderStatus>) request.getAttribute("order_list");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							OrderStatus product = (OrderStatus) list.get(i);
				%>
				<tr>
					<td style="text-align: center; width: 200px"><%=product.getProduct()%></td>
					<td style="text-align: center; width: 200px"><%=product.getCount()%></td>

					<td style="text-align: center; width: 200px"><a
						href="<%=request.getContextPath()%>/showCart?delno=<%=i%>">削除</a></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<hr style="text-align: center; height: 1px; background-color: black;margin-top:100px">
		<hr style="text-align: center; height: 2px; background-color: black">
		<table style="margin: auto">
			<%
				int total = 0;
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						OrderStatus product = (OrderStatus) list.get(i);
						total += product.getPrice();
					}
			%>

			<tr>
				<th style="background-color: #6495ed; width: 200px">合計</th>
				<td><%=format.moneyFormat(total)%></td>
			</tr>
			<%
				}
			%>
		</table>
		<div style="margin: 100px 30% 50px 70%">
			<form action="<%=request.getContextPath()%>/buyConfirm">
				<input type="submit" value="購入">
			</form>
		</div>
	</div>
	<!-- フッター部分 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>