package entity;

import java.sql.Date;

public class OutValue {
	private int id;
	private int interfaceId;
	private int valueSpace;
	private String outValueKey;
	private String outValueName;
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
	public int getValueSpace() {
		return valueSpace;
	}
	public void setValueSpace(int valueSpace) {
		this.valueSpace = valueSpace;
	}
	public String getOutValueKey() {
		return outValueKey;
	}
	public void setOutValueKey(String outValueKey) {
		this.outValueKey = outValueKey;
	}
	public String getOutValueName() {
		return outValueName;
	}
	public void setOutValueName(String outValueName) {
		this.outValueName = outValueName;
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
	
}
