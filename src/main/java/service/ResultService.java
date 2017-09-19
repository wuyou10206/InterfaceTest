package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.ResultDao;
import entity.Result;

public class ResultService {
	private ResultDao resultDao = new ResultDao();
	public List<Result> findResult() throws Exception{
		List<Result> results = resultDao.findResultAll();
		DBUtils_Mysql.close();
		return results;
	}
	public List<Result> findResultByResultIds(String resultIds) throws Exception{
		List<Result> results = new ArrayList<Result>();
		String[] result_ids = resultIds.split(",");
		for(String s:result_ids){
			int resultId = Integer.parseInt(s);
			Result result = findResultById(resultId);
			results.add(result);
		}
		DBUtils_Mysql.close();
		return results;
	}
	public List<Result> findResultByInterface(int interfaceId) throws Exception{
		List<Result> results = resultDao.findResultByInterface(interfaceId);
		DBUtils_Mysql.close();
		return results;
	}
	public Result findResultById(int id) throws Exception{
		Result result = resultDao.findResultById(id);
		DBUtils_Mysql.close();
		return result;
	}
	public Result findResultByMaxDate(int interfaceId) throws Exception{
		Result result = resultDao.findResultByMaxDate(interfaceId);
		DBUtils_Mysql.close();
		return result;
	}
	public int addResult(int interfaceId,int returnStatus,String returnContent,int isSuccess,int isPass,String executeResult,int executeUser,Timestamp executeDate,int isDelete) throws Exception{
		int resultId = 0;
		try {
			TransactionManager.beginTransaction();
			Result result = new Result();
			result.setInterfaceId(interfaceId);
			result.setReturnStatus(returnStatus);
			result.setReturnContent(returnContent);
			result.setIsSuccess(isSuccess);
			result.setIsPass(isPass);
			result.setExecuteResult(executeResult);
			result.setExecuteUser(executeUser);
			result.setExecuteDate(executeDate);
			result.setIsDetele(isDelete);
			resultId = resultDao.addResult(result);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return resultId;
	}
	public void deleteResult(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			resultDao.deleteResult(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyResult(int id,int interfaceId,int returnStatus,String returnContent,int isSuccess,int isPass,String executeResult,int executeUser,Timestamp executeDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Result result = new Result();
			result.setId(id);
			result.setReturnStatus(returnStatus);
			result.setReturnContent(returnContent);
			result.setIsSuccess(isSuccess);
			result.setIsPass(isPass);
			result.setExecuteResult(executeResult);
			result.setExecuteUser(executeUser);
			result.setExecuteDate(executeDate);
			result.setIsDetele(isDelete);
			resultDao.modifyResult(result);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
