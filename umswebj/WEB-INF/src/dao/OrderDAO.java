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

	// ユーザー情報を登録するメソッド
	public void insert(Order order) {

		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "INSERT INTO `order` VALUES(NULL,'1','1',curdate(),'"+ order.getNote() +"')";

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
}
