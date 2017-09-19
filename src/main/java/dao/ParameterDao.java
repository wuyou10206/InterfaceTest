package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.Parameter;

public class ParameterDao {
	public List<Parameter> findParameterAll() throws Exception{
		List<Parameter> parameters = new ArrayList<Parameter>();
		String sql = "SELECT ID,INTERFACE_ID,PARAMETER_KEY,PARAMETER_VALUE,PARAMETER_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_PARAMETER";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Parameter p = new Parameter();
			p.setId(rs.getInt(1));
			p.setInterfaceId(rs.getInt(2));
			p.setParameterKey(rs.getString(3));
			p.setParameterValue(rs.getString(4));
			p.setParameterVariable(rs.getString(5));
			p.setCreateUser(rs.getInt(6));
			p.setCreateDate(rs.getDate(7));
			p.setIsDelete(rs.getInt(8));
			parameters.add(p);
		}
		return parameters;
	}
	public List<Parameter> findParameterByInterface(int interfaceId) throws Exception{
		List<Parameter> parameters = new ArrayList<Parameter>();
		String sql = "SELECT ID,INTERFACE_ID,PARAMETER_KEY,PARAMETER_VALUE,PARAMETER_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_PARAMETER WHERE INTERFACE_ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Parameter p = new Parameter();
			p.setId(rs.getInt(1));
			p.setInterfaceId(rs.getInt(2));
			p.setParameterKey(rs.getString(3));
			p.setParameterValue(rs.getString(4));
			p.setParameterVariable(rs.getString(5));
			p.setCreateUser(rs.getInt(6));
			p.setCreateDate(rs.getDate(7));
			p.setIsDelete(rs.getInt(8));
			parameters.add(p);
		}
		return parameters;
	}
	public void addParameter(Parameter parameter) throws Exception{
		String sql = "INSERT INTO T_PARAMETER(INTERFACE_ID,PARAMETER_KEY,PARAMETER_VALUE,PARAMETER_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE)VALUES(?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, parameter.getInterfaceId());
		ps.setString(2,parameter.getParameterKey());
		ps.setString(3,parameter.getParameterValue());
		ps.setString(4, parameter.getParameterVariable());
		ps.setInt(5, parameter.getCreateUser());
		ps.setDate(6, parameter.getCreateDate());
		ps.setInt(7, parameter.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyParameter(Parameter parameter) throws Exception{
		String sql = "UPDATE T_PARAMETER SET INTERFACE_ID=?,PARAMETER_KEY=?,PARAMETER_VALUE=?,PARAMETER_VARIABLE=?,CREATE_USER=?,CREATE_DATE=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, parameter.getInterfaceId());
		ps.setString(2,parameter.getParameterKey());
		ps.setString(3,parameter.getParameterValue());
		ps.setString(4, parameter.getParameterVariable());
		ps.setInt(5, parameter.getCreateUser());
		ps.setDate(6, parameter.getCreateDate());
		ps.setInt(7, parameter.getIsDelete());
		ps.setInt(8, parameter.getId());
		ps.executeUpdate();
	}
	public void deleteParameterByInterface(int interfaceId) throws Exception{
		String sql = "DELETE FROM T_PARAMETER WHERE INTERFACE_ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,interfaceId);
		ps.executeUpdate();
	}
	public void deleteParameter(int id) throws Exception{
		String sql = "DELETE FROM T_PARAMETER WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
	
}
