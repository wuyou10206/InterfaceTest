package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TaskPerformanceService;
import entity.TaskPerformance;
import entity.User;

public class ShowTaskPerformanceHistory extends HttpServlet {

	private static final long serialVersionUID = 4869103313986923689L;

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
			TaskPerformanceService taskPerformanceService = new TaskPerformanceService();
			List<TaskPerformance> tps = taskPerformanceService.findTaskPerformanceByTask(taskId);
			request.setAttribute("tps", tps);
			request.setAttribute("task_id", taskId);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_performance_history.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
