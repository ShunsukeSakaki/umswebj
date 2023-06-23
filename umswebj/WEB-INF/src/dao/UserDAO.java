package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import bean.User;

public class UserDAO extends DbConnection {

	// useridからユーザー情報を取得するメソッド
	public User selectByUser(int userid) {
		Connection con = null;
		Statement smt = null;

		// ユーザー情報を格納するオブジェクト
		User user = new User();

		// sql文
		String sql = "SELECT * FROM user WHERE user_id =" + userid;

		try {
			con = getConnection();
			smt = con.createStatement();

			// sql文発行
			ResultSet rs = smt.executeQuery(sql);

			// ユーザー情報をuserに格納
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setMail(rs.getString("mail"));
				user.setAuthority(rs.getString("authority"));
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
		return user;
	}

	// useridとpasswordからユーザー情報を取得するメソッド
	public User selectByUser(int userid, String password) {
		Connection con = null;
		Statement smt = null;

		// ユーザー情報を格納するオブジェクト
		User user = new User();

		// sql文
		String sql = "SELECT * FROM user WHERE user_id =" + userid + " AND password='" + password + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			// sql文発行
			ResultSet rs = smt.executeQuery(sql);

			// ユーザー情報をuserに格納
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setMail(rs.getString("mail"));
				user.setAuthority(rs.getString("authority"));
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
		return user;
	}

	// ユーザー情報を登録するメソッド
	public void insert(String name, String address, String mail) {

		Connection con = null;
		Statement smt = null;

		// ユーザー情報を格納するオブジェクト
		User user = new User();

		// SQL文
		String sql = "INSERT INTO user VALUES(NULL,'" + name + "','','" + address + "','" + mail + "','2')";
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

	// useridからユーザー情報を取得するメソッド
	public User selectByEmail(String email) {
		Connection con = null;
		Statement smt = null;

		// ユーザー情報を格納するオブジェクト
		User user = new User();

		// sql文
		String sql = "SELECT * FROM user WHERE mail = '" + email + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			// sql文発行
			ResultSet rs = smt.executeQuery(sql);

			// ユーザー情報をuserに格納
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setMail(rs.getString("mail"));
				user.setAuthority(rs.getString("authority"));
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
		return user;
	}

	public void delete(String email) {

		Connection con = null;
		Statement smt = null;

		try {
			// 削除用のSQL文を文字列として定義
			String sql = "DELETE FROM user WHERE mail = '" + email + "'";

			// Connectionオブジェクトを生成
			con = getConnection();
			// Statementオブジェクトを生成
			smt = con.createStatement();

			// SQL文を発行し書籍データを削除
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);

		} finally {
			if (smt != null) {
				try {
					smt.close(); // Statementオブジェクトをクローズ
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close(); // Connectionオブジェクトをクローズ
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
