<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.*,util.MyFormat"%>

<%
ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("product_list");
User user = (User)session.getAttribute("user");
MyFormat myFormat = new MyFormat();
int plusStock = (int) request.getAttribute("plusStock");

%>

<html>
	<head>
		<title>一覧画面</title>
	</head>
	<body>
		<div style="text-align:center">
		<!-- ヘッダー -->
			<%@include file="/common/header.jsp" %>

			<!-- メニュー -->
			<table style="margin:auto; width:100%; align:center">
				<tr>
					<td style="width:30%; text-align:center">
						[<a href="<%=request.getContextPath() %>/view/menu.jsp">MENU</a>]
					</td>
					<td style="width:30%; font-size:24px; text-align:center; color:#A52A2A">商品一覧</td>
					<td style="width:30%; text-align:center">&nbsp;</td>
				</tr>
			</table>

			<hr style="height:1; background-color:#32CD32"/>

			<!-- 一覧表示 -->
			<table style="margin:0 auto; width:100%">
				<tr>
					<th style="background-color:#D3D3D3; width:25%">商品</th>
					<th style="background-color:#D3D3D3; width:25%">在庫</th>
					<th style="background-color:#D3D3D3; width:25%">価格</th>
	<%
	if(user.getAuthority().equals("1")){
	%>
					<th style="background-color:#D3D3D3; width:25%">購入数</th>
	<%
	}else{
	%>
					<th style="background-color:#D3D3D3; width:25%">追加在庫数</th>
	<%
	}
	%>
				</tr>
				<%if(list != null){
					for(int i = 0; i < list.size(); i++){%>
						<tr>
							<td style="text-align:center; width:25%">
									<%=list.get(i).getProduct() %>
							</td>
							<td style="text-align:center; width:25%"><%=list.get(i).getStock()+plusStock %></td>
							<td style="text-align:center; width:25%"><%=myFormat.moneyFormat(list.get(i).getPrice()) %></td>
	<%
	if(user.getAuthority().equals("2")){
	%>
							<td style="text-align:center; width:25%">
								<%if(list.get(i).getStock() > 0){ %>
									<form action="<%=request.getContextPath() %>/insertIntoCart" method="get">
										<input type="hidden" name="productId" value="<%=list.get(i).getProductId() %>">
										<select name="count">
										<%for(int j = 0; j <= list.get(i).getStock(); j++){ %>
											<option value="<%=j %>"><%=j %></option>
										<%} %>
										</select>
										<input type="submit" value="カートに入れる">
									</form>
									<%}else{ %>
										在庫切れです
									<%} %>
	<%
	}else{
	%>
							<td style="text-align:center; width:25%">
							<form action="<%=request.getContextPath() %>/addStock" method="get">
							<input type="hidden" name="productId" value="<%=list.get(i).getProductId() %>">
							<input type="hidden" name="stock" value="<%=list.get(i).getStock() %>">
							<input type="text"name="plusStock"value="">
							<input type="submit" value="在庫数を更新">
							</form>
	<%
	}
	%>
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