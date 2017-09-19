package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import conf.Constant;
import entity.TaskResult;

public class TaskResultDao {
	public List<TaskResult> findTaskResultAll() throws Exception{
		List<TaskResult> taskResults = new ArrayList<TaskResult>();
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS FROM T_TASK_RESULT WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskResult taskResult = new TaskResult();
			taskResult.setId(rs.getInt(1));
			taskResult.setTaskId(rs.getInt(2));
			taskResult.setResultIds(rs.getString(3));
			taskResult.setUserId(rs.getInt(4));
			taskResult.setExecuteTime(rs.getTimestamp(5));
			taskResult.setIsDelete(rs.getInt(6));
			taskResult.setTaskPass(rs.getInt(7));
			taskResults.add(taskResult);
		}
		return taskResults;
	}
	public List<TaskResult> findTaskResultByTask(int taskId) throws Exception{
		List<TaskResult> taskResults = new ArrayList<TaskResult>();
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS FROM T_TASK_RESULT WHERE TASK_ID=? ORDER BY EXECUTE_TIME DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,taskId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TaskResult taskResult = new TaskResult();
			taskResult.setId(rs.getInt(1));
			taskResult.setTaskId(rs.getInt(2));
			taskResult.setResultIds(rs.getString(3));
			taskResult.setUserId(rs.getInt(4));
			taskResult.setExecuteTime(rs.getTimestamp(5));
			taskResult.setIsDelete(rs.getInt(6));
			taskResult.setTaskPass(rs.getInt(7));
			taskResults.add(taskResult);
		}
		return taskResults;
	}
	public TaskResult findTaskResultByMaxDate(int taskId) throws Exception{
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS FROM T_TASK_RESULT WHERE TASK_ID=? ORDER BY EXECUTE_TIME DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,taskId);
		ResultSet rs = ps.executeQuery();
		TaskResult taskResult = null;
		while(rs.next()){
			taskResult = new TaskResult();
			taskResult.setId(rs.getInt(1));
			taskResult.setTaskId(rs.getInt(2));
			taskResult.setResultIds(rs.getString(3));
			taskResult.setUserId(rs.getInt(4));
			taskResult.setExecuteTime(rs.getTimestamp(5));
			taskResult.setIsDelete(rs.getInt(6));
			taskResult.setTaskPass(rs.getInt(7));
		}
		return taskResult;
	}
	public TaskResult findTaskResultById(int id) throws Exception{
		String sql = "SELECT ID,TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS FROM T_TASK_RESULT WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		TaskResult taskResult = null;
		while(rs.next()){
			taskResult = new TaskResult();
			taskResult.setId(rs.getInt(1));
			taskResult.setTaskId(rs.getInt(2));
			taskResult.setResultIds(rs.getString(3));
			taskResult.setUserId(rs.getInt(4));
			taskResult.setExecuteTime(rs.getTimestamp(5));
			taskResult.setIsDelete(rs.getInt(6));
			taskResult.setTaskPass(rs.getInt(7));
		}
		return taskResult;
	}
	public void addTaskResult(TaskResult taskResult) throws Exception{
		String sql = "INSERT INTO T_TASK_RESULT(TASK_ID,RESULT_IDS,USER_ID,EXECUTE_TIME,IS_DELETE,TASK_PASS)VALUES(?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, taskResult.getTaskId());
		ps.setString(2, taskResult.getResultIds());
		ps.setInt(3, taskResult.getUserId());
		ps.setTimestamp(4, taskResult.getExecuteTime());
		ps.setInt(5, taskResult.getIsDelete());
		ps.setInt(6, taskResult.getTaskPass());
		ps.executeUpdate();
	}
	
	public void deleteTaskResult(int id) throws Exception{
		String sql = "DELETE FROM T_TASK_RESULT WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
