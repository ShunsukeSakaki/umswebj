package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Order;
import bean.OrderDetail;
import bean.OrderStatus;
import bean.User;
import dao.OrderDetailDAO;
import dao.OrderStatusDAO;
import dao.UserDAO;

public class OrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッション維持確認
		HttpSession session = request.getSession();
		User User = (User) session.getAttribute("user");

		//各変数宣言
		String strOrderNumber = request.getParameter("orderNumber");
		String error = "";
		String cmd = request.getParameter("cmd");
		int orderNumber = Integer.parseInt(strOrderNumber);

		try {
			// セッション切れの場合エラーを投げる
			if (User == null) {
				error = "セッション切れの為、購入は出来ません。";
				cmd = "session";
				return;
			}

			// 注文者のidをもとに商品情報を格納する配列
			ArrayList<OrderDetail> detailList = new ArrayList<OrderDetail>();
			//配送状況など、商品情報以外を格納する配列
			OrderStatus orderStatus = new OrderStatus();

			// オブジェクト
			OrderStatusDAO orderStatusDao = new OrderStatusDAO();
			OrderDetailDAO orderDetailDao = new OrderDetailDAO();

			// userid検索メソッド
			detailList = orderDetailDao.selectByOrderNumber(orderNumber);
			//商品情報以外全て格納
			orderStatus = orderStatusDao.selectByOrderNum(orderNumber);

			// 検索結果を持ってdetail.jspにフォワード
			request.setAttribute("detailList", detailList);
			request.setAttribute("orderStatus", orderStatus);

		} catch (IllegalStateException e) {
			if (cmd.equals("orderStastus")) {
				error = "DB接続エラーの為、ユーザーの詳細情報は表示出来ません。";
				cmd = "session";
			} else {
				error = "DB接続エラーの為、更新画面は表示できませんでした。";
				cmd = "session";
			}
			return;// エラーが発生した場合、tryを脱してfinallyへ飛ぶ

		} finally {
			if (error.equals("") && cmd.equals("orderStatus")) {
				request.getRequestDispatcher("/view/orderStatus.jsp").forward(request, response);

			}
			if (error.equals("") && cmd.equals("updateOrder")) {
				request.getRequestDispatcher("/view/updateOrder.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}
}
