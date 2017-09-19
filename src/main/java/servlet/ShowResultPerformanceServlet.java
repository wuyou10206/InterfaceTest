package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.InterfaceService;
import service.ResultPerformanceService;
import entity.Interface;
import entity.ResultPerformance;
import entity.User;

public class ShowResultPerformanceServlet extends HttpServlet {

	private static final long serialVersionUID = -3903348529431112841L;

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
			int resultPerformanceId = Integer.parseInt(request.getParameter("result_performance_id"));
			ResultPerformanceService resultPerformanceService = new ResultPerformanceService();
			ResultPerformance rp = resultPerformanceService.findResultPerformanceById(resultPerformanceId);
			Interface inter = new InterfaceService().findInterfaceById(rp.getInterfaceId());
			request.setAttribute("rp",rp);
			request.setAttribute("inter",inter);
			request.getRequestDispatcher("/WEB-INF/jsp/show_result_performance.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
