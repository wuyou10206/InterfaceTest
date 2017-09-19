package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import conf.Constant;
import dao.TaskDao;
import entity.Task;

public class TaskService {
	private TaskDao taskDao = new TaskDao();
	public List<Task> findTask() throws Exception{
		List<Task> tasks = taskDao.findTaskAll();
		DBUtils_Mysql.close();
		return tasks;
	}
	public List<Task> findTaskByProject(int projectId) throws Exception{
		List<Task> tasks = taskDao.findTaskByProject(projectId);
		DBUtils_Mysql.close();
		return tasks;
	}
	public Task findTaskByID(int id) throws Exception{
		Task task = taskDao.findTaskById(id);
		DBUtils_Mysql.close();
		return task;
	}
	public void addTask(String name,String interfaceIds,int userId,Date addTime,int projectId,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Task task = new Task();
			task.setTaskName(name);
			task.setInterfaceIds(interfaceIds);
			task.setUserId(userId);
			task.setAddTime(addTime);
			task.setProjectId(projectId);
			task.setIsDelete(isDelete);
			taskDao.addTask(task);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteTaskByDelete(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Task task = taskDao.findTaskById(id);
			task.setIsDelete(Constant.DELETE);
			taskDao.modifyTask(task);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteTask(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			taskDao.deleteTask(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyTask(int id,String interfaceIds) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Task task = taskDao.findTaskById(id);
			task.setInterfaceIds(interfaceIds);
			taskDao.modifyTask(task);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyTask(int id,String name,String interfaceIds,int userId,Date addTime,int projectId,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Task task = new Task();
			task.setId(id);
			task.setTaskName(name);
			task.setInterfaceIds(interfaceIds);
			task.setUserId(userId);
			task.setAddTime(addTime);
			task.setProjectId(projectId);
			task.setIsDelete(isDelete);
			taskDao.modifyTask(task);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public boolean isCanAddTask(String taskName,int projectId) throws Exception{
		List<Task> tasks = findTaskByProject(projectId);
		for (Task task : tasks) {
			if(task.getTaskName().equals(taskName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
	public boolean isCanAddTask(String taskName,int taskId,int projectId) throws Exception{
		List<Task> tasks = findTaskByProject(projectId);
		for (Task task : tasks) {
			if(task.getId()!=taskId&&task.getTaskName().equals(taskName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
	public boolean isCanAddTask(String taskName) throws Exception{
		List<Task> tasks = findTask();
		for (Task task : tasks) {
			if(task.getTaskName().equals(taskName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
}
