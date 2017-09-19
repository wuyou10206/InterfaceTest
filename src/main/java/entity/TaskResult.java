package entity;

import java.sql.Timestamp;

public class TaskResult {
	private int id;
	private int taskId;
	private String resultIds;
	private int userId;
	private Timestamp executeTime;
	private int isDelete;
	private int taskPass;
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
	
}
