package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.InterfaceCheck;

public class CheckDao {
	public List<InterfaceCheck> findInterfaceCheckAll() throws Exception{
		List<InterfaceCheck> checks = new ArrayList<InterfaceCheck>();
		String sql = "SELECT ID,INTERFACE_ID,CHECK_MODE,CHECK_POINT,CREATE_USER,CREATE_DATE,IS_DELETE,CHECK_DESC FROM T_CHECK";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			InterfaceCheck check = new InterfaceCheck();
			check.setId(rs.getInt(1));
			check.setInterfaceId(rs.getInt(2));
			check.setCheckMode(rs.getInt(3));
			check.setCheckPoint(rs.getString(4));
			check.setCreateUser(rs.getInt(5));
			check.setCreateDate(rs.getDate(6));
			check.setIsDelete(rs.getInt(7));
			check.setCheckDesc(rs.getString(8));
			checks.add(check);
		}
		return checks;
	}
	public List<InterfaceCheck> findInterfaceCheckByInterface(int interfaceId) throws Exception{
		List<InterfaceCheck> checks = new ArrayList<InterfaceCheck>();
		String sql = "SELECT ID,INTERFACE_ID,CHECK_MODE,CHECK_POINT,CREATE_USER,CREATE_DATE,IS_DELETE,CHECK_DESC FROM T_CHECK WHERE INTERFACE_ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			InterfaceCheck check = new InterfaceCheck();
			check.setId(rs.getInt(1));
			check.setInterfaceId(rs.getInt(2));
			check.setCheckMode(rs.getInt(3));
			check.setCheckPoint(rs.getString(4));
			check.setCreateUser(rs.getInt(5));
			check.setCreateDate(rs.getDate(6));
			check.setIsDelete(rs.getInt(7));
			check.setCheckDesc(rs.getString(8));
			checks.add(check);
		}
		return checks;
	}
	public void addInterfaceCheck(InterfaceCheck check) throws Exception{
		String sql = "INSERT INTO T_CHECK(INTERFACE_ID,CHECK_MODE,CHECK_POINT,CREATE_USER,CREATE_DATE,IS_DELETE,CHECK_DESC)VALUES(?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, check.getInterfaceId());
		ps.setInt(2,check.getCheckMode());
		ps.setString(3,check.getCheckPoint());
		ps.setInt(4, check.getCreateUser());
		ps.setDate(5, check.getCreateDate());
		ps.setInt(6, check.getIsDelete());
		ps.setString(7, check.getCheckDesc());
		ps.executeUpdate();
	}
	public void modifyInterfaceCheck(InterfaceCheck check) throws Exception{
		String sql = "UPDATE T_CHECK SET INTERFACE_ID=?,CHECK_MODE=?,CHECK_POINT=?,CREATE_USER=?,CREATE_DATE=?,IS_DELETE=?,CHECK_DESC=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, check.getInterfaceId());
		ps.setInt(2,check.getCheckMode());
		ps.setString(3,check.getCheckPoint());
		ps.setInt(4, check.getCreateUser());
		ps.setDate(5, check.getCreateDate());
		ps.setInt(6, check.getIsDelete());
		ps.setString(7, check.getCheckDesc());
		ps.setInt(8, check.getId());
		
		ps.executeUpdate();
	}
	public void deleteInterfaceCheckByInterface(int id) throws Exception{
		String sql = "DELETE FROM T_CHECK WHERE INTERFACE_ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
	public void deleteInterfaceCheck(int id) throws Exception{
		String sql = "DELETE FROM T_CHECK WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
