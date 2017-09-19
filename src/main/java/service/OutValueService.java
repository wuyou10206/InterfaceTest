package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.OutValueDao;
import entity.OutValue;

public class OutValueService {
	private OutValueDao outValueDao = new OutValueDao();
	public List<OutValue> findOutValue() throws Exception{
		List<OutValue> outValues = outValueDao.findOutValueAll();
		DBUtils_Mysql.close();
		return outValues;
	}
	public List<OutValue> findOutValueByInterface(int interfaceId) throws Exception{
		List<OutValue> outValues = outValueDao.findOutValueByInterface(interfaceId);
		DBUtils_Mysql.close();
		return outValues;
	}
	public void addOutValue(int interfaceId,int valueSpace,String outValueKey,String outValueName,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			OutValue outValue = new OutValue();
			outValue.setInterfaceId(interfaceId);
			outValue.setValueSpace(valueSpace);
			outValue.setOutValueKey(outValueKey);
			outValue.setCreateUser(createUser);
			outValue.setCreateDate(createDate);
			outValue.setIsDelete(isDelete);
			outValue.setOutValueName(outValueName);
			outValueDao.addOutValue(outValue);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteCheck(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			outValueDao.deleteOutValue(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteCheckByInterface(int interfaceId) throws Exception{
		try {
			TransactionManager.beginTransaction();
			outValueDao.deleteOutValueByInterface(interfaceId);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyOutValue(int id,int interfaceId,int valueSpace,String outValueKey,String outValueName,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			OutValue outValue = new OutValue();
			outValue.setId(id);
			outValue.setInterfaceId(interfaceId);
			outValue.setValueSpace(valueSpace);
			outValue.setOutValueKey(outValueKey);
			outValue.setCreateUser(createUser);
			outValue.setCreateDate(createDate);
			outValue.setIsDelete(isDelete);
			outValue.setOutValueName(outValueName);
			outValueDao.modifyOutValue(outValue);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
