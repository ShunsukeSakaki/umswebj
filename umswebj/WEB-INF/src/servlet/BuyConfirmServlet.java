package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.*;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyConfirmServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// セッション生成
			//HttpSession session = request.getSession();

			// セッションスコープからuser,orderListを取得する
			//User user = (User) session.getAttribute("user");
			//ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("product_list");
			//ArrayList<Integer> amountList = (ArrayList<Integer>) session.getAttribute("amount_list");

			/*
			// セッション切れの場合
			if (user == null) {
				error = "セッション切れの為、購入は出来ません。";
				cmd = "logout";
				return;
			}
			*/

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

			// カートに何も入っていない場合
			if (productList == null || productList.isEmpty()) {
				error = "カートの中に何も無かったので購入は出来ません。";
				cmd = "menu";
				return;
			}

			// 画面からの入力情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			// フォームに入力されたデータを取得する
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String remarks = request.getParameter("remarks");

			// エラーメッセージの設定
			if (name.equals("")) {
				error = "名前が未入力の為、購入は出来ませんでした。";
				cmd = "logout";
				return;
			}

			if (email.equals("")) {
				error = "メールアドレスが未入力の為、購入は出来ませんでした。";
				cmd = "logout";
				return;
			}

			if (address.equals("")) {
				error = "住所が未入力の為、購入は出来ませんでした。";
				cmd = "logout";
				return;
			}

			// DAOオブジェクト生成
			UserDAO userDao = new UserDAO();
			OrderDAO orderDao = new OrderDAO();
			OrderDetailDAO ordDetailDao = new OrderDetailDAO();

			// Userオブジェクト生成
			User user = userDao.selectByEmail(email);
			if (user != null) {
				userDao.delete(user.getMail());
			}
			// Userの情報をDBに登録する
			userDao.insert(name, address, email);

			// Orderオブジェクト生成
			Order order = new Order();

			// 当日の日付をStringとして保存する
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);

			// Orderオブジェクトのインスタンス変数に値を代入
			order.setPaymentStatus("1");
			order.setDeliveryStatus("1");
			order.setPurchaseOrderDate(strDate);
			if (!remarks.equals("")) {
				order.setNote(remarks);
			}

			// Orderオブジェクトの情報をDBに登録
			orderDao.insert(order);

			// OrderStatusオブジェクト生成
			OrderDetail ordDetail = new OrderDetail();

			ArrayList<OrderDetail> ordDetailList = ordDetailDao.select();

		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーの為、購入は出来ません。";
			cmd = "logout";

		} finally {
			if (error.equals("")) {
				// エラーが無い場合は buyConfirm.jspにフォワードする
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
