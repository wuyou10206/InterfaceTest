package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import service.TaskService;
import util.AlertJson;
import entity.User;

public class SaveTaskInterfaceIds extends HttpServlet {
	
	private static final long serialVersionUID = -6765610897585005767L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = null;
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			out = response.getWriter();
			User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			int taskId = Integer.parseInt(request.getParameter("task_id"));
			String taskName = request.getParameter("task_name");
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			TaskService taskService = new TaskService();
			if(!taskService.isCanAddTask(taskName,projectId)){
				json = AlertJson.failedJson("任务名称在该项目下已经存在，请重新修改","show_task_"+taskId,"show_task_"+taskId);
			}else{
				String interfaceIds = request.getParameter("interfaceIds");
				int userId = login.getId();
				Date addTime = new Date(System.currentTimeMillis());
				int isDelete = 1;
				taskService.modifyTask(taskId,taskName, interfaceIds, userId, addTime, projectId, isDelete);
				json = AlertJson.successJson("任务修改成功", "show_task_"+taskId,"show_task_"+taskId);
			}
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
