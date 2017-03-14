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

import bulletinBoard.beans.Branch;
import bulletinBoard.beans.Department;
import bulletinBoard.beans.User;
import bulletinBoard.service.BranchService;
import bulletinBoard.service.DepartmentService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Branch> branch = new BranchService().getBranch();
		request.setAttribute("branch" , branch);
		List<Department> department = new DepartmentService().getDepartment();
		request.setAttribute("department" , department);
		request.getRequestDispatcher("signup.jsp").forward(request , response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin_id(request.getParameter("login_id"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		user.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

		if (isValid(request , messages) == true) {

			new UserService().register(user);

			response.sendRedirect("management");
		} else {
			session.setAttribute("errorMessages" , messages);
			request.setAttribute("user" , user);
			List<Branch> branch = new BranchService().getBranch();
			request.setAttribute("branch" , branch);
			List<Department> department = new DepartmentService().getDepartment();
			request.setAttribute("department" , department);
			request.getRequestDispatcher ("signup.jsp").forward (request , response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String check_password = request.getParameter("check_password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));

		if (StringUtils.isEmpty(login_id) || StringUtils.isBlank(login_id)) {
			messages.add("ログインIDを入力してください");
		} else {
			if (!login_id.matches("[a-zA-Z0-9]{6,20}")) {
				messages.add("ログインIDが不正です");
			} else {
				if (new UserService().overlap(login_id) != null ) {
					messages.add("既に使用されているログインIDです");
				}
			}
		}
		if (StringUtils.isEmpty(name)|| StringUtils.isBlank(name)) {
			messages.add("ユーザー名を入力してください");
		} else {
			if (name.length() > 10) {
				messages.add("ユーザー名は10文字以下で入力してください");
			}
		}
		if (StringUtils.isEmpty(password)|| StringUtils.isBlank(password)) {
			messages.add("パスワードを入力してください");
		} else {
			if (password.length() < 6 || password.length() > 255) {
				messages.add("パスワード6文字以上255文字以下で入力してください");
			} else {
				if (!password.equals(check_password)) {
					messages.add("パスワードと確認用のパスワードが不一致です");
				}

			}
		}

		if ((branch_id == 1 && !(department_id == 1 || department_id == 2 ))
				|| ((branch_id == 2 || branch_id ==3 || branch_id == 4)&& !(department_id == 3 || department_id == 4 ))) {
			messages.add("支店と部署・役所名は存在しない組み合わせです");
		}

		if (messages.size() == 0) {
			return true;
		} else {
		return false;
		}
	}
}
