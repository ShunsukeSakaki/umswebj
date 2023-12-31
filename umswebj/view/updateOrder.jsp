<%@page import="bean.OrderStatus"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="bean.Order,bean.User,"%>

<%
	OrderStatus orderStatus = (OrderStatus) request.getAttribute("orderStatus");
	String error = (String) request.getAttribute("error");
%>


<html>
<head>
<title>ユニフォーム受注管理システム</title>
</head>
<body>

	<%@ include file="/common/header.jsp"%>
	<hr style="height: 5; background-color: #GRAY" />
	<br>
	<div>
		<p>
			<a href="./orderList">[受注一覧]</a>
		</p>
		<p style="text-align: center; font-size: 24px">受注更新画面</p>
	</div>
	<hr style="height: 4; background-color: #000000" />

	<form action="<%=request.getContextPath()%>/statusUpdate">
		<table style="margin: auto; border: 0;">
			<tr>
				<td>&nbsp;</td>
				<td>&lt;変更前情報&gt;</td>
				<td>&lt;変更後情報&gt;</td>
			<tr>
				<th
					style="background-color: #66CCFF; text-align: left; padding-right: 45px;">入金状況</th>
				<td
					style="background-color: #00FA9A; text-align: left; padding-right: 45px;"><%=orderStatus.getPaymentStatus()%></td>
				<td><select name="paymentStatus">
						<option value="1">未入金</option>
						<option value="2">入金済</option>
				</select></td>
			</tr>
			<tr>
				<th style="background-color: #66CCFF; text-align: left">配送状況</th>
				<td style="background-color: #00FA9A; text-align: left"><%=orderStatus.getDeliveryStatus()%></td>
				<td><select name="deliveryStatus">
						<option value="1">未</option>
						<option value="2">発送準備中</option>
						<option value="3">発送済</option>
				</select></td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center"><input type="submit"
					value="変更完了"></td>
			</tr>
		</table>
		<input type="hidden" name="orderNumber" value="<%=orderStatus.getOrderNum()%>">
	</form>
</body>
</html>