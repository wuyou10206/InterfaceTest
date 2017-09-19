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
import service.ProjectService;
import util.AlertJson;
import entity.User;

public class ModifySaveProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 4599085557606173354L;

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
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			String projectName = request.getParameter("project_name");
			ProjectService projectService = new ProjectService();
			if(!projectService.isCanAddProject(projectName)){
				json = AlertJson.failedJson("项目名字已经存在，请重新修改","project_list" ,"project_list");
			}else{
				int userId = login.getId();
				Date addTime = new Date(System.currentTimeMillis());
				int isDelete = 1;
				projectService.modifyProject(projectId,projectName, userId, addTime, isDelete);
				json = AlertJson.successJson("项目修改成功", "project_list","project_list");
			}
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
