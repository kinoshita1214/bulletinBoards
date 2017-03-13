package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.Post;
import bulletinBoard.service.PostService;

/**
 * Servlet implementation class DeletePostServlet
 */
@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Post post = new Post();
		post.setId(Integer.parseInt(request.getParameter("post.id")));
		new PostService().delete(post);

		response.sendRedirect("./");

	}
}
