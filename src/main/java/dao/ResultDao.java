package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.Result;

public class ResultDao {
	public List<Result> findResultAll() throws Exception{
		List<Result> results = new ArrayList<Result>();
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE FROM T_RESULT";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Result result = new Result();
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
			results.add(result);
		}
		return results;
	}
	
	public List<Result> findResultByInterface(int interfaceId) throws Exception{
		List<Result> results = new ArrayList<Result>();
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE FROM T_RESULT WHERE INTERFACE_ID=? ORDER BY EXECUTE_DATE DESC";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Result result = new Result();
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
			results.add(result);
		}
		return results;
	}
	public Result findResultByMaxDate(int interfaceId) throws Exception{
		Result result = null;
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE FROM T_RESULT WHERE INTERFACE_ID=? ORDER BY EXECUTE_DATE DESC LIMIT 1";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			result = new Result();
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
		}
		return result;
	}
	public Result findResultById(int id) throws Exception{
		Result result = null;
		String sql = "SELECT ID,INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE FROM T_RESULT WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			result = new Result();
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
		}
		return result;
	}
	public int addResult(Result result) throws Exception{
		String sql = "INSERT INTO T_RESULT(INTERFACE_ID,RETURN_STATUS,RETURN_CONTENT,IS_SUCCESS,IS_PASS,EXECUTE_RESULT,EXECUTE_USER,EXECUTE_DATE,IS_DELETE)VALUES(?,?,?,?,?,?,?,?,?)";
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
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int resultId = 0;
		if(rs.next()){
			resultId = rs.getInt(1);
		}
		return resultId;
	}
	public void modifyResult(Result result) throws Exception{
		String sql = "UPDATE T_RESULT SET INTERFACE_ID=?,RETURN_STATUS=?,RETURN_CONTENT=?,IS_SUCCESS=?,IS_PASS=?,EXECUTE_RESULT=?,EXECUTE_USER=?,EXECUTE_DATE=?,IS_DELETE=? WHERE ID=?";
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
		ps.setInt(10, result.getId());
		ps.executeUpdate();
	}
	public void deleteResult(int id) throws Exception{
		String sql = "DELETE FROM T_RESULT WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
