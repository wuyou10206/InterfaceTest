package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import conf.Constant;
import entity.Task;

public class TaskDao {
	public List<Task> findTaskAll() throws Exception{
		List<Task> tasks = new ArrayList<Task>();
		String sql = "SELECT ID,TASK_NAME,INTERFACE_IDS,USER_ID,ADD_TIME,PROJECT_ID,IS_DELETE FROM T_TASK WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Task task = new Task();
			task.setId(rs.getInt(1));
			task.setTaskName(rs.getString(2));
			task.setInterfaceIds(rs.getString(3));
			task.setUserId(rs.getInt(4));
			task.setAddTime(rs.getDate(5));
			task.setProjectId(rs.getInt(6));
			task.setIsDelete(rs.getInt(7));
			tasks.add(task);
		}
		return tasks;
	}
	public List<Task> findTaskByProject(int projectId) throws Exception{
		List<Task> tasks = new ArrayList<Task>();
		String sql = "SELECT ID,TASK_NAME,INTERFACE_IDS,USER_ID,ADD_TIME,PROJECT_ID,IS_DELETE FROM T_TASK WHERE PROJECT_ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, projectId);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Task task = new Task();
			task.setId(rs.getInt(1));
			task.setTaskName(rs.getString(2));
			task.setInterfaceIds(rs.getString(3));
			task.setUserId(rs.getInt(4));
			task.setAddTime(rs.getDate(5));
			task.setProjectId(rs.getInt(6));
			task.setIsDelete(rs.getInt(7));
			tasks.add(task);
		}
		return tasks;
	}
	public Task findTaskById(int id) throws Exception{
		String sql = "SELECT ID,TASK_NAME,INTERFACE_IDS,USER_ID,ADD_TIME,PROJECT_ID,IS_DELETE FROM T_TASK WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		Task task = null;
		while(rs.next()){
			task = new Task();
			task.setId(rs.getInt(1));
			task.setTaskName(rs.getString(2));
			task.setInterfaceIds(rs.getString(3));
			task.setUserId(rs.getInt(4));
			task.setAddTime(rs.getDate(5));
			task.setProjectId(rs.getInt(6));
			task.setIsDelete(rs.getInt(7));
		}
		return task;
	}
	public void addTask(Task task) throws Exception{
		String sql = "INSERT INTO T_TASK(TASK_NAME,INTERFACE_IDS,USER_ID,ADD_TIME,PROJECT_ID,IS_DELETE)VALUES(?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, task.getTaskName());
		ps.setString(2, task.getInterfaceIds());
		ps.setInt(3, task.getUserId());
		ps.setDate(4, task.getAddTime());
		ps.setInt(5, task.getProjectId());
		ps.setInt(6, task.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyTask(Task task) throws Exception{
		String sql = "UPDATE T_TASK SET TASK_NAME=?,INTERFACE_IDS=?,USER_ID=?,ADD_TIME=?,PROJECT_ID=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, task.getTaskName());
		ps.setString(2, task.getInterfaceIds());
		ps.setInt(3, task.getUserId());
		ps.setDate(4, task.getAddTime());
		ps.setInt(5, task.getProjectId());
		ps.setInt(6, task.getIsDelete());
		ps.setInt(7, task.getId());
		ps.executeUpdate();
	}
	public void deleteTask(int id) throws Exception{
		String sql = "DELETE FROM T_TASK WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
