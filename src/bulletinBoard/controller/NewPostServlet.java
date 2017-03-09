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

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Post post = new Post();
			post.setUser_id(user.getId());
			post.setSubject(request.getParameter("subject"));
			post.setText(request.getParameter("text"));
			post.setCategory(request.getParameter("category"));

			new PostService().register(post);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("post.jsp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String category = request.getParameter("category");


		if (StringUtils.isEmpty(subject) == true) {
			messages.add("件名を入力してください");
		} else {
			if (subject.length() > 50) {
				messages.add("件名は50以下で入力してください");
			}
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		} else {
			if (text.length() >1000) {
				messages.add("本文は1000文字以下で入力してください");
			}
		}
		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください");
		} else {
			if (category.length() >10) {
				messages.add("カテゴリーは10文字以下で入力してください");
			}
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}