package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.OrderDetail;

public class OrderDetailDAO extends DbConnection {

	// 購入商品を登録する
	public int insert(OrderDetail productCart) {

		Connection con = null;
		Statement smt = null;

		// 購入商品を格納するオブジェクト
		OrderDetail orderDetail = new OrderDetail();

		// SQL文
		String sql = "INSERT INTO order_detail VALUES(" + productCart.getOrderNumber() + "," + productCart.getUserId()
				+ "," + productCart.getProductId() + "," + productCart.getCount() + ")";

		// 登録件数格納用変数
		int count = 0;
		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文発行
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// リソース解放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

}
