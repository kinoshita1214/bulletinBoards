package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.Post;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserPost;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.PostService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String user_id = request.getParameter("user_id");

		Post category = new Post();
		category.setCategory(request.getParameter("category"));

		User user;
		List<UserPost> posts;
		List<UserPost> categories;
		List<UserComment> comments;
		boolean isShowPostForm;
		boolean isShowCommentForm;


		if (user_id == null) {
			user = (User) request.getSession().getAttribute("loginUser");
			posts = new PostService().getPost(null, category);
			categories = new PostService().getCategory(null);

			comments = new CommentService().getComment(null);
			if (user != null) {
				isShowPostForm = true;
				isShowCommentForm = true;

			} else {
				isShowPostForm = false;
				isShowCommentForm = false;

			}
		} else {
			int uId = Integer.parseInt(user_id);
			user = new UserService().getUser(uId);
			posts = new PostService().getPost(uId, category);
			categories = new PostService().getCategory(uId);
			comments = new CommentService().getComment(uId);
			isShowPostForm = false;
			isShowCommentForm = false;
		}


		request.setAttribute("user", user);
		request.setAttribute("posts", posts);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		request.setAttribute("comments", comments);
		request.setAttribute("isShowPostForm", isShowPostForm);
		request.setAttribute("isShowCommentForm", isShowCommentForm);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
