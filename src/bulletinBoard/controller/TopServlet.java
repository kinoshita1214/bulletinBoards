package bulletinBoard.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		List<UserPost> dates;
		dates = new PostService().getDate();
		String start =dates.get(0).getInsertDate().toString();
		SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String end = e.format(new Date());
		Post category = new Post();
		category.setCategory(request.getParameter("category"));

		start = request.getParameter("start");
		end = request.getParameter("end");
		System.out.println(start);
		System.out.println(end);

		User user;
		List<UserPost> posts;
		List<UserPost> categories;
		List<UserComment> comments;

		if (user_id == null) {
			user = (User) request.getSession().getAttribute("loginUser");
			posts = new PostService().getPost(start , end , category);
			categories = new PostService().getCategory();
			comments = new CommentService().getComment();

		} else {
			int uId = Integer.parseInt(user_id);
			user = new UserService().getUser(uId);
			posts = new PostService().getPost(start , end , category);
			categories = new PostService().getCategory();
			comments = new CommentService().getComment();

		}


		request.setAttribute("user", user);
		request.setAttribute("posts", posts);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		request.setAttribute("comments", comments);


		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
