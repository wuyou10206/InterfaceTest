package service;

import java.sql.Date;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.ParameterDao;
import entity.Parameter;

public class ParameterService {
	private ParameterDao parameterDao = new ParameterDao();
	public List<Parameter> findParameter() throws Exception{
		List<Parameter> Parameters = parameterDao.findParameterAll();
		DBUtils_Mysql.close();
		return Parameters;
	}
	public List<Parameter> findParameterByInterface(int interfaceId) throws Exception{
		List<Parameter> Parameters = parameterDao.findParameterByInterface(interfaceId);
		DBUtils_Mysql.close();
		return Parameters;
	}
	public void addParameter(int interfaceId,String parameterKey,String parameterValue,String parameterVariable,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Parameter parameter = new Parameter();
			parameter.setInterfaceId(interfaceId);
			parameter.setParameterKey(parameterKey);
			parameter.setParameterValue(parameterValue);
			parameter.setParameterVariable(parameterVariable);
			parameter.setCreateUser(createUser);
			parameter.setCreateDate(createDate);
			parameter.setIsDelete(isDelete);
			parameterDao.addParameter(parameter);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteParameter(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			parameterDao.deleteParameter(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteParameterByInterface(int interfaceId) throws Exception{
		try {
			TransactionManager.beginTransaction();
			parameterDao.deleteParameterByInterface(interfaceId);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void mpdifyParameter(int id,int interfaceId,String parameterKey,String parameterValue,String parameterVariable,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Parameter parameter = new Parameter();
			parameter.setId(id);
			parameter.setInterfaceId(interfaceId);
			parameter.setParameterKey(parameterKey);
			parameter.setParameterValue(parameterValue);
			parameter.setParameterVariable(parameterVariable);
			parameter.setCreateUser(createUser);
			parameter.setCreateDate(createDate);
			parameter.setIsDelete(isDelete);
			parameterDao.modifyParameter(parameter);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
