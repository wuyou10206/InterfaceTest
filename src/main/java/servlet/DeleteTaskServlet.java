package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import service.TaskService;
import util.AlertJson;
import entity.User;

public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 2291522826622106726L;

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
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			JSONObject json = null;
			TaskService taskService = new TaskService();
			taskService.deleteTaskByDelete(taskId);
			json = AlertJson.successJson("任务删除成功","show_project_task_"+projectId,"show_project_task_"+projectId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
