<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.*,util.MyFormat"%>

<%
	ArrayList<OrderStatus> list = (ArrayList<OrderStatus>)request.getAttribute("order_list");
MyFormat myFormat = new MyFormat();
%>

<html>
	<head>
		<title>受注状況一覧画面</title>
	</head>
	<body>
		<div style="text-align:center">
		<!-- ヘッダー -->
			<%@include file="/common/header.jsp" %>

			<!-- メニュー -->
			<table style="margin:auto; width:100%; align:center">
				<tr>
					<td style="width:30%; text-align:center">
						[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ戻る</a>]
					</td>
					<td style="width:30%; font-size:24px; text-align:center; color:#A52A2A">受注状況一覧</td>
					<td style="width:30%; text-align:center">&nbsp</td>
				</tr>
			</table>

			<hr style="height:1; background-color:#32CD32"/>

			<!-- 一覧表示 -->
			<table style="margin:0 auto; width:100%">
				<tr>
					<th style="background-color:#D3D3D3; width:14%">No</th>
					<th style="background-color:#D3D3D3; width:14%">氏名</th>
					<th style="background-color:#D3D3D3; width:14%">合計金額</th>
					<th style="background-color:#D3D3D3; width:14%">発注日</th>
					<th style="background-color:#D3D3D3; width:14%">入金状況</th>
					<th style="background-color:#D3D3D3; width:14%">発送準備</th>
					<th style="background-color:#D3D3D3; width:14%">&nbsp</th>
				</tr>
				<%if(list != null){
					for(int i = 0; i < list.size(); i++){%>
						<tr>
							<td style="text-align:center; width:14%"><%=list.get(i).getOrderNum() %></td>
							<td style="text-align:center; width:14%"><%=list.get(i).getName() %></td>
							<td style="text-align:center; width:14%"><%=myFormat.moneyFormat(list.get(i).getPrice()) %></td>
							<td style="text-align:center; width:14%">
								<%=list.get(i).getPurchaseOrderDate().replace("-", "/") %>
							</td>
							<td style="text-align:center; width:14%">
								<%if(list.get(i).getPaymentStatus().equals("1")){ %>
									未入金
								<%}else if(list.get(i).getPaymentStatus().equals("2")){ %>
									入金済み
								<%} %>
							</td>
							<td style="text-align:center; width:14%">
								<%if(list.get(i).getDeliveryStatus().equals("1")){ %>
									未発送
								<%}else if(list.get(i).getDeliveryStatus().equals("2")){ %>
									発送準備中
								<%}else if(list.get(i).getDeliveryStatus().equals("3")){ %>
									発送済み
								<%} %>
							</td>
							<td style="text-align:center; width:14%">
								<a href="<%=request.getContextPath() %>/orderDetail">詳細</a>/
								<a href="<%=request.getContextPath() %>/orderUpdate">更新</a>
							</td>
						</tr>
					<%}
				} %>
			</table>

			<!-- フッター -->
			<%@include file="/common/footer.jsp" %>
		</div>
	</body>
</html>