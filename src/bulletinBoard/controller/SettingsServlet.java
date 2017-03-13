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
import bulletinBoard.service.DepartmentService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	List<String> messages = new ArrayList<String>();
    	String code = request.getParameter("id");
    	if (StringUtils.isEmpty(code)) {
    		messages.add("不正なエラーが発生しました");
    		session.setAttribute ("errorMessages" , messages);
    		response.sendRedirect("management");
    		return;
    	} else {
    		if (!code.matches("[0-9]+$")){
    			messages.add("不正なエラーが発生しました");
        		session.setAttribute ("errorMessages" , messages);
        		response.sendRedirect("management");
        		return;
    		}
    	}
    	int id = Integer.parseInt(code);
    	User editUser = new UserService().getUser(id);
    	if (editUser == null) {
    		messages.add("不正なエラーが発生しました");
    		session.setAttribute ("errorMessages" , messages);
    		response.sendRedirect("management");
    		return;
    	}
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
    	HttpSession session = request.getSession();
    	List<String> messages = new ArrayList<String>();
    	User editUser = getEditUser(request);

    	if (isValid (request , messages) ==true) {

    		try {
    			new UserService().update (editUser);
    		} catch (NoRowsUpdatedRuntimeException e) {
    			messages.add ("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
    			session.setAttribute ("errorMessages" , messages);
    			request.setAttribute("editUser" , editUser);
    			request.getRequestDispatcher ("settings.jsp").forward (request , response);
    			return;
    		}
    		response.sendRedirect("management");
    	} else {
    		List<Branch> branch = new BranchService().getBranch();
    		request.setAttribute("branch" , branch);
    		List<Department> department = new DepartmentService().getDepartment();
    		request.setAttribute("department" , department);
        	request.setAttribute("editUser" , editUser);
    		request.setAttribute("errorMessages" , messages);
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

	private boolean isValid(HttpServletRequest request, List<String> messages) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		User editUser = new UserService().getUser(id);
		String login_id = request.getParameter("login_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String check_password = request.getParameter("check_password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		User overlap = new UserService().overlap(login_id);
		if (StringUtils.isEmpty(login_id)) {
			messages.add("ログインIDを入力してください");
		} else {
			if (!login_id.matches("[a-zA-Z0-9]{6,20}")) {
				messages.add("ログインIDが不正です");
			} else {
				if (!login_id.equals(editUser.getLogin_id())) {
					if (overlap.getLogin_id() != null ) {
						messages.add("既に使用されているログインIDです");
					}
				}
			}
		}
		if (StringUtils.isEmpty(name)) {
			messages.add("ユーザー名を入力してください");
		} else {
			if (name.length() > 10) {
				messages.add("ユーザー名は10文字以下で入力してください");
			}
		}
		if (!StringUtils.isEmpty(password)) {
			if (password.length() < 6 || password.length() > 255) {
				messages.add("パスワードは6文字以上255文字以下で入力してください");
			} else {
				if (!password.equals(check_password)) {
					messages.add("パスワードと確認用のパスワードが不一致です");
				}
			}
		}
		if ((branch_id == 1 && !(department_id == 1 || department_id == 2 ))
				|| ((branch_id == 2 || branch_id ==3 || branch_id == 4)&& !(department_id == 3 || department_id == 4 ))) {
			messages.add("支店名と部署・役所名は存在しない組み合わせです");
		}

		if (messages.size() == 0) {
			return true;
		} else {
		return false;
		}
	}
}
