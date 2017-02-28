package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserManagement;
import bulletinBoard.service.ManagementService;

@WebServlet(urlPatterns = { "/management" })
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute ("loginUser");
		boolean isShowManagementForm;
		if (user != null) {
			isShowManagementForm = true;
		} else {
			isShowManagementForm =false;
		}

		List<UserManagement> managements = new ManagementService().getManagement(null);

		request.setAttribute ("managements" , managements);
		request.setAttribute("isShowManagementForm" , isShowManagementForm);

		request.getRequestDispatcher ("/management.jsp").forward(request , response);

	}
}
