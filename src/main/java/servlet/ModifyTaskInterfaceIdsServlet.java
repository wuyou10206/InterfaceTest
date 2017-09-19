package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ModuleService;
import service.TaskService;
import entity.Module;
import entity.Task;
import entity.User;

public class ModifyTaskInterfaceIdsServlet extends HttpServlet {

	private static final long serialVersionUID = 7617144418860553899L;

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
			int taskId = Integer.parseInt(request.getParameter("task_id"));
			TaskService taskService = new TaskService();
			Task task = taskService.findTaskByID(taskId);
			ModuleService moduleService = new ModuleService();
			List<Module> modules = moduleService.findModuleByProject(task.getProjectId());
			request.setAttribute("task",task);
			request.setAttribute("modules",modules);
			request.getRequestDispatcher("/WEB-INF/jsp/modify_task_interfaceIds.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
