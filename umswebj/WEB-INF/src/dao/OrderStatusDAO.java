package dao;

import java.sql.*;
import java.util.*;

import bean.OrderStatus;

public class OrderStatusDAO extends DbConnection {

	// 受注状況を全件取得するメソッド
	public ArrayList<OrderStatus> selectAll() {
		// 変数宣言
		Connection con = null;
		Statement smt = null;

		// return用のオブジェクト生成
		ArrayList<OrderStatus> orderList = new ArrayList<OrderStatus>();

		// SQL文(購入者情報と商品情報を組み合わせたものを検索)
		String sql = "SELECT o.order_number, u.user_id, u.name, sum(p.price*od.count), o.payment_status, o.delivery_status, o.purchase_order_date, o.note FROM `order` o "
				+ "INNER JOIN order_detail od ON o.order_number = od.order_number "
				+ "INNER JOIN user u ON od.user_id = u.user_id "
				+ "INNER JOIN product_info p ON od.product_id = p.product_id " + "GROUP BY o.order_number";

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文をDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果を配列に格納
			while (rs.next()) {
				OrderStatus orderStatus = new OrderStatus();

				orderStatus.setOrderNum(rs.getInt("order_number"));
				orderStatus.setName(rs.getString("name"));
				orderStatus.setPrice(rs.getInt("sum(p.price*od.count)"));
				orderStatus.setPurchaseOrderDate(rs.getString("purchase_order_date"));
				orderStatus.setPaymentStatus(rs.getString("payment_status"));
				orderStatus.setDeliveryStatus(rs.getString("delivery_status"));

				orderList.add(orderStatus);
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

	// 受注状況の詳細情報を取得するメソッド
	public OrderStatus selectByOrderNum(int orderNum) {
		Connection con = null;
		Statement smt = null;

		// 受注状況を格納するオブジェクト
		OrderStatus orderStatus = new OrderStatus();

		// SQL文
		String sql = "SELECT o.order_number, u.user_id, u.name, sum(p.price*od.count), o.payment_status, o.delivery_status, o.purchase_order_date, o.note, u.mail, u.address FROM `order` o "
				+ "INNER JOIN order_detail od ON o.order_number = od.order_number "
				+ "INNER JOIN user u ON od.user_id = u.user_id "
				+ "INNER JOIN product_info p ON od.product_id = p.product_id " + "WHERE = " + orderNum
				+ "GROUP BY o.order_number";

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文をDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果を格納
			if (rs.next()) {
				orderStatus.setName(rs.getString("name"));
				orderStatus.setPrice(rs.getInt("sum(p.price*od.count)"));
				orderStatus.setPurchaseOrderDate(rs.getString("purchase_order_date"));
				orderStatus.setPaymentStatus(rs.getString("payment_status"));
				orderStatus.setDeliveryStatus(rs.getString("delivery_status"));
				orderStatus.setMail(rs.getString("mail"));
				orderStatus.setAddress(rs.getString("address"));
				orderStatus.setNote(rs.getString("note"));
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
		return orderStatus;
	}

}
