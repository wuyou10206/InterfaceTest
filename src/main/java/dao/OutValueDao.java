package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.OutValue;

public class OutValueDao {
	public List<OutValue> findOutValueAll() throws Exception{
		List<OutValue> outValues = new ArrayList<OutValue>();
		String sql = "SELECT ID,INTERFACE_ID,VALUE_SPACE,OUT_VALUE_KEY,OUT_VALUE_NAME,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_OUT_VALUE";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			OutValue outValue = new OutValue();
			outValue.setId(rs.getInt(1));
			outValue.setInterfaceId(rs.getInt(2));
			outValue.setValueSpace(rs.getInt(3));
			outValue.setOutValueKey(rs.getString(4));
			outValue.setOutValueName(rs.getString(5));
			outValue.setCreateUser(rs.getInt(6));
			outValue.setCreateDate(rs.getDate(7));
			outValue.setIsDelete(rs.getInt(8));
			outValues.add(outValue);
		}
		return outValues;
	}
	public List<OutValue> findOutValueByInterface(int interfaceId) throws Exception{
		List<OutValue> outValues = new ArrayList<OutValue>();
		String sql = "SELECT ID,INTERFACE_ID,VALUE_SPACE,OUT_VALUE_KEY,OUT_VALUE_NAME,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_OUT_VALUE WHERE INTERFACE_ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			OutValue outValue = new OutValue();
			outValue.setId(rs.getInt(1));
			outValue.setInterfaceId(rs.getInt(2));
			outValue.setValueSpace(rs.getInt(3));
			outValue.setOutValueKey(rs.getString(4));
			outValue.setOutValueName(rs.getString(5));
			outValue.setCreateUser(rs.getInt(6));
			outValue.setCreateDate(rs.getDate(7));
			outValue.setIsDelete(rs.getInt(8));
			outValues.add(outValue);
		}
		return outValues;
	}
	public void addOutValue(OutValue outValue) throws Exception{
		String sql = "INSERT INTO T_OUT_VALUE(INTERFACE_ID,VALUE_SPACE,OUT_VALUE_KEY,OUT_VALUE_NAME,CREATE_USER,CREATE_DATE,IS_DELETE)VALUES(?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, outValue.getInterfaceId());
		ps.setInt(2,outValue.getValueSpace());
		ps.setString(3,outValue.getOutValueKey());
		ps.setString(4, outValue.getOutValueName());
		ps.setInt(5, outValue.getCreateUser());
		ps.setDate(6, outValue.getCreateDate());
		ps.setInt(7, outValue.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyOutValue(OutValue outValue) throws Exception{
		String sql = "UPDATE T_OUT_VALUE SET INTERFACE_ID=?,VALUE_SPACE=?,OUT_VALUE_KEY=?,OUT_VALUE_NAME=?,CREATE_USER=?,CREATE_DATE=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, outValue.getInterfaceId());
		ps.setInt(2,outValue.getValueSpace());
		ps.setString(3,outValue.getOutValueKey());
		ps.setString(4, outValue.getOutValueName());
		ps.setInt(5, outValue.getCreateUser());
		ps.setDate(6, outValue.getCreateDate());
		ps.setInt(7, outValue.getIsDelete());
		ps.setInt(8, outValue.getId());
		
		ps.executeUpdate();
	}
	public void deleteOutValueByInterface(int id) throws Exception{
		String sql = "DELETE FROM T_OUT_VALUE WHERE INTERFACE_ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
	public void deleteOutValue(int id) throws Exception{
		String sql = "DELETE FROM T_OUT_VALUE WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
