package servlet;

import java.util.List;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import service.TimerTaskService;
import service.UserService;
import util.ExecuteInterface;
import util.GlobalVariable;
import entity.TimerTask;
import entity.User;

public class LoadTimerServlet extends HttpServlet {

	private static final long serialVersionUID = -6381010040196310363L;
	
	public void init() throws ServletException {
		try {
			TimerTaskService timerTaskService = new TimerTaskService();
			List<TimerTask> timerTasks = timerTaskService.findTimerTask();
			UserService userService = new UserService();
			for (TimerTask timerTask : timerTasks) {
				final int taskId = timerTask.getTaskId();
				final User login = userService.findUserById(timerTask.getUserId());
				Timer timer = new Timer();
				timer.schedule(new java.util.TimerTask() {
					public void run() {
						try {
							ExecuteInterface.executeTask(taskId, login);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, timerTask.getExecuteTime(),timerTask.getLoopTime());
				GlobalVariable.timerMap.put("timer_task_"+taskId,timer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 

}
