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
import service.CheckService;
import service.HeadService;
import service.InterfaceService;
import service.OutValueService;
import service.ParameterService;
import util.AlertJson;
import entity.User;

public class ModifyInterfaceSaveServlet extends HttpServlet {

	private static final long serialVersionUID = -1429642547289006190L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int interfaceId = Integer.parseInt(request.getParameter("interface_id"));
			String interfaceName = request.getParameter("interface_name");
			String interfaceAddress = request.getParameter("interface_address");
			int requestMode = Integer.parseInt(request.getParameter("request_mode"));
			int httpCode = Integer.parseInt(request.getParameter("http_code"));
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			int createUser = login.getId();
			Date createDate = new Date(System.currentTimeMillis());
			int isDelete = 1;
			InterfaceService interfaceService = new InterfaceService();
			interfaceService.modifyInterface(interfaceId, interfaceName, interfaceAddress, requestMode, httpCode, moduleId, createUser, createDate, isDelete);
			int parameterCount = Integer.parseInt(request.getParameter("parameterid"));
			ParameterService parameterService = new ParameterService();
			parameterService.deleteParameterByInterface(interfaceId);
			for(int i=1;i<parameterCount;i++){
				String parameterKey = request.getParameter("parameter_key"+i);
				String parameterValue = request.getParameter("parameter_value"+i);
				String parameterVariable = request.getParameter("parameter_variable"+i);
				parameterService.addParameter(interfaceId, parameterKey, parameterValue,parameterVariable, createUser, createDate, isDelete);
			}
			HeadService headService = new HeadService();
			headService.deleteHeadByInterface(interfaceId);
			int cookieCount = Integer.parseInt(request.getParameter("headid"));
			for(int i=1;i<cookieCount;i++){
				String headKey = request.getParameter("head_key"+i);
				String headValue = request.getParameter("head_value"+i);
				String headVariable = request.getParameter("head_variable"+i);
				headService.addHead(interfaceId, headKey, headValue, headVariable,createUser, createDate, isDelete);
			}
			CheckService checkService = new CheckService();
			checkService.deleteCheckByInterface(interfaceId);
			int checkCount = Integer.parseInt(request.getParameter("checkid"));
			for(int i=1;i<checkCount;i++){
				int checkMode = Integer.parseInt(request.getParameter("check_mode"+i));
				String checkPoint = request.getParameter("check_point"+i);
				String checkDesc = request.getParameter("check_desc"+i);
				checkService.addCheck(interfaceId, checkMode, checkPoint, createUser, createDate, isDelete,checkDesc);
			}
			OutValueService outValueService = new OutValueService();
			outValueService.deleteCheckByInterface(interfaceId);
			int outValueCount = Integer.parseInt(request.getParameter("outValueid"));
			for(int i=1;i<outValueCount;i++){
				int valueSpace = Integer.parseInt(request.getParameter("value_space"+i));
				String outValueKey = request.getParameter("out_value_key"+i);
				String outValueName = request.getParameter("out_value_name"+i);
				outValueService.addOutValue(interfaceId, valueSpace, outValueKey, outValueName, createUser, createDate, isDelete);
			}
			JSONObject json = AlertJson.successJson("接口修改成功", "show_interface_"+interfaceId,"show_interface_"+interfaceId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
