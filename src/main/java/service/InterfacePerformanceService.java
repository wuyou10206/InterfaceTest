package service;

import java.sql.Timestamp;
import java.util.List;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.InterfacePerformanceDao;
import entity.InterfacePerformance;

public class InterfacePerformanceService {
	private InterfacePerformanceDao interfacePerformanceDao = new InterfacePerformanceDao();
	public List<InterfacePerformance> findInterfacePerformance() throws Exception{
		List<InterfacePerformance> ips = interfacePerformanceDao.findInterfacePerformanceAll();
		DBUtils_Mysql.close();
		return ips;
	}
	
	public List<InterfacePerformance> findInterfacePerformanceByInterface(int interfaceId) throws Exception{
		List<InterfacePerformance> ips = interfacePerformanceDao.findInterfacePerformanceByInterface(interfaceId);
		DBUtils_Mysql.close();
		return ips;
	}
	public InterfacePerformance findInterfacePerformanceById(int id) throws Exception{
		InterfacePerformance ip = interfacePerformanceDao.findInterfacePerformanceById(id);
		DBUtils_Mysql.close();
		return ip;
	}
	public InterfacePerformance findInterfacePerformanceByMaxDate(int interfaceId) throws Exception{
		InterfacePerformance ip = interfacePerformanceDao.findInterfacePerformanceByMaxDate(interfaceId);
		DBUtils_Mysql.close();
		return ip;
	}
	public int addInterfacePerformance(int interfaceId,String resultPerformanceIds,int threadNumber,long totalTime,long avgTime,long time5,long time9,long minTime,long maxTime,double tps,double throughput,int userId,Timestamp addTime,int isDelete) throws Exception{
		int interfacePerformanceId = 0;
		try {
			TransactionManager.beginTransaction();
			InterfacePerformance ip = new InterfacePerformance();
			ip.setInterfaceId(interfaceId);
			ip.setResultPerformanceIds(resultPerformanceIds);
			ip.setThreadNumber(threadNumber);
			ip.setTotalTime(totalTime);
			ip.setAvgTime(avgTime);
			ip.setTime5(time5);
			ip.setTime9(time9);
			ip.setMinTime(minTime);
			ip.setMaxTime(maxTime);
			ip.setTps(tps);
			ip.setThroughput(throughput);
			ip.setUserId(userId);
			ip.setAddTime(addTime);
			ip.setIsDelete(isDelete);
			interfacePerformanceId = interfacePerformanceDao.addInterfacePerformance(ip);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return interfacePerformanceId;
	}
	public void deleteInterfacePerformance(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			interfacePerformanceDao.deleteInterfacePerformance(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyInterfacePerformance(int id,int interfaceId,String resultPerformanceIds,int threadNumber,long totalTime,long avgTime,long time5,long time9,long minTime,long maxTime,double tps,double throughput,int userId,Timestamp addTime,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			InterfacePerformance ip = new InterfacePerformance();
			ip.setId(id);
			ip.setInterfaceId(interfaceId);
			ip.setResultPerformanceIds(resultPerformanceIds);
			ip.setThreadNumber(threadNumber);
			ip.setTotalTime(totalTime);
			ip.setAvgTime(avgTime);
			ip.setTime5(time5);
			ip.setTime9(time9);
			ip.setMinTime(minTime);
			ip.setMaxTime(maxTime);
			ip.setTps(tps);
			ip.setThroughput(throughput);
			ip.setUserId(userId);
			ip.setAddTime(addTime);
			ip.setIsDelete(isDelete);
			interfacePerformanceDao.modifyInterfacePerformance(ip);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
