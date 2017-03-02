package bulletinBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.Comment;
import bulletinBoard.service.CommentService;

@WebServlet(urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Comment comment = new Comment();
		comment.setId(Integer.parseInt(request.getParameter("comment.id")));
		new CommentService().put(comment);

		response.sendRedirect("./");
		}
	}

