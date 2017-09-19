package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.CheckDao;
import entity.InterfaceCheck;

public class CheckService {
	private CheckDao checkDao = new CheckDao();
	public List<InterfaceCheck> findCheck() throws Exception{
		List<InterfaceCheck> checks = checkDao.findInterfaceCheckAll();
		DBUtils_Mysql.close();
		return checks;
	}
	public List<InterfaceCheck> findCheckByInterface(int interfaceId) throws Exception{
		List<InterfaceCheck> checks = checkDao.findInterfaceCheckByInterface(interfaceId);
		DBUtils_Mysql.close();
		return checks;
	}
	public void addCheck(int interfaceId,int checkMode,String checkPoint,int createUser,Date createDate,int isDelete,String checkDesc) throws Exception{
		try {
			TransactionManager.beginTransaction();
			InterfaceCheck check = new InterfaceCheck();
			check.setInterfaceId(interfaceId);
			check.setCheckMode(checkMode);
			check.setCheckPoint(checkPoint);
			check.setCreateUser(createUser);
			check.setCreateDate(createDate);
			check.setIsDelete(isDelete);
			check.setCheckDesc(checkDesc);
			checkDao.addInterfaceCheck(check);
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
			checkDao.deleteInterfaceCheck(id);
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
			checkDao.deleteInterfaceCheckByInterface(interfaceId);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyCheck(int id,int interfaceId,int checkMode,String checkPoint,int createUser,Date createDate,int isDelete,String checkDesc) throws Exception{
		try {
			TransactionManager.beginTransaction();
			InterfaceCheck check = new InterfaceCheck();
			check.setId(id);
			check.setInterfaceId(interfaceId);
			check.setCheckMode(checkMode);
			check.setCheckPoint(checkPoint);
			check.setCreateUser(createUser);
			check.setCreateDate(createDate);
			check.setIsDelete(isDelete);
			check.setCheckDesc(checkDesc);
			checkDao.modifyInterfaceCheck(check);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
