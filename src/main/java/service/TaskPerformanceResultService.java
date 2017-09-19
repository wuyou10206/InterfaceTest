package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.TaskPerformanceResultDao;
import entity.TaskPerformanceResult;

public class TaskPerformanceResultService {
	private TaskPerformanceResultDao taskPerformanceResultDao = new TaskPerformanceResultDao();
	public List<TaskPerformanceResult> findTaskPerformanceResult() throws Exception{
		List<TaskPerformanceResult> taskPerformanceResults = taskPerformanceResultDao.findTaskPerformanceResultAll();
		DBUtils_Mysql.close();
		return taskPerformanceResults;
	}
	public List<TaskPerformanceResult> findTaskPerformanceResultByTaskPerformanceResultIds(List<Integer> taskPerformanceResultIds) throws Exception{
		List<TaskPerformanceResult> taskPerformanceResults = new ArrayList<TaskPerformanceResult>();
		for (Integer id:taskPerformanceResultIds) {
			TaskPerformanceResult taskPerformanceResult = findTaskPerformanceResultByID(id);
			taskPerformanceResults.add(taskPerformanceResult);
		}
		return taskPerformanceResults;
	}
	public List<TaskPerformanceResult> findTaskPerformanceResultByTaskPerformanceResultIds(String taskPerformanceResultIds) throws Exception{
		List<TaskPerformanceResult> results = new ArrayList<TaskPerformanceResult>();
		String[] result_ids = taskPerformanceResultIds.split(",");
		for(String s:result_ids){
			int id = Integer.parseInt(s);
			TaskPerformanceResult result = findTaskPerformanceResultByID(id);
			results.add(result);
		}
		return results;
	}
	public List<TaskPerformanceResult> findTaskPerformanceResultByTask(int taskId) throws Exception{
		List<TaskPerformanceResult> taskPerformanceResults = taskPerformanceResultDao.findTaskPerformanceResultByTask(taskId);
		DBUtils_Mysql.close();
		return taskPerformanceResults;
	}
	public TaskPerformanceResult findTaskPerformanceResultByID(int id) throws Exception{
		TaskPerformanceResult taskPerformanceResult = taskPerformanceResultDao.findTaskPerformanceResultById(id);
		DBUtils_Mysql.close();
		return taskPerformanceResult;
	}
	public TaskPerformanceResult findTaskPerformanceResultByMaxDate(int taskId) throws Exception{
		TaskPerformanceResult taskPerformanceResult = taskPerformanceResultDao.findTaskPerformanceResultByMaxDate(taskId);
		DBUtils_Mysql.close();
		return taskPerformanceResult;
	}
	public int addTaskPerformanceResult(int taskId,String resultIds,int userId,Timestamp executeTime,int isDelete,int taskPass,long beginDate,long endDate,long useDate) throws Exception{
		int taskPerformanceResultId = 0;
		try {
			TransactionManager.beginTransaction();
			TaskPerformanceResult taskPerformanceResult = new TaskPerformanceResult();
			taskPerformanceResult.setTaskId(taskId);
			taskPerformanceResult.setResultIds(resultIds);
			taskPerformanceResult.setUserId(userId);
			taskPerformanceResult.setExecuteTime(executeTime);
			taskPerformanceResult.setIsDelete(isDelete);
			taskPerformanceResult.setTaskPass(taskPass);
			taskPerformanceResult.setBeginDate(beginDate);
			taskPerformanceResult.setEndDate(endDate);
			taskPerformanceResult.setUseDate(useDate);
			taskPerformanceResultId = taskPerformanceResultDao.addTaskPerformanceResult(taskPerformanceResult);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return taskPerformanceResultId;
	}
	
	public void deleteTaskPerformanceResult(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			taskPerformanceResultDao.deleteTaskPerformanceResult(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
