package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.AlertJson;
import util.ExecuteInterface;
import entity.User;

public class ExecuteInterfaceServlet extends HttpServlet {

	private static final long serialVersionUID = -4862160525524277770L;

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
			ExecuteInterface.executeInterface(interfaceId, login);
			net.sf.json.JSONObject json = AlertJson.successJson("执行完成", "show_interface_"+interfaceId,"show_interface_"+interfaceId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
