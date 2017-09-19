package service;

import java.sql.Date;
import java.util.List;

import conf.Constant;

import util.DBUtils_Mysql;
import util.TransactionManager;
import dao.ModuleDao;
import entity.Module;

public class ModuleService {
	private ModuleDao moduleDao = new ModuleDao();
	public List<Module> findModule() throws Exception{
		List<Module> modules = moduleDao.findModuleAll();
		DBUtils_Mysql.close();
		return modules;
	}
	public List<Module> findModuleByProject(int pid) throws Exception{
		List<Module> modules = moduleDao.findModuleByProject(pid);
		DBUtils_Mysql.close();
		return modules;
	}
	public Module findModuleByID(int id) throws Exception{
		Module module = moduleDao.findModuleById(id);
		DBUtils_Mysql.close();
		return module;
	}
	public void addModule(String name,int userId,Date addTime,int pid,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Module module = new Module();
			module.setModuleName(name);
			module.setUserId(userId);
			module.setAddTime(addTime);
			module.setPid(pid);
			module.setIsDelete(isDelete);
			moduleDao.addModule(module);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteModuleByDelete(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Module module = moduleDao.findModuleById(id);
			module.setIsDelete(Constant.DELETE);
			moduleDao.modifyModule(module);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void deleteModule(int id) throws Exception{
		try {
			TransactionManager.beginTransaction();
			moduleDao.deleteModule(id);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public void modifyModule(int id,String name,int userId,Date addTime,int pid,int isDelete) throws Exception{
		try {
			TransactionManager.beginTransaction();
			Module module = new Module();
			module.setId(id);
			module.setModuleName(name);
			module.setUserId(userId);
			module.setAddTime(addTime);
			module.setPid(pid);
			module.setIsDelete(isDelete);
			moduleDao.modifyModule(module);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public boolean isCanAddModule(String moduleName,int moduleId,int pid) throws Exception{
		List<Module> modules = findModuleByProject(pid);
		for (Module module : modules) {
			if(module.getId()!=moduleId&&module.getModuleName().equals(moduleName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
	public boolean isCanAddModule(String moduleName,int pid) throws Exception{
		List<Module> modules = findModuleByProject(pid);
		for (Module module : modules) {
			if(module.getModuleName().equals(moduleName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
	public boolean isCanAddModule(String moduleName) throws Exception{
		List<Module> modules = findModule();
		for (Module module : modules) {
			if(module.getModuleName().equals(moduleName)){
				return false;
			}
		}
		DBUtils_Mysql.close();
		return true;
	}
}
