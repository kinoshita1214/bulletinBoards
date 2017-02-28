package bulletinBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request , response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> managements = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request , managements) == true) {

			User user = new User();
			user.setLogin_id(request.getParameter("login_id"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_name(request.getParameter("branch_name"));
			user.setDepartment_name(request.getParameter("department_name"));

			new UserService().register(user);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages" , managements);
			response.sendRedirect("/signup");
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> managements) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(login_id) == true) {
			managements.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			managements.add("パスワードを入力してください");
		}
		if (managements.size() == 0) {
			return true;
		} else {
		return false;
		}
	}
}