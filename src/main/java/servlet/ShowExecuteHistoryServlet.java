package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.InterfaceService;
import service.ResultService;
import entity.Interface;
import entity.Result;
import entity.User;

public class ShowExecuteHistoryServlet extends HttpServlet {

	private static final long serialVersionUID = 5295860624751001527L;

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
			int interfaceId = Integer.parseInt(request.getParameter("interface_id"));
			Interface inter = new InterfaceService().findInterfaceById(interfaceId);
			ResultService resultService = new ResultService();
			List<Result> results = resultService.findResultByInterface(interfaceId);
			request.setAttribute("inter",inter);
			request.setAttribute("results",results);
			request.getRequestDispatcher("/WEB-INF/jsp/show_execute_history.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
