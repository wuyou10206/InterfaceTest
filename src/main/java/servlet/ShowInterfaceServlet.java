package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CheckService;
import service.HeadService;
import service.InterfacePerformanceService;
import service.InterfaceService;
import service.OutValueService;
import service.ParameterService;
import service.ResultService;
import entity.Head;
import entity.Interface;
import entity.InterfaceCheck;
import entity.InterfacePerformance;
import entity.OutValue;
import entity.Parameter;
import entity.Result;
import entity.User;

public class ShowInterfaceServlet extends HttpServlet {
	private static final long serialVersionUID = -7215971664932295123L;

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
			InterfaceService interfaceService = new InterfaceService();
			Interface inter = interfaceService.findInterfaceById(interfaceId);
			ParameterService parameterService = new ParameterService();
			List<Parameter> parameters = parameterService.findParameterByInterface(interfaceId);
			HeadService headService = new HeadService();
			List<Head> heads = headService.findHeadByInterface(interfaceId);
			CheckService checkService = new CheckService();
			List<InterfaceCheck> checks = checkService.findCheckByInterface(interfaceId);
			OutValueService outValueService = new OutValueService();
			List<OutValue> outValues = outValueService.findOutValueByInterface(interfaceId);
			ResultService resultService = new ResultService();
			Result result = resultService.findResultByMaxDate(interfaceId);
			InterfacePerformanceService interfacePerformanceService = new InterfacePerformanceService();
			InterfacePerformance ip = interfacePerformanceService.findInterfacePerformanceByMaxDate(interfaceId);
			request.setAttribute("ip", ip);
			request.setAttribute("inter",inter);
			request.setAttribute("parameters",parameters);
			request.setAttribute("heads",heads);
			request.setAttribute("checks",checks);
			request.setAttribute("outValues",outValues);
			request.setAttribute("result",result);
			
			request.getRequestDispatcher("/WEB-INF/jsp/show_interface.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
