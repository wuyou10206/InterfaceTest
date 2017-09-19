package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.InterfaceService;

import entity.Interface;
import entity.User;

public class InterfaceListServlet extends HttpServlet {
	private static final long serialVersionUID = -2098487225566110505L;

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
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			InterfaceService interfaceService = new InterfaceService();
			List<Interface> interfaces = interfaceService.findInterfaceByModule(moduleId);
			request.setAttribute("interfaces",interfaces);
			request.setAttribute("module_id",moduleId);
			request.getRequestDispatcher("/WEB-INF/jsp/interface_list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
