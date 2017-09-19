package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import conf.Constant;
import entity.TaskPerformanceResult;

public class TaskPerformanceResultDao {
	public List<TaskPerformanceResult> findTaskPerformanceResultAll() throws Exception{
		List<TaskPerformanceResult> taskPerformanceResults = new ArrayList<TaskPerformanceResult>();
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS,BEGIN_DATE,END_DATE,USE_DATE FROM T_TASK_PERFORMANCE_RESULT WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskPerformanceResult taskPerformanceResult = new TaskPerformanceResult();
			taskPerformanceResult.setId(rs.getInt(1));
			taskPerformanceResult.setTaskId(rs.getInt(2));
			taskPerformanceResult.setResultIds(rs.getString(3));
			taskPerformanceResult.setUserId(rs.getInt(4));
			taskPerformanceResult.setExecuteTime(rs.getTimestamp(5));
			taskPerformanceResult.setIsDelete(rs.getInt(6));
			taskPerformanceResult.setTaskPass(rs.getInt(7));
			taskPerformanceResult.setBeginDate(rs.getLong(8));
			taskPerformanceResult.setEndDate(rs.getLong(9));
			taskPerformanceResult.setUseDate(rs.getLong(10));
			taskPerformanceResults.add(taskPerformanceResult);
		}
		return taskPerformanceResults;
	}
	public List<TaskPerformanceResult> findTaskPerformanceResultByTask(int taskId) throws Exception{
		List<TaskPerformanceResult> taskPerformanceResults = new ArrayList<TaskPerformanceResult>();
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS,BEGIN_DATE,END_DATE,USE_DATE FROM T_TASK_PERFORMANCE_RESULT WHERE TASK_ID=? ORDER BY EXECUTE_TIME DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,taskId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskPerformanceResult taskPerformanceResult = new TaskPerformanceResult();
			taskPerformanceResult.setId(rs.getInt(1));
			taskPerformanceResult.setTaskId(rs.getInt(2));
			taskPerformanceResult.setResultIds(rs.getString(3));
			taskPerformanceResult.setUserId(rs.getInt(4));
			taskPerformanceResult.setExecuteTime(rs.getTimestamp(5));
			taskPerformanceResult.setIsDelete(rs.getInt(6));
			taskPerformanceResult.setTaskPass(rs.getInt(7));
			taskPerformanceResult.setBeginDate(rs.getLong(8));
			taskPerformanceResult.setEndDate(rs.getLong(9));
			taskPerformanceResult.setUseDate(rs.getLong(10));
			taskPerformanceResults.add(taskPerformanceResult);
		}
		return taskPerformanceResults;
	}
	public TaskPerformanceResult findTaskPerformanceResultByMaxDate(int taskId) throws Exception{
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS,BEGIN_DATE,END_DATE,USE_DATE FROM T_TASK_PERFORMANCE_RESULT WHERE TASK_ID=? ORDER BY EXECUTE_TIME DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,taskId);
		ResultSet rs = ps.executeQuery();
		TaskPerformanceResult taskPerformanceResult = null;
		while(rs.next()){
			taskPerformanceResult = new TaskPerformanceResult();
			taskPerformanceResult.setId(rs.getInt(1));
			taskPerformanceResult.setTaskId(rs.getInt(2));
			taskPerformanceResult.setResultIds(rs.getString(3));
			taskPerformanceResult.setUserId(rs.getInt(4));
			taskPerformanceResult.setExecuteTime(rs.getTimestamp(5));
			taskPerformanceResult.setIsDelete(rs.getInt(6));
			taskPerformanceResult.setTaskPass(rs.getInt(7));
			taskPerformanceResult.setBeginDate(rs.getLong(8));
			taskPerformanceResult.setEndDate(rs.getLong(9));
			taskPerformanceResult.setUseDate(rs.getLong(10));
		}
		return taskPerformanceResult;
	}
	public TaskPerformanceResult findTaskPerformanceResultById(int id) throws Exception{
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS,BEGIN_DATE,END_DATE,USE_DATE FROM T_TASK_PERFORMANCE_RESULT WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		TaskPerformanceResult taskPerformanceResult = null;
		while(rs.next()){
			taskPerformanceResult = new TaskPerformanceResult();
			taskPerformanceResult.setId(rs.getInt(1));
			taskPerformanceResult.setTaskId(rs.getInt(2));
			taskPerformanceResult.setResultIds(rs.getString(3));
			taskPerformanceResult.setUserId(rs.getInt(4));
			taskPerformanceResult.setExecuteTime(rs.getTimestamp(5));
			taskPerformanceResult.setIsDelete(rs.getInt(6));
			taskPerformanceResult.setTaskPass(rs.getInt(7));
			taskPerformanceResult.setBeginDate(rs.getLong(8));
			taskPerformanceResult.setEndDate(rs.getLong(9));
			taskPerformanceResult.setUseDate(rs.getLong(10));
		}
		return taskPerformanceResult;
	}
	public int addTaskPerformanceResult(TaskPerformanceResult taskPerformanceResult) throws Exception{
		String sql = "INSERT INTO T_TASK_PERFORMANCE_RESULT(TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS,BEGIN_DATE,END_DATE,USE_DATE)VALUES(?,?,?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, taskPerformanceResult.getTaskId());
		ps.setString(2, taskPerformanceResult.getResultIds());
		ps.setInt(3, taskPerformanceResult.getUserId());
		ps.setTimestamp(4, taskPerformanceResult.getExecuteTime());
		ps.setInt(5, taskPerformanceResult.getIsDelete());
		ps.setInt(6, taskPerformanceResult.getTaskPass());
		ps.setLong(7, taskPerformanceResult.getBeginDate());
		ps.setLong(8, taskPerformanceResult.getEndDate());
		ps.setLong(9, taskPerformanceResult.getUseDate());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int taskPerformanceResultId = 0;
		if(rs.next()){
			taskPerformanceResultId = rs.getInt(1);
		}
		return taskPerformanceResultId;
	}
	
	public void deleteTaskPerformanceResult(int id) throws Exception{
		String sql = "DELETE FROM T_TASK_PERFORMANCE_RESULT WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
