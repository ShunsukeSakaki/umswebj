<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*,util.MyFormat"%>

<%
	//MyFormatクラスのインスタンス化
	MyFormat objFormat = new MyFormat();

	// セッションスコープから取得するデータ
	//ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("product_list");
	//ArrayList<Integer> productList = (ArrayList<Integer>) session.getAttribute("amount_list");

	// ↓ 後で消す（仮データ）
	int amount1 = 2;
	Product prod1 = new Product();
	prod1.setProductId(1);
	prod1.setProduct("ウニフォームA");
	prod1.setPrice(1000);
	prod1.setStock(10);
	prod1.setImageUrl("xxx");

	int amount2 = 1;
	Product prod2 = new Product();
	prod2.setProductId(2);
	prod2.setProduct("ウニフォームB");
	prod2.setPrice(700);
	prod2.setStock(5);
	prod2.setImageUrl("yyy");

	ArrayList<Product> productList = new ArrayList<Product>();
	productList.add(prod1);
	productList.add(prod2);

	ArrayList<Integer> amountList = new ArrayList<Integer>();
	amountList.add(amount1);
	amountList.add(amount2);
	// ↑ 後で消す（仮データ）
%>

<html>
<head>
<title>注文画面</title>
</head>
<body>
	<div style="text-align: center">
		<!-- ヘッダー部分 -->
		<%@include file="../common/header.jsp"%>

		<!-- 画面の見出し -->
		<h2>注文画面</h2>
		<hr>
		<br> <br>

		<!-- フォーム -->
		<form action="<%=request.getContextPath()%>/buyConfirm" method="post">
			<table style="align: center; margin: auto; border-spacing: 20px">
				<tr>
					<td style="text-align: center">氏名</td>
					<td style="text-align: center"><input type="text" name="name"></td>
				</tr>
				<tr>
					<td style="text-align: center">メールアドレス</td>
					<td style="text-align: center"><input type="text" name="email"></td>
				</tr>
				<tr>
					<td style="text-align: center">住所</td>
					<td style="text-align: center"><input type="text"
						name="address"></td>
				</tr>
				<tr>
					<td style="text-align: center">備考欄</td>
					<td style="text-align: center"><textarea name="remarks"
							rows="5" cols="40"></textarea></td>
				</tr>
			</table>

			<table style="align: center; margin: auto; border-spacing: 20px">
				<tr>
					<th colspan="2">購入商品</th>
				</tr>
				<%
					int total = 0;

					if (productList != null) {
						for (int i = 0; i < productList.size(); i++) {
							Product product = productList.get(i);
							int amount = amountList.get(i);
				%>
				<tr>
					<td><%=product.getProduct()%></td>
					<td><%=amount%>点</td>
				</tr>
				<%
					}
					} else {
				%>
				<tr class="cells">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<%
					}
				%>
			</table>
			<div style="text-align: right; width: 600px">
				<input type="submit" value="購入">
			</div>
		</form>

		<!-- フッター部分 -->
		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>