package it.unisa.control;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter("/*")
public class AccessControlFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(false);


		Boolean isAdmin = null;
		if (session != null) {
		    isAdmin = (Boolean) session.getAttribute("isAdmin");
		}
		
		String path = httpServletRequest.getServletPath();

		if (path.startsWith("/admin/")) {
			if (isAdmin == null || !isAdmin) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/login.jsp");
				return;
			}
		}
		
		//protezione servlet
		boolean isProtectedUserPath = path.equals("/account") || path.equals("/checkout") || path.equals("/create-order");
		
		if (isProtectedUserPath && isAdmin == null) { 
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/login.jsp");
			return;
		}

		chain.doFilter(request, response);
	}
}
