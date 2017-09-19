package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import conf.Constant;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.InterfaceDao;
import entity.Interface;
import entity.Task;

public class InterfaceService {
	private InterfaceDao interfaceDao = new InterfaceDao();
	private TaskService taskService = new TaskService();
	public List<Interface> findInterface() throws Exception{
		List<Interface> Interfaces = interfaceDao.findInterfaceAll();
		DBUtils_Mysql.close();
		return Interfaces;
	}
	public List<Interface> findInterfaceByModule(int moduleId) throws Exception{
		List<Interface> Interfaces = interfaceDao.findInterfaceByModule(moduleId);
		DBUtils_Mysql.close();
		return Interfaces;
	}
	public List<Interface> findInterfaceByTask(int taskId) throws Exception{
		Task task = taskService.findTaskByID(taskId);
		String[] interfaceIds = task.getInterfaceIds().split(",");
		List<Interface> interfaces = new ArrayList<Interface>();
		for(String interfaceId:interfaceIds){
			int interface_id = Integer.parseInt(interfaceId);
			Interface inter = findInterfaceById(interface_id);
			interfaces.add(inter);
		}
		return interfaces;
	}
	public Interface findInterfaceById(int id) throws Exception{
		Interface inter = interfaceDao.findInterfaceById(id);
		DBUtils_Mysql.close();
		return inter;
	}
	public int addInterface(String interfaceName,String interfaceAddress,int requestMode,int httpCode,int moduleId,int createUser,Date createDate,int isDelete) throws Exception{
		int interface_id = 0;
		try {
			TransactionManager.beginTransaction();
			Interface inter = new Interface();
			inter.setInterfaceName(interfaceName);
			inter.setInterfaceAddress(interfaceAddress);
			inter.setRequestMode(requestMode);
			inter.setHttpCode(httpCode);
			inter.setModuleId(moduleId);
			inter.setCreateUser(createUser);
			inter.setCreateDate(createDate);
			inter.setIsDelete(isDelete);
			interface_id = interfaceDao.addInterface(inter);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		return interface_id;
	}
	public void deleteInterface(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			interfaceDao.deleteInterface(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteInterfaceByDelete(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Interface inter = interfaceDao.findInterfaceById(id);
			inter.setIsDelete(Constant.DELETE);
			interfaceDao.modifyInterface(inter);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyInterface(int id,String interfaceName,String interfaceAddress,int requestMode,int httpCode,int moduleId,int createUser,Date createDate,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Interface inter = new Interface();
			inter.setId(id);
			inter.setInterfaceName(interfaceName);
			inter.setInterfaceAddress(interfaceAddress);
			inter.setRequestMode(requestMode);
			inter.setHttpCode(httpCode);
			inter.setModuleId(moduleId);
			inter.setCreateUser(createUser);
			inter.setCreateDate(createDate);
			inter.setIsDelete(isDelete);
			interfaceDao.modifyInterface(inter);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
