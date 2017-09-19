package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.InterfaceService;
import service.TaskPerformanceService;
import service.TaskResultService;
import service.TimerTaskService;
import entity.Interface;
import entity.TaskPerformance;
import entity.TaskResult;
import entity.TimerTask;
import entity.User;

public class ShowTaskInterfaceServlet extends HttpServlet {

	private static final long serialVersionUID = 3650218013333151685L;

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
			InterfaceService interfaceService = new InterfaceService();
			List<Interface> interfaces = interfaceService.findInterfaceByTask(taskId);
			TaskResultService taskResultService = new TaskResultService();
			TaskResult taskResult = taskResultService.findTaskResultByMaxDate(taskId);
			TimerTaskService timerTaskService = new TimerTaskService();
			List<TimerTask> timerTasks = timerTaskService.findTimerTaskByTask(taskId);
			TimerTask timerTask = null;
			if(timerTasks!=null&&timerTasks.size()>0){
				timerTask = timerTasks.get(0);
			}
			TaskPerformanceService taskPerformanceService = new TaskPerformanceService();
			TaskPerformance tp = taskPerformanceService.findTaskPerformanceByMaxDate(taskId);
			request.setAttribute("tp",tp);
			request.setAttribute("timerTask",timerTask);
			request.setAttribute("interfaces",interfaces);
			request.setAttribute("taskResult",taskResult);
			request.setAttribute("task_id",taskId);
			request.getRequestDispatcher("/WEB-INF/jsp/show_task_interface.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
