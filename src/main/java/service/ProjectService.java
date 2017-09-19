package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import conf.Constant;
import dao.ProjectDao;
import entity.Project;

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();
	public List<Project> findProject() throws Exception{
		List<Project> Projects = projectDao.findProjectAll();
		DBUtils_Mysql.close();
		return Projects;
	}
	public List<Project> findProjectByUser(int userId) throws Exception{
		List<Project> Projects = projectDao.findProjectByUser(userId);
		DBUtils_Mysql.close();
		return Projects;
	}
	public Project findProjectByID(int id) throws Exception{
		Project Project = projectDao.findProjectById(id);
		DBUtils_Mysql.close();
		return Project;
	}
	public void addProject(String name,int userId,Date addTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Project project = new Project();
			project.setProjectName(name);
			project.setUserId(userId);
			project.setAddTime(addTime);
			project.setIsDelete(isDelete);
			projectDao.addProject(project);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteProjectByDelete(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Project project = projectDao.findProjectById(id);
			project.setIsDelete(Constant.DELETE);
			projectDao.modifyProject(project);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteProject(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			projectDao.deleteProject(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyProject(int id,String name,int userId,Date addTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Project project = new Project();
			project.setId(id);
			project.setProjectName(name);
			project.setUserId(userId);
			project.setAddTime(addTime);
			project.setIsDelete(isDelete);
			projectDao.modifyProject(project);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public boolean isCanAddProject(String projectName) throws Exception{
		List<Project> projects = findProject();
		for (Project project : projects) {
			if(project.getProjectName().equals(projectName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
}
