package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;
import bulletinBoard.service.UserService;


@WebServlet(urlPatterns = { "/stop" })
public class StopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();

		user.setId(Integer.parseInt(request.getParameter("management.id")));
		user.setIs_stoped(Integer.parseInt(request.getParameter("is_stoped")));
		new UserService().invert(user);
		response.sendRedirect("management");
	}

}
