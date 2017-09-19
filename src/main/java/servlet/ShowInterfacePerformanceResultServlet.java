package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.InterfacePerformanceService;
import service.ResultPerformanceService;
import entity.InterfacePerformance;
import entity.ResultPerformance;
import entity.User;

public class ShowInterfacePerformanceResultServlet extends HttpServlet {

	private static final long serialVersionUID = -2649526730122194288L;

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
			InterfacePerformanceService interfacePerformanceService = new InterfacePerformanceService();
			InterfacePerformance ip = interfacePerformanceService.findInterfacePerformanceByMaxDate(interfaceId);
			List<ResultPerformance> rps = new ResultPerformanceService().findResultByResultPerformanceIds(ip.getResultPerformanceIds());
			request.setAttribute("ip", ip);
			request.setAttribute("rps", rps);
			request.setAttribute("interface_id", interfaceId);
			request.getRequestDispatcher("/WEB-INF/jsp/show_interface_performance_result.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
