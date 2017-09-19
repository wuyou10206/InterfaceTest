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
import entity.Task;
import entity.User;

public class InterfaceUpServlet extends HttpServlet {

	private static final long serialVersionUID = -4907661641802577854L;

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
			int interfaceId = Integer.parseInt(request.getParameter("interface_id"));
			JSONObject json = null;
			TaskService taskService = new TaskService();
			Task task = taskService.findTaskByID(taskId);
			String[] interfaceIds = task.getInterfaceIds().split(",");
			for (int i = 0; i < interfaceIds.length; i++) {
				String interId = interfaceIds[i];
				if(interId.equals(interfaceId+"")){
					interfaceIds[i] = interfaceIds[i-1];
					interfaceIds[i-1]=interId;
					break;
				}
			}
			String interfaceIdsStr = "";
			for (int i = 0; i < interfaceIds.length; i++) {
				interfaceIdsStr+=","+interfaceIds[i];
			}
			taskService.modifyTask(taskId, interfaceIdsStr.substring(1));
			json = AlertJson.successJson("向上移动成功","show_task_"+taskId,"show_task_"+taskId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
