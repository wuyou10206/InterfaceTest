package service;

import util.DBUtils_Mysql;
import dao.UserDao;
import entity.User;

public class UserService {
	public User findUserByNameAndPwd(String name,String pwd) throws Exception{
		UserDao userDao = new UserDao();
		User user = userDao.findUserByNameAndPwd(name, pwd);
		DBUtils_Mysql.close();
		return user;
	}
	public User findUserById(int id) throws Exception{
		UserDao userDao = new UserDao();
		User user = userDao.findUserById(id);
		DBUtils_Mysql.close();
		return user;
	}
}
