package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";
		String message = "";

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");
		// UserDAOをインスタンス化,関連メソッド呼び出し
		UserDAO userDao = new UserDAO();
		User user = userDao.selectByUser(1);
		try {
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "logout";
		} finally {
			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// 取得したUserオブジェクトをセッションスコープに"user"という名前で登録
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// エラーが無い場合はmenu.jspにフォワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		User user = new User();
		UserDAO userDao = new UserDAO();
		try {
			// userid, password入力パラメータを取得
			String strUserid = request.getParameter("user");
			String password = request.getParameter("password");

			if (strUserid.equals("")) {
				error = "入力されたユーザーIDが空文字です。";
				cmd = "logout";
				return;
			}
			if (password.equals("")) {
				error = "入力されたパスワードが空文字です。";
				cmd = "logout";
				return;
			}

			// int型に変換
			int userid = 0;
			try {
				userid = Integer.parseInt(strUserid);
			} catch (NumberFormatException e) {
				error = "不適切な文字列を数値に変換した為、ログインは出来ません。";
				cmd = "logout";
			}

			user = userDao.selectByUser(userid, password);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "logout";

		} finally {
			// クッキーに入力情報のuseridとpasswordを登録する。（期間は5日間）
			// user用
			if (user.getUserId() != 0) {
				Cookie userCookie = new Cookie("userid", String.valueOf(user.getUserId()));
				userCookie.setMaxAge(60 * 60 * 24 * 5);

				// password用
				Cookie passwordCookie = new Cookie("password", user.getPassword());
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);

				// 取得したUserオブジェクトをセッションスコープに"user"という名前で登録
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				// エラーが無い場合はmenu.jspにフォワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

			} else {
				if (error.equals("")) {
					request.setAttribute("message", "入力値に誤りがあります");
					request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				} else {
					// エラーが有る場合はerror.jspにフォワードする
					request.setAttribute("error", error);
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}
		}
	}
}
