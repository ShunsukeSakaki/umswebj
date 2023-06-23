package dao;

import java.sql.*;
import java.util.*;

import bean.Order;
import bean.OrderDetail;

public class OrderDetailDAO extends DbConnection {

	// 注文情報を全表示する
	public ArrayList<OrderDetail> selectByOrderNumber(int orderNumber) {
		// 変数宣言
		Connection con = null;
		Statement smt = null;

		// return用のオブジェクト生成
		ArrayList<OrderDetail> orderList = new ArrayList<OrderDetail>();

		// SQL文(注文情報を検索)
		String sql = "SELECT * FROM order_detail o "
				+ "INNER JOIN product_info  p ON o.product_id = p.product_id "
				+ "WHERE o.order_number =" +orderNumber+";";

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文をDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果を配列に格納
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderNumber(rs.getInt("order_number"));
				orderDetail.setUserId(rs.getInt("user_id"));
				orderDetail.setProductId(rs.getInt("product_id"));
				orderDetail.setCount(rs.getInt("count"));
				orderDetail.setProduct(rs.getString("product"));
				orderDetail.setPrice(rs.getInt("price"));
				orderDetail.setStock(rs.getInt("stock"));
				orderDetail.setImageUrl(rs.getString("image_url"));
				orderList.add(orderDetail);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// リソースの開放
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
		return orderList;
	}
}
