package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.HeadDao;
import entity.Head;

public class HeadService {
	private HeadDao headDao = new HeadDao();
	public List<Head> findHead() throws Exception{
		List<Head> cookies = headDao.findHeadAll();
		DBUtils_Mysql.close();
		return cookies;
	}
	public List<Head> findHeadByInterface(int interfaceId) throws Exception{
		List<Head> cookies = headDao.findHeadByInterface(interfaceId);
		DBUtils_Mysql.close();
		return cookies;
	}
	public void addHead(int interfaceId,String headKey,String headValue,String headVariable,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Head head = new Head();
			head.setInterfaceId(interfaceId);
			head.setHeadKey(headKey);
			head.setHeadValue(headValue);
			head.setHeadVariable(headVariable);
			head.setCreateUser(createUser);
			head.setCreateDate(createDate);
			head.setIsDelete(isDelete);
			headDao.addHead(head);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteHead(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			headDao.deleteHead(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteHeadByInterface(int interfaceId) throws Exception{
		try {
			TransactionManager.beginTransaction();
			headDao.deleteHeadByInterface(interfaceId);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyHead(int id,int interfaceId,String headKey,String headValue,String headVariable,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Head head = new Head();
			head.setId(id);
			head.setInterfaceId(interfaceId);
			head.setHeadKey(headKey);
			head.setHeadValue(headValue);
			head.setHeadVariable(headVariable);
			head.setCreateUser(createUser);
			head.setCreateDate(createDate);
			head.setIsDelete(isDelete);
			headDao.modifyHead(head);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
