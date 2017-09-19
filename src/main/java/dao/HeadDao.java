package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils_Mysql;
import entity.Head;

public class HeadDao {
	public List<Head> findHeadByInterface(int interfaceId) throws Exception{
		List<Head> heads = new ArrayList<Head>();
		String sql = "SELECT ID,INTERFACE_ID,HEAD_KEY,HEAD_VALUE,HEAD_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_HEAD WHERE INTERFACE_ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, interfaceId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Head head = new Head();
			head.setId(rs.getInt(1));
			head.setInterfaceId(rs.getInt(2));
			head.setHeadKey(rs.getString(3));
			head.setHeadValue(rs.getString(4));
			head.setHeadVariable(rs.getString(5));
			head.setCreateUser(rs.getInt(6));
			head.setCreateDate(rs.getDate(7));
			head.setIsDelete(rs.getInt(8));
			heads.add(head);
		}
		return heads;
	}
	public List<Head> findHeadAll() throws Exception{
		List<Head> heads = new ArrayList<Head>();
		String sql = "SELECT ID,INTERFACE_ID,HEAD_KEY,HEAD_VALUE,HEAD_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE FROM T_HEAD";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Head head = new Head();
			head.setId(rs.getInt(1));
			head.setInterfaceId(rs.getInt(2));
			head.setHeadKey(rs.getString(3));
			head.setHeadValue(rs.getString(4));
			head.setHeadVariable(rs.getString(5));
			head.setCreateUser(rs.getInt(6));
			head.setCreateDate(rs.getDate(7));
			head.setIsDelete(rs.getInt(8));
			heads.add(head);
		}
		return heads;
	}
	public void addHead(Head head) throws Exception{
		String sql = "INSERT INTO T_HEAD(INTERFACE_ID,HEAD_KEY,HEAD_VALUE,HEAD_VARIABLE,CREATE_USER,CREATE_DATE,IS_DELETE)VALUES(?,?,?,?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, head.getInterfaceId());
		ps.setString(2,head.getHeadKey());
		ps.setString(3,head.getHeadValue());
		ps.setString(4, head.getHeadVariable());
		ps.setInt(5, head.getCreateUser());
		ps.setDate(6, head.getCreateDate());
		ps.setInt(7, head.getIsDelete());
		ps.executeUpdate();
	}
	public void modifyHead(Head head) throws Exception{
		String sql = "UPDATE T_HEAD SET INTERFACE_ID=?,HEAD_KEY=?,HEAD_VALUE=?,HEAD_VARIABLE=?,CREATE_USER=?,CREATE_DATE=?,IS_DELETE=? WHERE ID=?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, head.getInterfaceId());
		ps.setString(2,head.getHeadKey());
		ps.setString(3,head.getHeadValue());
		ps.setString(4, head.getHeadVariable());
		ps.setInt(5, head.getCreateUser());
		ps.setDate(6, head.getCreateDate());
		ps.setInt(7, head.getIsDelete());
		ps.setInt(8, head.getId());
		ps.executeUpdate();
	}
	public void deleteHeadByInterface(int id) throws Exception{
		String sql = "DELETE FROM T_HEAD WHERE INTERFACE_ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
	public void deleteHead(int id) throws Exception{
		String sql = "DELETE FROM T_HEAD WHERE ID = ?";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
	}
}
