package entity;

import java.sql.Timestamp;

public class TaskPerformance {
	private int id;
	private int taskId;
	private String taskPerformanceResultIds;
	private int threadNumber;
	private long totalTime;
	private long avgTime;
	private long time5;
	private long time9;
	private long minTime;
	private long maxTime;
	private double tps;
	private double throughput;
	private int userId;
	private Timestamp addTime;
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
	public String getTaskPerformanceResultIds() {
		return taskPerformanceResultIds;
	}
	public void setTaskPerformanceResultIds(String taskPerformanceResultIds) {
		this.taskPerformanceResultIds = taskPerformanceResultIds;
	}
	public int getThreadNumber() {
		return threadNumber;
	}
	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	public long getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(long avgTime) {
		this.avgTime = avgTime;
	}
	public long getTime5() {
		return time5;
	}
	public void setTime5(long time5) {
		this.time5 = time5;
	}
	public long getTime9() {
		return time9;
	}
	public void setTime9(long time9) {
		this.time9 = time9;
	}
	public long getMinTime() {
		return minTime;
	}
	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}
	public long getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
	public double getTps() {
		return tps;
	}
	public void setTps(double tps) {
		this.tps = tps;
	}
	public double getThroughput() {
		return throughput;
	}
	public void setThroughput(double throughput) {
		this.throughput = throughput;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
