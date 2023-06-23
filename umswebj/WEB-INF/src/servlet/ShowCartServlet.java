package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderStatus;
import bean.OrderedItem;
import bean.User;
import dao.OrderedItemDAO;

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
			ArrayList<OrderedItem> order_list = (ArrayList<OrderedItem>) session.getAttribute("order_list");

			// delnoが「null」でない場合order_listから該当の書籍情報を削除
			if (delno != null) {
				order_list.remove(Integer.parseInt(delno));
			}
			// ProductDAOをインスタンス化
			OrderedItemDAO orderedItemDao = new OrderedItemDAO();
			// 空のArraylist作成
			ArrayList<OrderStatus> list = new ArrayList<OrderStatus>();

			// 関連メソッドをorder_list(カートデータ)分だけ呼び出すfor文
			for (int i = 0; i < order_list.size(); i++) {
				OrderedItem orderedItem = order_list.get(i);
				OrderStatus orderStatus = OrderedItemDao.selectAll();
				list.add(orderStatus);
			}

			// 取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納
			request.setAttribute("Product_list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート状況は確認出来ません。";
			cmd = "logout";
		} finally {
			// showCart.jspにフォワード
			request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
		}
	}
}
