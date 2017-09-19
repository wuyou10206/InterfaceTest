package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils_Mysql {
	private static String driver;
	private static String url;
	private static String dbUser;
	private static String dbPwd;
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	static{
		Properties pro = new Properties();
		try {
			pro.load(
					DBUtils_Mysql.class.
					getResourceAsStream(
							"db_mysql.properties"));
			url = pro.getProperty("url");
			driver = pro.getProperty("driver");
			dbUser = pro.getProperty("dbUser");
			dbPwd = pro.getProperty("dbPwd");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection connection = connectionHolder.get();
		if(connection==null){
			connection = getConn();
			connectionHolder.set(connection);
		}
		return connection;
	}
	private static Connection getConn(){
		Connection connection = null;
		try {
			connection = 
				DriverManager.getConnection
				(url, dbUser, dbPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection; 
	}
	public static void close(){
		Connection connection = connectionHolder.get();
		connectionHolder.set(null);
		if(connection!=null){
			close(connection);
		}
	}
	private static void close(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

