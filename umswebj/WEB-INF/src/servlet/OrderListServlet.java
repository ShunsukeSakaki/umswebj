package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

import bean.OrderStatus;
import dao.OrderStatusDAO;;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;// エラーメッセージ用変数
		String cmd = "logout";// エラー後の遷移先指定用変数
		try {
			//DB操作のためのオブジェクト
			OrderStatusDAO orderStatusDAO = new OrderStatusDAO();

			//全注文情報を取得
			ArrayList<OrderStatus> list = orderStatusDAO.selectAll();

			//リクエストスコープに登録
			request.setAttribute("order_list", list);
		}catch (Exception e) {
			error = "DB接続エラーの為、受注状況一覧表示は行えませんでした。";
		} finally {
			if (error == null) { // エラーなしなら一覧表示へ
				request.getRequestDispatcher("/view/orderList.jsp").forward(request, response);
			} else { // エラーありならerrorとcmdを持ってエラー画面へ
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}
