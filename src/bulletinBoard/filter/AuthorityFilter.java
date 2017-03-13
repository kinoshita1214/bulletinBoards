package bulletinBoard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinBoard.beans.User;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
@WebFilter(urlPatterns = { "/management", "/signup" , "/settings"})
public class AuthorityFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
		if (user.getBranch_id() == 1 && user.getDepartment_id()==1) {
			chain.doFilter(request, response);
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("権限がありません。");
			session.setAttribute("errorMessages", messages);

			((HttpServletResponse) response).sendRedirect("./");
		}

	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
