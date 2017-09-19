package util;

import java.sql.Connection;

/**
 * 事务管理器
 * @author Administrator
 *
 */
public class TransactionManager {
	public static void beginTransaction() throws Exception{
		Connection connection = DBUtils_Mysql.getConnection();
		connection.setAutoCommit(false);
	}
	public static void commit() throws Exception{
		Connection connection = DBUtils_Mysql.getConnection();
		connection.commit();
		DBUtils_Mysql.close();
	}
	public static void rollback() throws Exception{
		Connection connection = DBUtils_Mysql.getConnection();
		connection.rollback();
		DBUtils_Mysql.close();
	}
}
