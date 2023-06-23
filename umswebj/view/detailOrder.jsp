<%@page import="bean.Order,bean.OrderDetail,bean.OrderStatus,"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	String error = (String) request.getAttribute("error");
	ArrayList<OrderDetail> detailList = (ArrayList<OrderDetail>) request.getAttribute("detailList");
	OrderStatus orderStatus = (OrderStatus)request.getAttribute("orderStatus");
	OrderDetail orderDetail = new OrderDetail();
	int totalPrice = 0; //個数*金額
	int sum = 0; //合計金額の合算
%>
<html>
<head>
<title>ユニフォーム受注管理システム</title>
</head>
<body>
	<div>
		<hr style="height: 5; background-color: #0000FF" />
		<br>
		<p>
			<a href="./ListUserServlet">[受注一覧へ戻る]</a>

		</p>
		<p style="text-align: center; font-size: 24px">受注詳細情報</p>
	</div>
	<hr style="height: 4; background-color: #000000" />
	<table style="margin: auto; border: 0;">
		<tr>
			<td colspan=2 style="text-align: center"><a
				href="<%=request.getContextPath()%>/DetailUserServlet?userid=<%=user.getUserid()%>&cmd=updateUser"><input
					type="submit" value="更新"></a>
			<td colspan=2 style="text-align: center">
			<a href="<%=request.getContextPath()%>/DeleteUserServlet?userid=<%=user.getUserid()%>"><input
					type="submit" value="削除"></a>
		</tr>
		<tr>
			<th
				colspan=2 style="background-color: #66CCFF; text-align: left; padding-right: 45px;">個人情報</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">氏名</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getName()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">メールアドレス</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getMail() %></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">住所</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getAddress()%></td>
		</tr>

		<tr>
			<th
				colspan=2 style="background-color: #66CCFF; text-align: left; padding-right: 45px;">購入商品</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"></td>
		</tr>
		<%for(int i = 0;i < detailList.size(); i++){ %>
		<tr>
			<th
				colspan=2 style="background-color: #66CCFF; text-align: left; padding-right: 45px;"><%=i %>番目</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">種類</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=detailList.get(i).getProduct()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">金額</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=detailList.get(i).getPrice()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">個数</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=detailList.get(i).getCount()%></td>
		</tr>
		<% totalPrice= detailList.get(i).getPrice()*detailList.get(i).getCount();%>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">合計金額</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=totalPrice%></td>
		</tr>
		<tr>
			<td
				style="colspan=2; background-color: #00FA9A; text-align: left; padding-right: 45px;">&nbsp;</td>
		</tr>
		<%sum += totalPrice; %>
		<%} %>

		<tr>
			<th
				colspan=2 style="background-color: #66CCFF; text-align: left; padding-right: 45px;">注文情報</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">お支払い金額</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=sum %></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">入金状況</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getPaymentStatus()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">配送状況</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getDeliveryStatus()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">発注日</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getPurchaseOrderDate()%></td>
		</tr>
		<tr>
			<th
				style="background-color: #66CCFF; text-align: left; padding-right: 45px;">備考</th>
			<td
				style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getNote()%></td>
		</tr>

	</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>