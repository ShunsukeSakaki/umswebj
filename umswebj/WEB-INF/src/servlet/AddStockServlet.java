package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import dao.ProductDAO;

public class AddStockServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String strPlusStock = request.getParameter("plusStock");
		String strStock = request.getParameter("stock");
		String strProductId = request.getParameter("productId");

		int productId = Integer.parseInt(strProductId);
		int plusStock = Integer.parseInt(strPlusStock);
		int Stock = Integer.parseInt(strStock);
		int newStock = Stock + plusStock;

		String error = null;// エラーメッセージ用変数
		String cmd = "logout";// エラー後の遷移先指定用変数
		try {
			// DB接続の為のオブジェクト
			ProductDAO productDAO = new ProductDAO();

			// 全商品の情報を取得する
			productDAO.update(newStock,productId);

		} catch (Exception e) {
			error = "DB接続エラーの為、在庫追加は行えませんでした。";
		} finally {
			if (error == null) { // エラーなしなら一覧表示へ
				request.getRequestDispatcher("/uniformList").forward(request, response);
			} else { // エラーありならerrorとcmdを持ってエラー画面へ
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
