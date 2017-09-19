package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Constant;

import util.DBUtils_Mysql;
import entity.Interface;

public class InterfaceDao {
	public List<Interface> findInterfaceAll() throws Exception{
		List<Interface> interfaces = new ArrayList<Interface>();
		String sql = "SELECT ID,INTERFACE_NAME,INTERFACE_ADDRESS,REQUEST_MODE,HTTP_CODE,MODULE_ID,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_INTERFACE WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Interface inter = new Interface();
			inter.setId(rs.getInt(1));
			inter.setInterfaceName(rs.getString(2));
			inter.setInterfaceAddress(rs.getString(3));
			inter.setRequestMode(rs.getInt(4));
			inter.setHttpCode(rs.getInt(5));
			inter.setModuleId(rs.getInt(6));
			inter.setCreateUser(rs.getInt(7));
			inter.setCreateDate(rs.getDate(8));
			inter.setIsDelete(rs.getInt(9));
			interfaces.add(inter);
		}
		return interfaces;
	}
	
	public List<Interface> findInterfaceByModule(int moduleId) throws Exception{
		List<Interface> interfaces = new ArrayList<Interface>();
		String sql = "SELECT ID,INTERFACE_NAME,INTERFACE_ADDRESS,REQUEST_MODE,HTTP_CODE,MODULE_ID,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_INTERFACE WHERE MODULE_ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, moduleId);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Interface inter = new Interface();
			inter.setId(rs.getInt(1));
			inter.setInterfaceName(rs.getString(2));
			inter.setInterfaceAddress(rs.getString(3));
			inter.setRequestMode(rs.getInt(4));
			inter.setHttpCode(rs.getInt(5));
			inter.setModuleId(rs.getInt(6));
			inter.setCreateUser(rs.getInt(7));
			inter.setCreateDate(rs.getDate(8));
			inter.setIsDelete(rs.getInt(9));
			interfaces.add(inter);
		}
		return interfaces;
	}
	public Interface findInterfaceById(int id) throws Exception{
		Interface inter = null;
		String sql = "SELECT ID,INTERFACE_NAME,INTERFACE_ADDRESS,REQUEST_MODE,HTTP_CODE,MODULE_ID,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_INTERFACE WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			inter = new Interface();
			inter.setId(rs.getInt(1));
			inter.setInterfaceName(rs.getString(2));
			inter.setInterfaceAddress(rs.getString(3));
			inter.setRequestMode(rs.getInt(4));
			inter.setHttpCode(rs.getInt(5));
			inter.setModuleId(rs.getInt(6));
			inter.setCreateUser(rs.getInt(7));
			inter.setCreateDate(rs.getDate(8));
			inter.setIsDelete(rs.getInt(9));
		}
		return inter;
	}
	public int addInterface(Interface inter) throws Exception{
		String sql = "INSERT INTO T_INTERFACE(INTERFACE_NAME,INTERFACE_ADDRESS,REQUEST_MODE,HTTP_CODE,MODULE_ID,CREATE_USER,CREATE_DATE,IS_DELETE)VALUES(?,?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, inter.getInterfaceName());
		ps.setString(2, inter.getInterfaceAddress());
		ps.setInt(3, inter.getRequestMode());
		ps.setInt(4, inter.getHttpCode());
		ps.setInt(5, inter.getModuleId());
		ps.setInt(6, inter.getCreateUser());
		ps.setDate(7, inter.getCreateDate());
		ps.setInt(8, inter.getIsDelete());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int interface_id = 0;
		if(rs.next()){
			interface_id = rs.getInt(1);
		}
		return interface_id;
		
	}
	public void modifyInterface(Interface inter) throws Exception{
		String sql = "UPDATE T_INTERFACE SET INTERFACE_NAME=?,INTERFACE_ADDRESS=?,REQUEST_MODE=?,HTTP_CODE=?,MODULE_ID=?,CREATE_USER=?,CREATE_DATE=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, inter.getInterfaceName());
		ps.setString(2, inter.getInterfaceAddress());
		ps.setInt(3, inter.getRequestMode());
		ps.setInt(4, inter.getHttpCode());
		ps.setInt(5, inter.getModuleId());
		ps.setInt(6, inter.getCreateUser());
		ps.setDate(7, inter.getCreateDate());
		ps.setInt(8, inter.getIsDelete());
		ps.setInt(9, inter.getId());
		ps.executeUpdate();
	}
	public void deleteInterface(int id) throws Exception{
		String sql = "DELETE FROM T_INTERFACE WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
