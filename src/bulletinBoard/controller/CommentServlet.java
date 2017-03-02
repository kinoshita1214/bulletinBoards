package bulletinBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowCommentForm;
		if (user != null) {
			isShowCommentForm = true;
		} else {
			isShowCommentForm = false;
		}
		List<UserComment> comments = new CommentService().getComment(null);
		request.setAttribute ("comments" , comments);
		request.setAttribute("isShowCommentForm" , isShowCommentForm);

		request.getRequestDispatcher("/comment.jsp").forward(request , response);
	}

}

