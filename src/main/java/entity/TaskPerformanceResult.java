package entity;

import java.sql.Timestamp;

public class TaskPerformanceResult {
	private int id;
	private int taskId;
	private String resultIds;
	private int userId;
	private Timestamp executeTime;
	private int isDelete;
	private int taskPass;
	private long beginDate;
	private long endDate;
	private long useDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getResultIds() {
		return resultIds;
	}
	public void setResultIds(String resultIds) {
		this.resultIds = resultIds;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Timestamp executeTime) {
		this.executeTime = executeTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getTaskPass() {
		return taskPass;
	}
	public void setTaskPass(int taskPass) {
		this.taskPass = taskPass;
	}
	public long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public long getUseDate() {
		return useDate;
	}
	public void setUseDate(long useDate) {
		this.useDate = useDate;
	}
	
}
