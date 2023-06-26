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
import bean.User;
import dao.ProductDAO;

public class ShowCartServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定する
			request.setCharacterEncoding("UTF-8");

			// delnoの入力パラメータを取得
			String delno = request.getParameter("delno");

			// セッションオブジェクトの生成
			HttpSession session = request.getSession();

			// セッションから"user"を取得する
			User user = (User) session.getAttribute("user");

			// (セッション切れの場合はerror.jspに遷移する)
			if (user == null) {
				error = "セッション切れの為、カート状況は確認出来ません。";
				return;
			}
			// セッションから"order_list"を取得
			ArrayList<OrderDetail> order_list = (ArrayList<OrderDetail>) session.getAttribute("order_list");

			// delnoが「null」でない場合order_listから該当の書籍情報を削除
			if (delno != null) {
				order_list.remove(Integer.parseInt(delno));
			}
			// ProductDAOをインスタンス化
			ProductDAO productDao = new ProductDAO();

			// 空のArraylist作成
			ArrayList<Product> list = new ArrayList<Product>();

			// 関連メソッドをorder_list(カートデータ)分だけ呼び出すfor文
			for (int i = 0; i < order_list.size(); i++) {
				OrderDetail orderDetail = order_list.get(i);
				Product product = productDao.selectByProduct(orderDetail.getOrderNumber());
				list.add(product);
			}

			// 取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納
			request.setAttribute("order_list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート状況は確認出来ません。";
			cmd = "logout";
		} finally {
			// showCart.jspにフォワード
			request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
		}
	}
}
