package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtils_Mysql;
import entity.User;

public class UserDao {
	public void addUser(User user) throws Exception{
		String sql = "INSERT INTO T_USER(NAME,ALIAS,PWD,EMAIL)VALUES(?,?,?,?)";
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setString(2, user.getAlias());
		ps.setString(3, user.getPwd());
		ps.setString(4, user.getEmail());
		ps.executeUpdate();
	}
	public User findUserById(int id) throws Exception{
		String sql = "SELECT ID,NAME,ALIAS,PWD,EMAIL FROM T_USER WHERE ID=?";
		User user = null;
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setAlias(rs.getString(3));
			user.setPwd(rs.getString(4));
			user.setEmail(rs.getString(5));
		}
		return user;
	}
	public User findUserByNameAndPwd(String name,String pwd) throws Exception{
		String sql = "SELECT ID,NAME,ALIAS,PWD,EMAIL FROM T_USER WHERE NAME=? AND PWD=?";
		User user = null;
		Connection connection = DBUtils_Mysql.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setAlias(rs.getString(3));
			user.setPwd(rs.getString(4));
			user.setEmail(rs.getString(5));
		}
		return user;
	}
}
