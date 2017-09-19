package servlet;

import java.io.IOException;

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

public class ShowResultServlet extends HttpServlet {

	private static final long serialVersionUID = -4323305938848963202L;

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
			int resultId = Integer.parseInt(request.getParameter("result_id"));
			ResultService rs = new ResultService();
			Result r = rs.findResultById(resultId);
			Interface inter = new InterfaceService().findInterfaceById(r.getInterfaceId());
			request.setAttribute("result",r);
			request.setAttribute("inter",inter);
			request.getRequestDispatcher("/WEB-INF/jsp/show_result.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
