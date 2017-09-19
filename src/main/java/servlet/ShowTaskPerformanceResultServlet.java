package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TaskPerformanceResultService;
import service.TaskPerformanceService;
import entity.TaskPerformance;
import entity.TaskPerformanceResult;
import entity.User;

public class ShowTaskPerformanceResultServlet extends HttpServlet {

	private static final long serialVersionUID = -603793820976320273L;

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
			TaskPerformance tp = taskPerformanceService.findTaskPerformanceByMaxDate(taskId);
			List<TaskPerformanceResult> tprs = new TaskPerformanceResultService().findTaskPerformanceResultByTaskPerformanceResultIds(tp.getTaskPerformanceResultIds());
			request.setAttribute("tp", tp);
			request.setAttribute("tprs", tprs);
			request.setAttribute("task_id", taskId);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_performance_result.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
