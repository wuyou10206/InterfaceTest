package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.TaskPerformance;

public class TaskPerformanceDao {
	public List<TaskPerformance> findTaskPerformanceAll() throws Exception{
		List<TaskPerformance> tps = new ArrayList<TaskPerformance>();
		String sql = "SELECT ID,TASK_ID,TASK_PERFORMANCE_RESULT_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_TASK_PERFORMANCE";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskPerformance tp = new TaskPerformance();
			tp.setId(rs.getInt(1));
			tp.setTaskId(rs.getInt(2));
			tp.setTaskPerformanceResultIds(rs.getString(3));
			tp.setThreadNumber(rs.getInt(4));
			tp.setTotalTime(rs.getLong(5));
			tp.setAvgTime(rs.getLong(6));
			tp.setTime5(rs.getLong(7));
			tp.setTime9(rs.getLong(8));
			tp.setMinTime(rs.getLong(9));
			tp.setMaxTime(rs.getLong(10));
			tp.setTps(rs.getDouble(11));
			tp.setThroughput(rs.getDouble(12));
			tp.setUserId(rs.getInt(13));
			tp.setAddTime(rs.getTimestamp(14));
			tp.setIsDelete(rs.getInt(15));
			tps.add(tp);
		}
		return tps;
	}
	
	public List<TaskPerformance> findTaskPerformanceByTask(int taskId) throws Exception{
		List<TaskPerformance> tps = new ArrayList<TaskPerformance>();
		String sql = "SELECT ID,TASK_ID,TASK_PERFORMANCE_RESULT_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_TASK_PERFORMANCE WHERE TASK_ID=? ORDER BY ADD_TIME DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, taskId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskPerformance tp = new TaskPerformance();
			tp.setId(rs.getInt(1));
			tp.setTaskId(rs.getInt(2));
			tp.setTaskPerformanceResultIds(rs.getString(3));
			tp.setThreadNumber(rs.getInt(4));
			tp.setTotalTime(rs.getLong(5));
			tp.setAvgTime(rs.getLong(6));
			tp.setTime5(rs.getLong(7));
			tp.setTime9(rs.getLong(8));
			tp.setMinTime(rs.getLong(9));
			tp.setMaxTime(rs.getLong(10));
			tp.setTps(rs.getDouble(11));
			tp.setThroughput(rs.getDouble(12));
			tp.setUserId(rs.getInt(13));
			tp.setAddTime(rs.getTimestamp(14));
			tp.setIsDelete(rs.getInt(15));
			tps.add(tp);
		}
		return tps;
	}
	public TaskPerformance findTaskPerformanceByMaxDate(int taskId) throws Exception{
		TaskPerformance tp = null;
		String sql = "SELECT ID,TASK_ID,TASK_PERFORMANCE_RESULT_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_TASK_PERFORMANCE WHERE TASK_ID=? ORDER BY ADD_TIME DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, taskId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tp = new TaskPerformance();
			tp.setId(rs.getInt(1));
			tp.setTaskId(rs.getInt(2));
			tp.setTaskPerformanceResultIds(rs.getString(3));
			tp.setThreadNumber(rs.getInt(4));
			tp.setTotalTime(rs.getLong(5));
			tp.setAvgTime(rs.getLong(6));
			tp.setTime5(rs.getLong(7));
			tp.setTime9(rs.getLong(8));
			tp.setMinTime(rs.getLong(9));
			tp.setMaxTime(rs.getLong(10));
			tp.setTps(rs.getDouble(11));
			tp.setThroughput(rs.getDouble(12));
			tp.setUserId(rs.getInt(13));
			tp.setAddTime(rs.getTimestamp(14));
			tp.setIsDelete(rs.getInt(15));
		}
		return tp;
	}
	public TaskPerformance findTaskPerformanceById(int id) throws Exception{
		TaskPerformance tp = null;
		String sql = "SELECT ID,TASK_ID,TASK_PERFORMANCE_RESULT_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE FROM T_TASK_PERFORMANCE WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tp = new TaskPerformance();
			tp.setId(rs.getInt(1));
			tp.setTaskId(rs.getInt(2));
			tp.setTaskPerformanceResultIds(rs.getString(3));
			tp.setThreadNumber(rs.getInt(4));
			tp.setTotalTime(rs.getLong(5));
			tp.setAvgTime(rs.getLong(6));
			tp.setTime5(rs.getLong(7));
			tp.setTime9(rs.getLong(8));
			tp.setMinTime(rs.getLong(9));
			tp.setMaxTime(rs.getLong(10));
			tp.setTps(rs.getDouble(11));
			tp.setThroughput(rs.getDouble(12));
			tp.setUserId(rs.getInt(13));
			tp.setAddTime(rs.getTimestamp(14));
			tp.setIsDelete(rs.getInt(15));
		}
		return tp;
	}
	public int addTaskPerformance(TaskPerformance tp) throws Exception{
		String sql = "INSERT INTO T_TASK_PERFORMANCE(TASK_ID,TASK_PERFORMANCE_RESULT_IDS,THREAD_NUMBER,TOTAL_TIME,AVG_TIME,TIME_5,TIME_9,MIN_TIME,MAX_TIME,TPS,THROUGHPUT,USER_ID,ADD_TIME,IS_DELETE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, tp.getTaskId());
		ps.setString(2, tp.getTaskPerformanceResultIds());
		ps.setInt(3, tp.getThreadNumber());
		ps.setLong(4, tp.getTotalTime());
		ps.setLong(5, tp.getAvgTime());
		ps.setLong(6, tp.getTime5());
		ps.setLong(7, tp.getTime9());
		ps.setLong(8, tp.getMinTime());
		ps.setLong(9, tp.getMaxTime());
		ps.setDouble(10, tp.getTps());
		ps.setDouble(11, tp.getThroughput());
		ps.setInt(12,tp.getUserId());
		ps.setTimestamp(13, tp.getAddTime());
		ps.setInt(14, tp.getIsDelete());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int taskPerformanceId = 0;
		if(rs.next()){
			taskPerformanceId = rs.getInt(1);
		}
		return taskPerformanceId;
	}
	public void modifyTaskPerformance(TaskPerformance tp) throws Exception{
		String sql = "UPDATE T_TASK_PERFORMANCE SET TASK_ID=?,TASK_PERFORMANCE_RESULT_IDS=?,THREAD_NUMBER=?,TOTAL_TIME=?,AVG_TIME=?,TIME_5=?,TIME_9=?,MIN_TIME=?,MAX_TIME=?,TPS=?,THROUGHPUT=?,USER_ID=?,ADD_TIME=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, tp.getTaskId());
		ps.setString(2, tp.getTaskPerformanceResultIds());
		ps.setInt(3, tp.getThreadNumber());
		ps.setLong(4, tp.getTotalTime());
		ps.setLong(5, tp.getAvgTime());
		ps.setLong(6, tp.getTime5());
		ps.setLong(7, tp.getTime9());
		ps.setLong(8, tp.getMinTime());
		ps.setLong(9, tp.getMaxTime());
		ps.setDouble(10, tp.getTps());
		ps.setDouble(11, tp.getThroughput());
		ps.setInt(12,tp.getUserId());
		ps.setTimestamp(13, tp.getAddTime());
		ps.setInt(14, tp.getIsDelete());
		ps.setInt(15, tp.getId());
		ps.executeUpdate();
	}
	public void deleteTaskPerformance(int id) throws Exception{
		String sql = "DELETE FROM T_TASK_PERFORMANCE WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
