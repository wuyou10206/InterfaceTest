package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import conf.Constant;
import entity.TimerTask;

public class TimerTaskDao {
	public List<TimerTask> findTimerTaskAll() throws Exception{
		List<TimerTask> timerTasks = new ArrayList<TimerTask>();
		String sql = "SELECT ID,TASK_ID,EXECUTE_TIME,LOOP_TIME,USER_ID,CREATE_TIME,IS_DELETE FROM T_TIMER_TASK WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TimerTask timerTask = new TimerTask();
			timerTask.setId(rs.getInt(1));
			timerTask.setTaskId(rs.getInt(2));
			timerTask.setExecuteTime(rs.getTimestamp(3));
			timerTask.setLoopTime(rs.getLong(4));
			timerTask.setUserId(rs.getInt(5));
			timerTask.setCreateTime(rs.getTimestamp(6));
			timerTask.setIsDelete(rs.getInt(7));
			timerTasks.add(timerTask);
		}
		return timerTasks;
	}
	public List<TimerTask> findTimerTaskByTask(int taskId) throws Exception{
		List<TimerTask> timerTasks = new ArrayList<TimerTask>();
		String sql = "SELECT ID,TASK_ID,EXECUTE_TIME,LOOP_TIME,USER_ID,CREATE_TIME,IS_DELETE FROM T_TIMER_TASK WHERE TASK_ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,taskId);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TimerTask timerTask = new TimerTask();
			timerTask.setId(rs.getInt(1));
			timerTask.setTaskId(rs.getInt(2));
			timerTask.setExecuteTime(rs.getTimestamp(3));
			timerTask.setLoopTime(rs.getLong(4));
			timerTask.setUserId(rs.getInt(5));
			timerTask.setCreateTime(rs.getTimestamp(6));
			timerTask.setIsDelete(rs.getInt(7));
			timerTasks.add(timerTask);
		}
		return timerTasks;
	}
	
	public TimerTask findTimerTaskById(int id) throws Exception{
		String sql = "SELECT ID,TASK_ID,EXECUTE_TIME,LOOP_TIME,USER_ID,CREATE_TIME,IS_DELETE FROM T_TIMER_TASK WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		TimerTask timerTask = null;
		while(rs.next()){
			timerTask = new TimerTask();
			timerTask.setId(rs.getInt(1));
			timerTask.setTaskId(rs.getInt(2));
			timerTask.setExecuteTime(rs.getTimestamp(3));
			timerTask.setLoopTime(rs.getLong(4));
			timerTask.setUserId(rs.getInt(5));
			timerTask.setCreateTime(rs.getTimestamp(6));
			timerTask.setIsDelete(rs.getInt(7));
		}
		return timerTask;
	}
	public void modifyTimerTask(TimerTask timerTask) throws Exception{
		String sql = "UPDATE T_TIMER_TASK SET TASK_ID=?,EXECUTE_TIME=?,LOOP_TIME=?,USER_ID=?,CREATE_TIME=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, timerTask.getTaskId());
		ps.setTimestamp(2,timerTask.getExecuteTime());
		ps.setLong(3, timerTask.getLoopTime());
		ps.setInt(4, timerTask.getUserId());
		ps.setTimestamp(5, timerTask.getCreateTime());
		ps.setInt(6, timerTask.getIsDelete());
		ps.setInt(7, timerTask.getId());
		ps.executeUpdate();
	}
	public void addTimerTask(TimerTask timerTask) throws Exception{
		String sql = "INSERT INTO T_TIMER_TASK(TASK_ID,EXECUTE_TIME,LOOP_TIME,USER_ID,CREATE_TIME,IS_DELETE)VALUES(?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, timerTask.getTaskId());
		ps.setTimestamp(2,timerTask.getExecuteTime());
		ps.setLong(3, timerTask.getLoopTime());
		ps.setInt(4, timerTask.getUserId());
		ps.setTimestamp(5, timerTask.getCreateTime());
		ps.setInt(6, timerTask.getIsDelete());
		ps.executeUpdate();
	}
	
	public void deleteTimerTask(int id) throws Exception{
		String sql = "DELETE FROM T_TIMER_TASK WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
