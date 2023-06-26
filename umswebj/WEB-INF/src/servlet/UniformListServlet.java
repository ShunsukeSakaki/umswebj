package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import dao.ProductDAO;

public class UniformListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String strPlusStock = request.getParameter("plusStock");
		if(strPlusStock ==null) {
			strPlusStock = "";
		}
		ArrayList<Product> list = new ArrayList<Product>();
		int plusStock = 0;
		String error = null;// エラーメッセージ用変数
		String cmd = "logout";// エラー後の遷移先指定用変数
		try {
			//DB接続の為のオブジェクト
			ProductDAO productDAO = new ProductDAO();

			if(!strPlusStock.equals("")) {
				plusStock = Integer.parseInt(strPlusStock);
			}

			//全商品の情報を取得する
			list = productDAO.selectAll();

			//リクエストスコープに登録
			request.setAttribute("product_list", list);
			request.setAttribute("plusStock", plusStock);

		} catch (Exception e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
		} finally {
			if (error == null) { // エラーなしなら一覧表示へ
				request.getRequestDispatcher("/view/uniformList.jsp").forward(request, response);
			} else { // エラーありならerrorとcmdを持ってエラー画面へ
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
