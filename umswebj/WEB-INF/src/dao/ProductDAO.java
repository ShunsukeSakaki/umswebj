/* プログラム名：ユニフォーム受注管理システム
 * プログラムの説明：ユニフォーム受注管理と商品購入が行えるプログラム
 * 					ゲストユーザーと管理者で使用可能な機能を分けている。
 * 					現在は商品の一覧機能のみを有している。
 * URL:http://localhost:8080/umswebj/view/login.jsp
 * 作成者：宗像朝日
 * 作成日：2023年6月22日
 */

package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.Order;
import bean.Product;

public class ProductDAO extends DbConnection {

	// 全商品データを取得するメソッド called UniformListServlet
	public ArrayList<Product> selectAll() {
		Connection con = null;
		Statement smt = null;

		// 商品データを格納するArrayList
		ArrayList<Product> list = new ArrayList<Product>();

		// SQL文
		String sql = "SELECT * FROM product_info ORDER BY product_id";

		try {
			con = getConnection();
			smt = con.createStatement();

			// 全商品データを検索
			ResultSet rs = smt.executeQuery(sql);

			// listに検索結果を格納
			while (rs.next()) {
				// 列データ格納用のオブジェクト
				Product product = new Product();

				product.setProductId(rs.getString("product_id"));
				product.setProduct(rs.getString("product"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));
				product.setImage_url(rs.getString("image_url"));

				// 列データをlistに追加
				list.add(product);

			}
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
		return list;
	}

	// 在庫数の更新
	public void update(int stock, int productId) {

		// 変数宣言
		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "UPDATE product_info SET stock ='" + stock + "' WHERE product_id ='" + productId + "'";

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


}
