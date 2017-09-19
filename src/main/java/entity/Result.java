package entity;

import java.sql.Timestamp;

public class Result {
	private int id;
	private int interfaceId;
	private int returnStatus;
	private String returnContent;
	private int isSuccess;
	private int isPass;
	private String executeResult;
	private int executeUser;
	private Timestamp executeDate;
	private int isDetele;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(int interfaceId) {
		this.interfaceId = interfaceId;
	}
	public int getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(int returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getReturnContent() {
		return returnContent;
	}
	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public int getIsPass() {
		return isPass;
	}
	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}
	public int getExecuteUser() {
		return executeUser;
	}
	public void setExecuteUser(int executeUser) {
		this.executeUser = executeUser;
	}
	
	public Timestamp getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Timestamp executeDate) {
		this.executeDate = executeDate;
	}
	public int getIsDetele() {
		return isDetele;
	}
	public void setIsDetele(int isDetele) {
		this.isDetele = isDetele;
	}
	public String getExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
	
}
