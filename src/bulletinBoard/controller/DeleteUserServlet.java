package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;
import bulletinBoard.service.UserService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("id")));
		new UserService().delete(user);

		response.sendRedirect("management");

	}
}
