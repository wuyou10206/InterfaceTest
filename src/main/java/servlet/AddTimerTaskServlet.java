package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

public class AddTimerTaskServlet extends HttpServlet {

	private static final long serialVersionUID = -1228985427148026539L;

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
			request.setAttribute("task_id",taskId);
			request.getRequestDispatcher("/WEB-INF/jsp/add_timer_task.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
