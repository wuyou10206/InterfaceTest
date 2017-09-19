package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import service.TimerTaskService;
import util.AlertJson;
import util.GlobalVariable;
import entity.User;

public class DeleteTimerTaskServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6809645260516132854L;

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
			Timer timer = GlobalVariable.timerMap.get("timer_task_"+taskId);
			timer.cancel();
			GlobalVariable.timerMap.remove("timer_task_"+taskId);
			JSONObject json = null;
			TimerTaskService timerTaskService = new TimerTaskService();
			timerTaskService.deleteTimerTaskByTaskByDelete(taskId);
			json = AlertJson.successJson("任务删除成功","show_task_"+taskId,"show_task_"+taskId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
