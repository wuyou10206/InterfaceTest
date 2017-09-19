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

public class SaveTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 5770570512256747891L;

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
			String taskName = request.getParameter("task_name");
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			TaskService taskService = new TaskService();
			if(!taskService.isCanAddTask(taskName,projectId)){
				json = AlertJson.failedJson("该项目下已经有该任务了","show_project_task_"+projectId,"show_project_task_"+projectId);
			}else{
				String interfaceIds = request.getParameter("interfaceIds");
				int userId = login.getId();
				Date addTime = new Date(System.currentTimeMillis());
				int isDelete = 1;
				taskService.addTask(taskName, interfaceIds, userId, addTime, projectId, isDelete);
				json = AlertJson.successJson("任务添加成功", "show_project_task_"+projectId,"show_project_task_"+projectId);
			}
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
