package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.ResultPerformance;

public class ResultPerformanceDao {
	public List<ResultPerformance> findResultPerformanceAll() throws Exception{
		List<ResultPerformance> results = new ArrayList<ResultPerformance>();
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE,BEGIN_DATE,END_DATE,USE_DATE FROM T_RESULT_PERFORMANCE";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ResultPerformance result = new ResultPerformance();
			result.setId(rs.getInt(1));
			result.setInterfaceId(rs.getInt(2));
			result.setReturnStatus(rs.getInt(3));
			result.setReturnContent(rs.getString(4));
			result.setIsSuccess(rs.getInt(5));
			result.setIsPass(rs.getInt(6));
			result.setExecuteResult(rs.getString(7));
			result.setExecuteUser(rs.getInt(8));
			result.setExecuteDate(rs.getTimestamp(9));
			result.setIsDetele(rs.getInt(10));
			result.setBeginDate(rs.getLong(11));
			result.setEndDate(rs.getLong(12));
			result.setUseDate(rs.getLong(13));
			results.add(result);
		}
		return results;
	}
	
	public List<ResultPerformance> findResultPerformanceByInterface(int interfaceId) throws Exception{
		List<ResultPerformance> results = new ArrayList<ResultPerformance>();
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE,BEGIN_DATE,END_DATE,USE_DATE FROM T_RESULT_PERFORMANCE WHERE INTERFACE_ID=? ORDER BY EXECUTE_DATE DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ResultPerformance result = new ResultPerformance();
			result.setId(rs.getInt(1));
			result.setInterfaceId(rs.getInt(2));
			result.setReturnStatus(rs.getInt(3));
			result.setReturnContent(rs.getString(4));
			result.setIsSuccess(rs.getInt(5));
			result.setIsPass(rs.getInt(6));
			result.setExecuteResult(rs.getString(7));
			result.setExecuteUser(rs.getInt(8));
			result.setExecuteDate(rs.getTimestamp(9));
			result.setIsDetele(rs.getInt(10));
			result.setBeginDate(rs.getLong(11));
			result.setEndDate(rs.getLong(12));
			result.setUseDate(rs.getLong(13));
			results.add(result);
		}
		return results;
	}
	public ResultPerformance findResultPerformanceByMaxDate(int interfaceId) throws Exception{
		ResultPerformance result = null;
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE,BEGIN_DATE,END_DATE,USE_DATE FROM T_RESULT_PERFORMANCE WHERE INTERFACE_ID=? ORDER BY EXECUTE_DATE DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			result = new ResultPerformance();
			result.setId(rs.getInt(1));
			result.setInterfaceId(rs.getInt(2));
			result.setReturnStatus(rs.getInt(3));
			result.setReturnContent(rs.getString(4));
			result.setIsSuccess(rs.getInt(5));
			result.setIsPass(rs.getInt(6));
			result.setExecuteResult(rs.getString(7));
			result.setExecuteUser(rs.getInt(8));
			result.setExecuteDate(rs.getTimestamp(9));
			result.setIsDetele(rs.getInt(10));
			result.setBeginDate(rs.getLong(11));
			result.setEndDate(rs.getLong(12));
			result.setUseDate(rs.getLong(13));
		}
		return result;
	}
	public ResultPerformance findResultPerformanceById(int id) throws Exception{
		ResultPerformance result = null;
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE,BEGIN_DATE,END_DATE,USE_DATE FROM T_RESULT_PERFORMANCE WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			result = new ResultPerformance();
			result.setId(rs.getInt(1));
			result.setInterfaceId(rs.getInt(2));
			result.setReturnStatus(rs.getInt(3));
			result.setReturnContent(rs.getString(4));
			result.setIsSuccess(rs.getInt(5));
			result.setIsPass(rs.getInt(6));
			result.setExecuteResult(rs.getString(7));
			result.setExecuteUser(rs.getInt(8));
			result.setExecuteDate(rs.getTimestamp(9));
			result.setIsDetele(rs.getInt(10));
			result.setBeginDate(rs.getLong(11));
			result.setEndDate(rs.getLong(12));
			result.setUseDate(rs.getLong(13));
		}
		return result;
	}
	public int addResultPerformance(ResultPerformance result) throws Exception{
		String sql = "INSERT INTO T_RESULT_PERFORMANCE(INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE,BEGIN_DATE,END_DATE,USE_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, result.getInterfaceId());
		ps.setInt(2, result.getReturnStatus());
		ps.setString(3, result.getReturnContent());
		ps.setInt(4, result.getIsSuccess());
		ps.setInt(5, result.getIsPass());
		ps.setString(6, result.getExecuteResult());
		ps.setInt(7, result.getExecuteUser());
		ps.setTimestamp(8, result.getExecuteDate());
		ps.setInt(9, result.getIsDetele());
		ps.setLong(10, result.getBeginDate());
		ps.setLong(11, result.getEndDate());
		ps.setLong(12, result.getUseDate());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int resultId = 0;
		if(rs.next()){
			resultId = rs.getInt(1);
		}
		return resultId;
	}
	public void modifyResultPerformance(ResultPerformance result) throws Exception{
		String sql = "UPDATE T_RESULT_PERFORMANCE SET INTERFACE_ID=?,RETURN_STATUS=?,RETURN_CONTENT=?,IS_SUCCESS=?,IS_PASS=?,EXECUTE_RESULT=?,EXECUTE_USER=?,EXECUTE_DATE=?,IS_DELETE=?,BEGIN_DATE=?,END_DATE=?,USE_DATE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, result.getInterfaceId());
		ps.setInt(2, result.getReturnStatus());
		ps.setString(3, result.getReturnContent());
		ps.setInt(4, result.getIsSuccess());
		ps.setInt(5, result.getIsPass());
		ps.setString(6, result.getExecuteResult());
		ps.setInt(7, result.getExecuteUser());
		ps.setTimestamp(8, result.getExecuteDate());
		ps.setInt(9, result.getIsDetele());
		ps.setLong(10, result.getBeginDate());
		ps.setLong(11, result.getEndDate());
		ps.setLong(12, result.getUseDate());
		ps.setInt(13, result.getId());
		ps.executeUpdate();
	}
	public void deleteResultPerformance(int id) throws Exception{
		String sql = "DELETE FROM T_RESULT_PERFORMANCE WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
