package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import service.InterfaceService;
import util.AlertJson;

import entity.User;

public class DeleteInterfaceServlet extends HttpServlet {

	private static final long serialVersionUID = 8029278135270376642L;

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
			int moduleId = Integer.parseInt(request.getParameter("module_id"));
			InterfaceService interfaceService = new InterfaceService();
			interfaceService.deleteInterfaceByDelete(interfaceId);
			JSONObject json = AlertJson.successJson("接口添加成功", "show_module_"+moduleId,"show_module_"+moduleId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
