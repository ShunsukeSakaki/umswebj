package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.OrderedItem;
import bean.Product;
import dao.OrderedItemDAO;
import dao.ProductDAO;

public class InsertIntoCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字エンコーディングを設定
		request.setCharacterEncoding("UTF-8");
		String error = null;
		String cmd = "";// エラー後の遷移先指定用変数

		try {
			// jspから受け取り
			String productId = request.getParameter("productId");// 商品ID
			String countString = request.getParameter("count");// 個数

			int count = Integer.parseInt(countString);

			ProductDAO productDao = new ProductDAO();
			// 商品IDより一件分のデータ取得
			Product product = productDao.selectByProductId(productId);

			OrderedItem orderedItem = new OrderedItem();

			if (countString.equals("0")) {
				error = "数量が未入力の為、カートに追加出来ませんでした。";
				cmd = "list";
				return;
			}

			// 商品名取得 orderItemに格納
			orderedItem.setProduct(product.getProduct());
			// 商品数追加
			orderedItem.setAmount(count);

			// orderItemに値段格納
			orderedItem.setPrice(product.getPrice());

			// session準備
			HttpSession session = request.getSession();

			// セッションからカート状況を取得
			ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) session.getAttribute("order_list");

			// カートが空の場合
			if (list == null) {
				list = new ArrayList<OrderedItem>();
			} else {
				// カート内に同じ商品が既に存在する場合、数量と価格を更新する
				for (OrderedItem existingItem : list) {
					if (existingItem.getProduct().equals(orderedItem.getProduct())) {
						existingItem.setAmount(existingItem.getAmount() + orderedItem.getAmount());
						existingItem.setPrice(existingItem.getPrice() + orderedItem.getPrice());
						return; // 更新が完了したらメソッドを終了する
					}
				}
			}

			// セッションに追加
			session.setAttribute("order_list", list);

			// listに格納
			list.add(orderedItem);

		} catch (Exception e) {
			error = "DB接続エラーの為、カートに追加は出来ません。";
		} finally {
			if (error == null) { // エラーなしならカート追加画面へ
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			} else { // エラーありならerrorとcmdを持ってエラー画面へ
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}

}
