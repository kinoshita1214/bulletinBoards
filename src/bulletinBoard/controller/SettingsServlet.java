package bulletinBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Branch;
import bulletinBoard.beans.Department;
import bulletinBoard.beans.User;
import bulletinBoard.exception.NoRowsUpdatedRuntimeException;
import bulletinBoard.service.BranchService;
import bulletinBoard.service.DepartmentService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("management.id"));

    	User editUser = new UserService().getUser(id);
    	request.setAttribute("editUser" , editUser);

    	List<Branch> branch = new BranchService().getBranch();
		request.setAttribute("branch" , branch);
		List<Department> department = new DepartmentService().getDepartment();
		request.setAttribute("department" , department);
    	request.getRequestDispatcher ("settings.jsp").forward (request , response);
    }

    @Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

    	List<String> messages = new ArrayList<String>();
    	User editUser = getEditUser(request);

    	if (isValid (request , messages) ==true) {

    		try {
    			new UserService().update (editUser);
    		} catch (NoRowsUpdatedRuntimeException e) {
    			messages.add ("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
    			request.setAttribute ("errorMessages" , messages);
    			request.getRequestDispatcher ("settings.jsp").forward (request , response);
    			return;
    		}
    		response.sendRedirect("management");
    	} else {
    		request.setAttribute("errorMessage" , messages);
    		request.getRequestDispatcher ("settings.jsp").forward (request , response);
    	}
	}

	private User getEditUser(HttpServletRequest request) throws IOException , ServletException {

		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
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
