package cn.com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * 数据库操作工具类
 * @author XuanZP
 *
 */
public class DBUtils {
	public static Connection conn;
	/***
	 * 获取连接
	 * @return
	 */
	public static Connection getConn() {
		try {
			if ((conn == null) || (conn.isClosed())) {
				String url = Const.jdbc_url;
				String user = Const.jdbc_user;
				String password = Const.jdbc_password; 
				Class.forName(Const.jdbc_driver);
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/***
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		try {
			if ((conn != null) && (!conn.isClosed()))
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/***
	 * 关闭 Statement
	 * @param ps
	 */
	public static void closeStatement(Statement ps) {
		try {
			if ((ps != null) && (!ps.isClosed()))
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/***
	 * 关闭数据�?
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		try {
			if ((rs != null) && (!rs.isClosed()))
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
