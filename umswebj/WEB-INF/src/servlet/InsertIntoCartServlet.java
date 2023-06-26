package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderDetail;
import bean.Product;
import dao.ProductDAO;

public class InsertIntoCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字エンコーディングを設定
		request.setCharacterEncoding("UTF-8");
		String error = null;
		String cmd = "";// エラー後の遷移先指定用変数

		try {
			// uniformList.jspからproductId,count受け取り
			String productId = request.getParameter("productId");// 商品ID
			String countString = request.getParameter("count");// 個数

			// 受け取った値がString型なのでint型にキャスト
			int count = Integer.parseInt(countString);

			ProductDAO productDao = new ProductDAO();
			// 商品IDより一件分のデータ取得
			Product product = productDao.selectByProductId(productId);

			// 入力チェック
			if (countString.equals("0")) {
				error = "数量が未入力の為、カートに追加出来ませんでした。";
				cmd = "list";
				return;
			}

			OrderDetail orderDetail = new OrderDetail();
			// 商品ID取得 orderItemに格納
			orderDetail.setProductId(product.getProductId());
			// 商品数追加
			orderDetail.setCount(count);

			// JSP表示用
			request.setAttribute("orderDetail", orderDetail);
			request.setAttribute("product", product);

			// session準備
			HttpSession session = request.getSession();

			// セッションからカート状況を取得
			ArrayList<OrderDetail> list = (ArrayList<OrderDetail>) session.getAttribute("order_list");

			// 在庫数をチェック
			if (orderDetail.getCount() > product.getStock()) {
				error = "在庫数が不足しています。";
				cmd = "list";
				return;
			}

			// カートが空の場合
			if (list == null) {
				list = new ArrayList<OrderDetail>();
			} else {
				// カート内に同じ商品が既に存在する場合、数量と価格を更新する
				for (OrderDetail existingItem : list) {
					if (existingItem.getProductId() == orderDetail.getProductId()) {
						// 在庫数をチェック
						int updatedCount = existingItem.getCount() + orderDetail.getCount();
						if (updatedCount > product.getStock()) {
							error = "在庫数が不足しています。";
							cmd = "list";
							return;
						}
						existingItem.setCount(updatedCount);
						return; // 更新が完了したらメソッドを終了する
					}
				}

			}
			// listに格納
			list.add(orderDetail);
			// 格納したlistをorder_listでsessionに登録
			session.setAttribute("order_list", list);

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
