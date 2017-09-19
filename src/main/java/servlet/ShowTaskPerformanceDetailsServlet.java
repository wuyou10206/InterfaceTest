package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ResultPerformanceService;
import service.TaskPerformanceResultService;
import entity.ResultPerformance;
import entity.TaskPerformanceResult;
import entity.User;

public class ShowTaskPerformanceDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = -641817362600757001L;

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
			int taskPerformanceResultId = Integer.parseInt(request.getParameter("task_performance_result_id"));
			TaskPerformanceResultService taskPerformanceResultService = new TaskPerformanceResultService();
			TaskPerformanceResult taskPerformanceResult = taskPerformanceResultService.findTaskPerformanceResultByID(taskPerformanceResultId);
			List<ResultPerformance> results = new ResultPerformanceService().findResultByResultPerformanceIds(taskPerformanceResult.getResultIds());
			request.setAttribute("results",results);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_performance_details.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
