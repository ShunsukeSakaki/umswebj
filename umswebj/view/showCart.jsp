<%@page import="bean.OrderStatus"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.OrderInfo"%>
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
					<h1>カート内商品</h1>
				</div>
			</div>
		</div>

		<div align="center">
			<table style="margin: 100px 10px 0px 0px">
				<tr>
					<th>商品名</th>
					<th>数量</th>
					<th>削除</th>
				</tr>
				<%
					/*受け取ったカートデータの数だけ画面に表示を行う*/
					ArrayList<OrderInfo> list = (ArrayList<OrderInfo>) request.getAttribute("order_list");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							OrderInfo orderInfo = (OrderInfo) list.get(i);
				%>
				<tr>
					<td style="text-align: center; width: 300px"><%=orderInfo.getProduct()%></td>
					<td style="text-align: center; width: 200px"><%=orderInfo.getCount()%></td>
					<td style="text-align: center; width: 100px"><a
						href="<%=request.getContextPath()%>/showCart?delno=<%=i%>">削除</a></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<table style="margin: auto">
			<%
				int total = 0;
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						OrderInfo orderInfo = (OrderInfo) list.get(i);
						total += orderInfo.getPrice();
					}
			%>

			<tr>
				<th style="text-decoration:underline">合計</th>
				<td style="text-decoration:underline"><%=format.moneyFormat(total)%></td>
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