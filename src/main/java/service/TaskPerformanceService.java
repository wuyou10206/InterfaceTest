package service;

import java.sql.Timestamp;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.TaskPerformanceDao;
import entity.TaskPerformance;

public class TaskPerformanceService {
	private TaskPerformanceDao taskPerformanceDao = new TaskPerformanceDao();
	public List<TaskPerformance> findTaskPerformance() throws Exception{
		List<TaskPerformance> tps = taskPerformanceDao.findTaskPerformanceAll();
		DBUtils_Mysql.close();
		return tps;
	}
	
	public List<TaskPerformance> findTaskPerformanceByTask(int taskId) throws Exception{
		List<TaskPerformance> tps = taskPerformanceDao.findTaskPerformanceByTask(taskId);
		DBUtils_Mysql.close();
		return tps;
	}
	public TaskPerformance findTaskPerformanceById(int id) throws Exception{
		TaskPerformance tp = taskPerformanceDao.findTaskPerformanceById(id);
		DBUtils_Mysql.close();
		return tp;
	}
	public TaskPerformance findTaskPerformanceByMaxDate(int taskId) throws Exception{
		TaskPerformance tp = taskPerformanceDao.findTaskPerformanceByMaxDate(taskId);
		DBUtils_Mysql.close();
		return tp;
	}
	public int addTaskPerformance(int taskId,String taskPerformanceResultIds,int threadNumber,long totalTime,long avgTime,long time5,long time9,long minTime,long maxTime,double tps,double throughput,int userId,Timestamp addTime,int isDelete) throws Exception{
		int taskPerformanceId = 0;
		try {
			TransactionManager.beginTransaction();
			TaskPerformance tp = new TaskPerformance();
			tp.setTaskId(taskId);
			tp.setTaskPerformanceResultIds(taskPerformanceResultIds);
			tp.setThreadNumber(threadNumber);
			tp.setTotalTime(totalTime);
			tp.setAvgTime(avgTime);
			tp.setTime5(time5);
			tp.setTime9(time9);
			tp.setMinTime(minTime);
			tp.setMaxTime(maxTime);
			tp.setTps(tps);
			tp.setThroughput(throughput);
			tp.setUserId(userId);
			tp.setAddTime(addTime);
			tp.setIsDelete(isDelete);
			taskPerformanceId = taskPerformanceDao.addTaskPerformance(tp);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return taskPerformanceId;
	}
	public void deleteTaskPerformance(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			taskPerformanceDao.deleteTaskPerformance(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyTaskPerformance(int id,int taskId,String taskPerformanceResultIds,int threadNumber,long totalTime,long avgTime,long time5,long time9,long minTime,long maxTime,double tps,double throughput,int userId,Timestamp addTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			TaskPerformance tp = new TaskPerformance();
			tp.setId(id);
			tp.setTaskId(taskId);
			tp.setTaskPerformanceResultIds(taskPerformanceResultIds);
			tp.setThreadNumber(threadNumber);
			tp.setTotalTime(totalTime);
			tp.setAvgTime(avgTime);
			tp.setTime5(time5);
			tp.setTime9(time9);
			tp.setMinTime(minTime);
			tp.setMaxTime(maxTime);
			tp.setTps(tps);
			tp.setThroughput(throughput);
			tp.setUserId(userId);
			tp.setAddTime(addTime);
			tp.setIsDelete(isDelete);
			taskPerformanceDao.modifyTaskPerformance(tp);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
