package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserPost;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String user_id = request.getParameter("user_id");

		User user;
		List<UserPost> posts = null;
		boolean isShowPostForm;

		if (user_id == null) {
			user = (User) request.getSession().getAttribute("loginUser");
			if (user != null) {
				isShowPostForm = true;
			} else {
				isShowPostForm = false;
			}
		} else {
			int uId = Integer.parseInt(user_id);
			user = new UserService().getUser(uId);
			isShowPostForm = false;
		}
		request.setAttribute("user", user);
		request.setAttribute("posts", posts);
		request.setAttribute("isShowPostForm", isShowPostForm);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
