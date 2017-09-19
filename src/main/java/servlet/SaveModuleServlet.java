package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import service.ModuleService;
import util.AlertJson;
import entity.User;

public class SaveModuleServlet extends HttpServlet {

	private static final long serialVersionUID = 410308100146754878L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = null;
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			out = response.getWriter();
			User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			String moduleName = request.getParameter("module_name");
			int pid = Integer.parseInt(request.getParameter("project_id"));
			ModuleService moduleService = new ModuleService();
			if(!moduleService.isCanAddModule(moduleName,moduleId,pid)){
				json = AlertJson.failedJson("模块名字在该项目下已经存在，请重新修改","module_list" ,"module_list");
			}else{
				int userId = login.getId();
				Date addTime = new Date(System.currentTimeMillis());
				int isDelete = 1;
				moduleService.modifyModule(moduleId,moduleName, userId, addTime, pid, isDelete);
				json = AlertJson.successJson("模块修改成功", "show_project_module_"+pid,"show_project_module_"+pid);
			}
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
