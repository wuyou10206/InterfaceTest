package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.ResultPerformanceDao;
import entity.ResultPerformance;

public class ResultPerformanceService {
	private ResultPerformanceDao resultPerformanceDao = new ResultPerformanceDao();
	public List<ResultPerformance> findResultPerformance() throws Exception{
		List<ResultPerformance> results = resultPerformanceDao.findResultPerformanceAll();
		DBUtils_Mysql.close();
		return results;
	}
	public List<ResultPerformance> findResultByResultPerformanceIds(List<Integer> resultIds) throws Exception{
		List<ResultPerformance> results = new ArrayList<ResultPerformance>();
		for(Integer resultId:resultIds){
			ResultPerformance result = findResultPerformanceById(resultId);
			results.add(result);
		}
		return results;
	}
	public List<ResultPerformance> findResultByResultPerformanceIds(String resultIds) throws Exception{
		List<ResultPerformance> results = new ArrayList<ResultPerformance>();
		String[] result_ids = resultIds.split(",");
		for(String s:result_ids){
			int resultId = Integer.parseInt(s);
			ResultPerformance result = findResultPerformanceById(resultId);
			results.add(result);
		}
		return results;
	}
	public List<ResultPerformance> findResultPerformanceByInterface(int interfaceId) throws Exception{
		List<ResultPerformance> results = resultPerformanceDao.findResultPerformanceByInterface(interfaceId);
		DBUtils_Mysql.close();
		return results;
	}
	public ResultPerformance findResultPerformanceById(int id) throws Exception{
		ResultPerformance result = resultPerformanceDao.findResultPerformanceById(id);
		DBUtils_Mysql.close();
		return result;
	}
	public ResultPerformance findResultPerformanceByMaxDate(int interfaceId) throws Exception{
		ResultPerformance result = resultPerformanceDao.findResultPerformanceByMaxDate(interfaceId);
		DBUtils_Mysql.close();
		return result;
	}
	public int addResultPerformance(int interfaceId,int returnStatus,String returnContent,int isSuccess,int isPass,String executeResult,int executeUser,Timestamp executeDate,int isDelete,long beginDate,long endDate,long useDate) throws Exception{
		int resultId = 0;
		try {
			TransactionManager.beginTransaction();
			ResultPerformance result = new ResultPerformance();
			result.setInterfaceId(interfaceId);
			result.setReturnStatus(returnStatus);
			result.setReturnContent(returnContent);
			result.setIsSuccess(isSuccess);
			result.setIsPass(isPass);
			result.setExecuteResult(executeResult);
			result.setExecuteUser(executeUser);
			result.setExecuteDate(executeDate);
			result.setIsDetele(isDelete);
			result.setBeginDate(beginDate);
			result.setEndDate(endDate);
			result.setUseDate(useDate);
			resultId = resultPerformanceDao.addResultPerformance(result);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return resultId;
	}
	public void deleteResultPerformance(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			resultPerformanceDao.deleteResultPerformance(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyResultPerformance(int id,int interfaceId,int returnStatus,String returnContent,int isSuccess,int isPass,String executeResult,int executeUser,Timestamp executeDate,int isDelete,long beginDate,long endDate,long useDate) throws Exception{
		try {
			TransactionManager.beginTransaction();
			ResultPerformance result = new ResultPerformance();
			result.setId(id);
			result.setReturnStatus(returnStatus);
			result.setReturnContent(returnContent);
			result.setIsSuccess(isSuccess);
			result.setIsPass(isPass);
			result.setExecuteResult(executeResult);
			result.setExecuteUser(executeUser);
			result.setExecuteDate(executeDate);
			result.setIsDetele(isDelete);
			result.setBeginDate(beginDate);
			result.setEndDate(endDate);
			result.setUseDate(useDate);
			resultPerformanceDao.modifyResultPerformance(result);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
