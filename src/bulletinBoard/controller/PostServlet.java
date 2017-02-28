package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet(urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowPostForm;
		if (user != null) {
			isShowPostForm = true;
		} else {
			isShowPostForm = false;
		}


		request.setAttribute("isShowPostForm" , isShowPostForm);

		request.getRequestDispatcher("/post.jsp").forward(request , response);
	}

}
