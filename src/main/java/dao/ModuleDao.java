package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Constant;

import util.DBUtils_Mysql;
import entity.Module;

public class ModuleDao {
	public List<Module> findModuleAll() throws Exception{
		List<Module> modules = new ArrayList<Module>();
		String sql = "SELECT ID,MODULE_NAME,USER_ID,ADD_TIME,PID,IS_DELETE FROM T_MODULES WHERE IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Module module = new Module();
			module.setId(rs.getInt(1));
			module.setModuleName(rs.getString(2));
			module.setUserId(rs.getInt(3));
			module.setAddTime(rs.getDate(4));
			module.setPid(rs.getInt(5));
			module.setIsDelete(rs.getInt(6));
			modules.add(module);
		}
		return modules;
	}
	public List<Module> findModuleByProject(int pid) throws Exception{
		List<Module> modules = new ArrayList<Module>();
		String sql = "SELECT ID,MODULE_NAME,USER_ID,ADD_TIME,PID,IS_DELETE FROM T_MODULES WHERE PID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, pid);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Module module = new Module();
			module.setId(rs.getInt(1));
			module.setModuleName(rs.getString(2));
			module.setUserId(rs.getInt(3));
			module.setAddTime(rs.getDate(4));
			module.setPid(rs.getInt(5));
			module.setIsDelete(rs.getInt(6));
			modules.add(module);
		}
		return modules;
	}
	public Module findModuleById(int id) throws Exception{
		String sql = "SELECT ID,MODULE_NAME,USER_ID,ADD_TIME,PID,IS_DELETE FROM T_MODULES WHERE ID=? AND IS_DELETE=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, Constant.EXIST);
		ResultSet rs = ps.executeQuery();
		Module module = null;
		while(rs.next()){
			module = new Module();
			module.setId(rs.getInt(1));
			module.setModuleName(rs.getString(2));
			module.setUserId(rs.getInt(3));
			module.setAddTime(rs.getDate(4));
			module.setPid(rs.getInt(5));
			module.setIsDelete(rs.getInt(6));
		}
		return module;
	}
	public void addModule(Module module) throws Exception{
		String sql = "INSERT INTO T_MODULES(MODULE_NAME,USER_ID,ADD_TIME,PID,IS_DELETE)VALUES(?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, module.getModuleName());
		ps.setInt(2, module.getUserId());
		ps.setDate(3, module.getAddTime());
		ps.setInt(4, module.getPid());
		ps.setInt(5, module.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyModule(Module module) throws Exception{
		String sql = "UPDATE T_MODULES SET MODULE_NAME=?,USER_ID=?,ADD_TIME=?,PID=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, module.getModuleName());
		ps.setInt(2, module.getUserId());
		ps.setDate(3, module.getAddTime());
		ps.setInt(4, module.getPid());
		ps.setInt(5, module.getIsDelete());
		ps.setInt(6, module.getId());
		ps.executeUpdate();
	}
	public void deleteModule(int id) throws Exception{
		String sql = "DELETE FROM T_MODULES WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
