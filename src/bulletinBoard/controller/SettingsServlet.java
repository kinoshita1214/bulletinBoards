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
import bulletinBoard.exception.NoRowsUpdatedRuntimeException;
import bulletinBoard.service.BranchService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<Branch> branch = new BranchService().getBranch();
		request.setAttribute("branch" , branch);
		List<Department> department = new DepartmentService().getDepartment();
		request.setAttribute("department" , department);
		request.getRequestDispatcher("signup.jsp").forward(request , response);

    	HttpSession session =request.getSession();
    	User loginUser = (User) session.getAttribute ("loginUser");

    	if (session.getAttribute ("editUser") == null) {
    		User editUser = new UserService().getUser(loginUser.getId());
    		session.setAttribute ("editUser", editUser);
    	}

    	request.getRequestDispatcher ("settings.jsp").forward (request , response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Branch> branch = new BranchService().getBranch();
		request.setAttribute("branch" , branch);
		List<Department> department = new DepartmentService().getDepartment();
		request.setAttribute("department" , department);
		request.getRequestDispatcher("signup.jsp").forward(request , response);
    	List<String> messages = new ArrayList<String>();

    	HttpSession session = request.getSession();

    	User editUser = getEditUser (request);
    	session.setAttribute("editUser" , editUser);

    	if (isValid (request , messages) ==true) {

    		try {
    			new UserService().update (editUser);
    		} catch (NoRowsUpdatedRuntimeException e) {
    			session.removeAttribute ("editUser");
    			messages.add ("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
    			session.setAttribute ("errorMessages" , messages);
    			response.sendRedirect ("settings");
    		}

    		session.setAttribute("loginUser" , editUser);
    		session.removeAttribute ("editUser");

    		response.sendRedirect("./");
    	} else {
    		session.setAttribute("errorMessage" , messages);
    		response.sendRedirect ("settings");
    	}
	}

	private User getEditUser(HttpServletRequest request) throws IOException , ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setLogin_id (request.getParameter ("login_id"));
		editUser.setName (request.getParameter ("name"));
		editUser.setPassword (request.getParameter("password"));
		editUser.setBranch_id (Integer.parseInt(request.getParameter("branch_id")));
		editUser.setDepartment_id (Integer.parseInt(request.getParameter("department_id")));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String login_id = request.getParameter ("login_id");
		String name = request.getParameter ("name");

		if (StringUtils.isEmpty(login_id) ==true ) {
			messages.add ("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty (name) == true) {
			messages.add ("名前を入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
