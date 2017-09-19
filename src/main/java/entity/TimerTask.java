package entity;

import java.sql.Timestamp;

public class TimerTask {
	private int id;
	private int taskId;
	private Timestamp executeTime;
	private long loopTime;
	private int userId;
	private Timestamp createTime;
	private int isDelete;
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
	public Timestamp getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Timestamp executeTime) {
		this.executeTime = executeTime;
	}
	public long getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(long loopTime) {
		this.loopTime = loopTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
