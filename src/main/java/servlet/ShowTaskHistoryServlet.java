package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TaskResultService;
import entity.TaskResult;
import entity.User;

public class ShowTaskHistoryServlet extends HttpServlet {

	private static final long serialVersionUID = -2399251428707541416L;

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
			TaskResultService taskResultService = new TaskResultService();
			List<TaskResult> taskResults = taskResultService.findTaskResultByTask(taskId);
			request.setAttribute("taskResults",taskResults);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_history.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
