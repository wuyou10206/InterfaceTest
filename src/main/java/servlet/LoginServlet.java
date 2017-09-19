package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import util.MD5;
import entity.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -8336037366844506186L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			String userName = request.getParameter("username");
			String pwd = request.getParameter("password");
			String checkCode = request.getParameter("check_code");
			String number = (String) session.getAttribute("number");
			if(number==null||(!checkCode.equalsIgnoreCase(number))){
				request.setAttribute("login_error", "验证码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			if(userName==null||pwd==null){
				request.setAttribute("login_error", "账号或密码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			UserService userService = new UserService();
			User u = userService.findUserByNameAndPwd(userName, MD5.md5(pwd));
			if(u==null){
				request.setAttribute("login_error", "账号或密码错误");
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			session.setAttribute("login_user",u);
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
