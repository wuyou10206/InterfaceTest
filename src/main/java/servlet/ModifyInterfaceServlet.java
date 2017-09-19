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
import service.InterfaceService;
import service.OutValueService;
import service.ParameterService;
import entity.Head;
import entity.Interface;
import entity.InterfaceCheck;
import entity.OutValue;
import entity.Parameter;
import entity.User;

public class ModifyInterfaceServlet extends HttpServlet {
	private static final long serialVersionUID = 6378378200316172931L;

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
			request.setAttribute("inter",inter);
			request.setAttribute("parameters",parameters);
			request.setAttribute("heads",heads);
			request.setAttribute("checks",checks);
			request.setAttribute("outValues",outValues);
			request.getRequestDispatcher("/WEB-INF/jsp/modify_interface.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
