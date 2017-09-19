package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TimerTaskService;
import util.AlertJson;
import util.ExecuteInterface;
import util.GlobalVariable;
import entity.User;

public class SaveTimerTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 3669517202408574723L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			final User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			final int taskId = Integer.parseInt(request.getParameter("task_id"));
			String executeTimeStr = request.getParameter("execute_time");
			long loopTime = Long.parseLong(request.getParameter("loop_time"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date executeTimeDate = format.parse(executeTimeStr);
			Timestamp executeTime = new Timestamp(executeTimeDate.getTime());
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						ExecuteInterface.executeTask(taskId, login);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, executeTimeDate,loopTime);
			GlobalVariable.timerMap.put("timer_task_"+taskId,timer);
			TimerTaskService timerTaskService = new TimerTaskService();
			int isDelete = 1;
			Timestamp createTime = new Timestamp(new Date().getTime());
			timerTaskService.addTimerTask(taskId, executeTime, loopTime, login.getId(), createTime, isDelete);
			net.sf.json.JSONObject json = AlertJson.successJson("设置定时任务成功", "show_task_"+taskId,"show_task_"+taskId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
