package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import service.InterfaceService;
import service.ModuleService;
import util.AlertJson;
import entity.Interface;
import entity.User;

public class DeleteModuleServlet extends HttpServlet {

	private static final long serialVersionUID = 9145891303419818981L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			int pid = Integer.parseInt(request.getParameter("project_id"));
			List<Interface> interfaces = new InterfaceService().findInterfaceByModule(moduleId);
			JSONObject json = null;
			if(interfaces.size()==0){
				ModuleService moduleService = new ModuleService();
				moduleService.deleteModuleByDelete(moduleId);
				json = AlertJson.successJson("模块删除成功","show_project_module_"+pid,"show_project_module_"+pid);
			}else{
				json = AlertJson.failedJson("该模块下有接口，不能删除，请先删除接口", "show_project_module_"+pid,"show_project_module_"+pid);
			}
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
