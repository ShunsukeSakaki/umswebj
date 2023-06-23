package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.User;
import dao.OrderDAO;
import dao.UserDAO;

public class StatusUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッション維持確認
		HttpSession session = request.getSession();
		User User = (User) session.getAttribute("user");

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		// エラー処理に関する変数を宣言
		String error = "";
		String cmd = "";

		// 入力パラメータを取得する
		String userId = request.getParameter("userId");
		String paymentStatus = request.getParameter("paymentStatus");
		String deliveryStatus = request.getParameter("deliveryStatus");

		try {
			// セッション接続確認
			if (User == null) {
				error = "セッション切れの為、購入は出来ません。";
				cmd = "session";
				return;
			}

			// DAOオブジェクト宣言
			OrderDAO orderDao = new OrderDAO();

			// パラメータをオブジェクトにセット
			Order order = new Order();
			order.setUserId(Integer.parseInt(userId));
			order.setPaymentStatus(paymentStatus);
			order.setDeliveryStatus(deliveryStatus);

			// 登録メソッド呼び出し
			orderDao.update(order);

		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/ListOrderServlet").forward(request, response);
			} else {// error.jspに飛び、エラー内容を出力
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}
