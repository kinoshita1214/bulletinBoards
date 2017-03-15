package bulletinBoard.filter;

import java.io.IOException;

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
import bulletinBoard.service.UserService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		String url= ((HttpServletRequest) request).getServletPath();
		User user = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
			if (url.equals("/login")) {
				chain.doFilter(request, response);
			} else {
				if (session.getAttribute("loginUser") != null) {
					int id = user.getId();
					user = new UserService().getUser(id);
					if(user == null){
						((HttpServletResponse) response).sendRedirect("login");
						return;
					}
					if(user.getIs_stoped() == 1) {
						((HttpServletResponse) response).sendRedirect("login");
						return;
					}
					session.setAttribute("loginUser", user);
					chain.doFilter(request, response);
				} else {
//					RequestDispatcher dispatcher = request.getRequestDispatcher("login");
//					dispatcher.forward(request,response);
					((HttpServletResponse) response).sendRedirect("login");
				}
			}
//			if((session = (HttpSession)((HttpServletRequest) request).getSession(false)) == null){
//				RequestDispatcher dispatcher = request.getRequestDispatcher("login");
//				dispatcher.forward(request,response);
//			}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
