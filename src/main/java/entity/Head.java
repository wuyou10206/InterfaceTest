package entity;

import java.sql.Date;

public class Head {
	private int id;
	private int interfaceId;
	private String headKey;
	private String headValue;
	private String headVariable;
	private int createUser;
	private Date createDate;
	private int isDelete;
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
	
	public String getHeadKey() {
		return headKey;
	}
	public void setHeadKey(String headKey) {
		this.headKey = headKey;
	}
	public String getHeadValue() {
		return headValue;
	}
	public void setHeadValue(String headValue) {
		this.headValue = headValue;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getHeadVariable() {
		return headVariable;
	}
	public void setHeadVariable(String headVariable) {
		this.headVariable = headVariable;
	}
	
}
