package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.*;

public class OrderedItemDAO extends DbConnection {
	public ArrayList<OrderedItem> selectAll() {

		Connection con = null;
		Statement smt = null;

		// 検索した書籍情報を格納するArrayListオブジェクト
		ArrayList<OrderedItem> ordItemList = new ArrayList<OrderedItem>();

		try {

			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文作成
			String sql = "SELECT `product_info`.`product`,`order`.`count`,`product_info`.`price` FROM `product_info` INNER JOIN `order` ON `product_info`.`product_id`=`order`.`product_id`";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をArrayListに格納
			while (rs.next()) {
				OrderedItem ordItem = new OrderedItem();
				ordItem.setProduct(rs.getString("product"));
				ordItem.setAmount(rs.getInt("count"));
				ordItem.setPrice(rs.getInt("price"));
				ordItemList.add(ordItem);
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
		return ordItemList;
	}

}
