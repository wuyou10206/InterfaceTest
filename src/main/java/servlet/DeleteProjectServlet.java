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
import service.ModuleService;
import service.ProjectService;
import service.TaskService;
import util.AlertJson;
import entity.Module;
import entity.Task;
import entity.User;

public class DeleteProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 7170668544633500445L;

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
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			List<Module> modules = new ModuleService().findModuleByProject(projectId);
			List<Task> tasks = new TaskService().findTaskByProject(projectId);
			JSONObject json = null;
			if(modules.size()==0&&tasks.size()==0){
				ProjectService projectService = new ProjectService();
				projectService.deleteProjectByDelete(projectId);
				json = AlertJson.successJson("项目删除成功","project_list","project_list");
			}else{
				json = AlertJson.failedJson("该项目下有模块或任务，不能删除，请先删除模块或任务", "project_list","project_list");
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
