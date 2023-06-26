package dao;

import java.sql.*;
import java.util.*;

import bean.Order;
import bean.User;

public class OrderDAO extends DbConnection {

	// 配送状況と入金状況の更新
	public void update(Order order) {

		// 変数宣言
		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "UPDATE order SET delivery_status ='" + order.getDeliveryStatus() + "'," + "payment_status ='"
				+ order.getPaymentStatus() + "'" + "WHERE order_number ='" + order.getOrderNumber() + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQL文を転送
			smt.executeUpdate(sql);

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
	}

	public void insert(Order order) {

		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "INSERT INTO `order` VALUES(" + order.getOrderNumber() + ",'1','1',curdate(),'" + order.getNote()
				+ "')";

		try {
			con = getConnection();
			smt = con.createStatement();

			// sql文発行
			smt.executeUpdate(sql);

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
	}

	public ArrayList<Order> selectAll() {

		Connection con = null;
		Statement smt = null;

		// 検索した書籍情報を格納するArrayListオブジェクト
		ArrayList<Order> orderList = new ArrayList<Order>();

		try {

			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文作成
			String sql = "SELECT order_number,payment_status,delivery_status,purchase_order_date,note FROM `order` ORDER BY order_number";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をArrayListに格納
			while (rs.next()) {
				Order order = new Order();
				order.setOrderNumber(rs.getInt("order_number"));
				order.setPaymentStatus(rs.getString("payment_status"));
				order.setDeliveryStatus(rs.getString("delivery_status"));
				order.setPurchaseOrderDate(rs.getString("purchase_order_date"));
				order.setNote(rs.getString("note"));
				orderList.add(order);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);

		} finally {
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
