package service;

import java.sql.Timestamp;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.TaskResultDao;
import entity.TaskResult;

public class TaskResultService {
	private TaskResultDao taskResultDao = new TaskResultDao();
	public List<TaskResult> findTaskResult() throws Exception{
		List<TaskResult> taskResults = taskResultDao.findTaskResultAll();
		DBUtils_Mysql.close();
		return taskResults;
	}
	public List<TaskResult> findTaskResultByTask(int taskId) throws Exception{
		List<TaskResult> taskResults = taskResultDao.findTaskResultByTask(taskId);
		DBUtils_Mysql.close();
		return taskResults;
	}
	public TaskResult findTaskResultByID(int id) throws Exception{
		TaskResult taskResult = taskResultDao.findTaskResultById(id);
		DBUtils_Mysql.close();
		return taskResult;
	}
	public TaskResult findTaskResultByMaxDate(int taskId) throws Exception{
		TaskResult taskResult = taskResultDao.findTaskResultByMaxDate(taskId);
		DBUtils_Mysql.close();
		return taskResult;
	}
	public void addTaskResult(int taskId,String resultIds,int userId,Timestamp executeTime,int isDelete,int taskPass) throws Exception{
		try {
			TransactionManager.beginTransaction();
			TaskResult taskResult = new TaskResult();
			taskResult.setTaskId(taskId);
			taskResult.setResultIds(resultIds);
			taskResult.setUserId(userId);
			taskResult.setExecuteTime(executeTime);
			taskResult.setIsDelete(isDelete);
			taskResult.setTaskPass(taskPass);
			taskResultDao.addTaskResult(taskResult);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	public void deleteTaskResult(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			taskResultDao.deleteTaskResult(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
}
