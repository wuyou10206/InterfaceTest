package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import conf.Constant;
import entity.Project;

public class ProjectDao {
	public List<Project> findProjectAll() throws Exception{
		List<Project> projects = new ArrayList<Project>();
		String sql = "SELECT ID,PROJECT_NAME,USER_ID,ADD_TIME,IS_DELETE FROM T_PROJECT WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Project project = new Project();
			project.setId(rs.getInt(1));
			project.setProjectName(rs.getString(2));
			project.setUserId(rs.getInt(3));
			project.setAddTime(rs.getDate(4));
			project.setIsDelete(rs.getInt(5));
			projects.add(project);
		}
		return projects;
	}
	public List<Project> findProjectByUser(int userId) throws Exception{
		List<Project> projects = new ArrayList<Project>();
		String sql = "SELECT ID,PROJECT_NAME,USER_ID,ADD_TIME,IS_DELETE FROM T_PROJECT WHERE IS_DELETE=? AND USER_ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ps.setInt(2, userId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Project project = new Project();
			project.setId(rs.getInt(1));
			project.setProjectName(rs.getString(2));
			project.setUserId(rs.getInt(3));
			project.setAddTime(rs.getDate(4));
			project.setIsDelete(rs.getInt(5));
			projects.add(project);
		}
		return projects;
	}
	public Project findProjectById(int id) throws Exception{
		String sql = "SELECT ID,PROJECT_NAME,USER_ID,ADD_TIME,IS_DELETE FROM T_PROJECT WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		Project project = null;
		while(rs.next()){
			project = new Project();
			project.setId(rs.getInt(1));
			project.setProjectName(rs.getString(2));
			project.setUserId(rs.getInt(3));
			project.setAddTime(rs.getDate(4));
			project.setIsDelete(rs.getInt(5));
		}
		return project;
	}
	public void addProject(Project project) throws Exception{
		String sql = "INSERT INTO T_PROJECT(PROJECT_NAME,USER_ID,ADD_TIME,IS_DELETE)VALUES(?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, project.getProjectName());
		ps.setInt(2, project.getUserId());
		ps.setDate(3, project.getAddTime());
		ps.setInt(4, project.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyProject(Project project) throws Exception{
		String sql = "UPDATE T_PROJECT SET Project_NAME=?,USER_ID=?,ADD_TIME=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, project.getProjectName());
		ps.setInt(2, project.getUserId());
		ps.setDate(3, project.getAddTime());
		ps.setInt(4, project.getIsDelete());
		ps.setInt(5, project.getId());
		ps.executeUpdate();
	}
	public void deleteProject(int id) throws Exception{
		String sql = "DELETE FROM T_PROJECT WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
