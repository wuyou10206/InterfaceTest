package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TaskService;
import entity.Task;
import entity.User;

public class ShowTaskListServlet extends HttpServlet {

	private static final long serialVersionUID = -4254641119796000829L;

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
			TaskService taskService = new TaskService();
			List<Task> tasks = taskService.findTaskByProject(projectId);
			request.setAttribute("tasks",tasks);
			request.setAttribute("project_id", projectId);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
