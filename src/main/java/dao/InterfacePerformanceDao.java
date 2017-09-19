package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.InterfacePerformance;

public class InterfacePerformanceDao {
	public List<InterfacePerformance> findInterfacePerformanceAll() throws Exception{
		List<InterfacePerformance> ips = new ArrayList<InterfacePerformance>();
		String sql = "SELECT ID,INTERFACE_ID,RESULT_PERFORMANCE_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_INTERFACE_PERFORMANCE";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			InterfacePerformance ip = new InterfacePerformance();
			ip.setId(rs.getInt(1));
			ip.setInterfaceId(rs.getInt(2));
			ip.setResultPerformanceIds(rs.getString(3));
			ip.setThreadNumber(rs.getInt(4));
			ip.setTotalTime(rs.getLong(5));
			ip.setAvgTime(rs.getLong(6));
			ip.setTime5(rs.getLong(7));
			ip.setTime9(rs.getLong(8));
			ip.setMinTime(rs.getLong(9));
			ip.setMaxTime(rs.getLong(10));
			ip.setTps(rs.getDouble(11));
			ip.setThroughput(rs.getDouble(12));
			ip.setUserId(rs.getInt(13));
			ip.setAddTime(rs.getTimestamp(14));
			ip.setIsDelete(rs.getInt(15));
			ips.add(ip);
		}
		return ips;
	}
	
	public List<InterfacePerformance> findInterfacePerformanceByInterface(int interfaceId) throws Exception{
		List<InterfacePerformance> ips = new ArrayList<InterfacePerformance>();
		String sql = "SELECT ID,INTERFACE_ID,RESULT_PERFORMANCE_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_INTERFACE_PERFORMANCE WHERE INTERFACE_ID=? ORDER BY ADD_TIME DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			InterfacePerformance ip = new InterfacePerformance();
			ip.setId(rs.getInt(1));
			ip.setInterfaceId(rs.getInt(2));
			ip.setResultPerformanceIds(rs.getString(3));
			ip.setThreadNumber(rs.getInt(4));
			ip.setTotalTime(rs.getLong(5));
			ip.setAvgTime(rs.getLong(6));
			ip.setTime5(rs.getLong(7));
			ip.setTime9(rs.getLong(8));
			ip.setMinTime(rs.getLong(9));
			ip.setMaxTime(rs.getLong(10));
			ip.setTps(rs.getDouble(11));
			ip.setThroughput(rs.getDouble(12));
			ip.setUserId(rs.getInt(13));
			ip.setAddTime(rs.getTimestamp(14));
			ip.setIsDelete(rs.getInt(15));
			ips.add(ip);
		}
		return ips;
	}
	public InterfacePerformance findInterfacePerformanceByMaxDate(int interfaceId) throws Exception{
		InterfacePerformance ip = null;
		String sql = "SELECT ID,INTERFACE_ID,RESULT_PERFORMANCE_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_INTERFACE_PERFORMANCE WHERE INTERFACE_ID=? ORDER BY ADD_TIME DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ip = new InterfacePerformance();
			ip.setId(rs.getInt(1));
			ip.setInterfaceId(rs.getInt(2));
			ip.setResultPerformanceIds(rs.getString(3));
			ip.setThreadNumber(rs.getInt(4));
			ip.setTotalTime(rs.getLong(5));
			ip.setAvgTime(rs.getLong(6));
			ip.setTime5(rs.getLong(7));
			ip.setTime9(rs.getLong(8));
			ip.setMinTime(rs.getLong(9));
			ip.setMaxTime(rs.getLong(10));
			ip.setTps(rs.getDouble(11));
			ip.setThroughput(rs.getDouble(12));
			ip.setUserId(rs.getInt(13));
			ip.setAddTime(rs.getTimestamp(14));
			ip.setIsDelete(rs.getInt(15));
		}
		return ip;
	}
	public InterfacePerformance findInterfacePerformanceById(int id) throws Exception{
		InterfacePerformance ip = null;
		String sql = "SELECT ID,INTERFACE_ID,RESULT_PERFORMANCE_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_INTERFACE_PERFORMANCE WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ip = new InterfacePerformance();
			ip.setId(rs.getInt(1));
			ip.setInterfaceId(rs.getInt(2));
			ip.setResultPerformanceIds(rs.getString(3));
			ip.setThreadNumber(rs.getInt(4));
			ip.setTotalTime(rs.getLong(5));
			ip.setAvgTime(rs.getLong(6));
			ip.setTime5(rs.getLong(7));
			ip.setTime9(rs.getLong(8));
			ip.setMinTime(rs.getLong(9));
			ip.setMaxTime(rs.getLong(10));
			ip.setTps(rs.getDouble(11));
			ip.setThroughput(rs.getDouble(12));
			ip.setUserId(rs.getInt(13));
			ip.setAddTime(rs.getTimestamp(14));
			ip.setIsDelete(rs.getInt(15));
		}
		return ip;
	}
	public int addInterfacePerformance(InterfacePerformance ip) throws Exception{
		String sql = "INSERT INTO T_INTERFACE_PERFORMANCE(INTERFACE_ID,RESULT_PERFORMANCE_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, ip.getInterfaceId());
		ps.setString(2, ip.getResultPerformanceIds());
		ps.setInt(3, ip.getThreadNumber());
		ps.setLong(4, ip.getTotalTime());
		ps.setLong(5, ip.getAvgTime());
		ps.setLong(6, ip.getTime5());
		ps.setLong(7, ip.getTime9());
		ps.setLong(8, ip.getMinTime());
		ps.setLong(9, ip.getMaxTime());
		ps.setDouble(10, ip.getTps());
		ps.setDouble(11, ip.getThroughput());
		ps.setInt(12,ip.getUserId());
		ps.setTimestamp(13, ip.getAddTime());
		ps.setInt(14, ip.getIsDelete());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int interfacePerformanceId = 0;
		if(rs.next()){
			interfacePerformanceId = rs.getInt(1);
		}
		return interfacePerformanceId;
	}
	public void modifyInterfacePerformance(InterfacePerformance ip) throws Exception{
		String sql = "UPDATE T_INTERFACE_PERFORMANCE SET INTERFACE_ID=?,RESULT_PERFORMANCE_IDS=?,THREAD_NUMBER=?,TOTAL_TIME=?,AVG_TIME=?,TIME_5=?,TIME_9=?,MIN_TIME=?,MAX_TIME=?,TPS=?,THROUGHPUT=?,USER_ID=?,ADD_TIME=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, ip.getInterfaceId());
		ps.setString(2, ip.getResultPerformanceIds());
		ps.setInt(3, ip.getThreadNumber());
		ps.setLong(4, ip.getTotalTime());
		ps.setLong(5, ip.getAvgTime());
		ps.setLong(6, ip.getTime5());
		ps.setLong(7, ip.getTime9());
		ps.setLong(8, ip.getMinTime());
		ps.setLong(9, ip.getMaxTime());
		ps.setDouble(10, ip.getTps());
		ps.setDouble(11, ip.getThroughput());
		ps.setInt(12,ip.getUserId());
		ps.setTimestamp(13, ip.getAddTime());
		ps.setInt(14, ip.getIsDelete());
		ps.setInt(15, ip.getId());
		ps.executeUpdate();
	}
	public void deleteInterfacePerformance(int id) throws Exception{
		String sql = "DELETE FROM T_INTERFACE_PERFORMANCE WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
