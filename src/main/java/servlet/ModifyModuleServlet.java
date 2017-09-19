package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ModuleService;
import entity.Module;
import entity.User;

public class ModifyModuleServlet extends HttpServlet {

	private static final long serialVersionUID = -6091556518922530767L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			Module module = new ModuleService().findModuleByID(moduleId);
			request.setAttribute("module",module);
			request.getRequestDispatcher("/WEB-INF/jsp/modify_module.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
