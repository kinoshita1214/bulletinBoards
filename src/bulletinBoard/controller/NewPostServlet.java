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

import bulletinBoard.beans.Post;
import bulletinBoard.beans.User;
import bulletinBoard.service.PostService;

@WebServlet(urlPatterns = { "/newPost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("post.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<String> posts = new ArrayList<String>();

		if (isValid(request, posts) == true) {

			User user = (User) session.getAttribute("loginUser");

			Post post = new Post();
			post.setUser_id(user.getId());
			post.setSubject(request.getParameter("subject"));
			post.setText(request.getParameter("post"));
			post.setCategory(request.getParameter("category"));

			new PostService().register(post);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", posts);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> posts) {

		String post = request.getParameter("post");

		if (StringUtils.isEmpty(post) == true) {
			posts.add("メッセージを入力してください");
		}
		if (1000 < post.length()) {
			posts.add("1000文字以下で入力してください");
		}
		if (posts.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}