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

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Post;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserPost;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user;
		List<UserPost> posts;
		List<UserPost> categories;
		List<UserComment> comments;
		List<UserPost> dates;
		dates = new PostService().getDate();
		String start = dates.get(0).getInsertDate().toString();
		SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String end = e.format(new Date());
		Post category = new Post();
		category.setCategory(request.getParameter("category"));

		if (request.getParameter("start") != null && request.getParameter("end") != null) {
			start = request.getParameter("start");
			end = request.getParameter("end");
		}
		if (StringUtils.isEmpty(request.getParameter("start")) == true) {
			start = dates.get(0).getInsertDate().toString();
		}
		if(StringUtils.isEmpty(request.getParameter("end")) == true) {
			end = e.format(new Date());
		}

		user = (User) request.getSession().getAttribute("loginUser");
		posts = new PostService().getPosts(start , end , category);
		categories = new PostService().getCategory();
		comments = new CommentService().getComment();

		request.setAttribute("user", user);
		request.setAttribute("posts", posts);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		request.setAttribute("comments", comments);
		request.setAttribute("start", start);
		request.setAttribute("end", end);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
