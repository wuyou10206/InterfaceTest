package service;

import java.sql.Timestamp;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import conf.Constant;
import dao.TimerTaskDao;
import entity.TimerTask;

public class TimerTaskService {
	private TimerTaskDao timerTaskDao = new TimerTaskDao();
	public List<TimerTask> findTimerTask() throws Exception{
		List<TimerTask> timerTasks = timerTaskDao.findTimerTaskAll();
		DBUtils_Mysql.close();
		return timerTasks;
	}
	public List<TimerTask> findTimerTaskByTask(int taskId) throws Exception{
		List<TimerTask> timerTasks = timerTaskDao.findTimerTaskByTask(taskId);
		DBUtils_Mysql.close();
		return timerTasks;
	}
	public TimerTask findTimerTaskByID(int id) throws Exception{
		TimerTask timerTask = timerTaskDao.findTimerTaskById(id);
		DBUtils_Mysql.close();
		return timerTask;
	}
	
	public void addTimerTask(int taskId,Timestamp executeTime,long loopTime,int userId,Timestamp createTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			TimerTask timerTask = new TimerTask();
			timerTask.setTaskId(taskId);
			timerTask.setExecuteTime(executeTime);
			timerTask.setLoopTime(loopTime);
			timerTask.setUserId(userId);
			timerTask.setCreateTime(createTime);
			timerTask.setIsDelete(isDelete);
			timerTaskDao.addTimerTask(timerTask);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyTimerTask(int id,int taskId,Timestamp executeTime,long loopTime,int userId,Timestamp createTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			TimerTask timerTask = new TimerTask();
			timerTask.setTaskId(taskId);
			timerTask.setExecuteTime(executeTime);
			timerTask.setLoopTime(loopTime);
			timerTask.setUserId(userId);
			timerTask.setCreateTime(createTime);
			timerTask.setIsDelete(isDelete);
			timerTaskDao.modifyTimerTask(timerTask);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteTimerTaskByTaskByDelete(int taskId) throws Exception{
		try {
			TransactionManager.beginTransaction();
			List<TimerTask> timerTasks = timerTaskDao.findTimerTaskByTask(taskId);
			for (TimerTask timerTask : timerTasks) {
				timerTask.setIsDelete(Constant.DELETE);
				timerTaskDao.modifyTimerTask(timerTask);
			}
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteTimerTaskByDelete(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			TimerTask timerTask = timerTaskDao.findTimerTaskById(id);
			timerTask.setIsDelete(Constant.DELETE);
			timerTaskDao.modifyTimerTask(timerTask);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteTimerTask(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			timerTaskDao.deleteTimerTask(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
