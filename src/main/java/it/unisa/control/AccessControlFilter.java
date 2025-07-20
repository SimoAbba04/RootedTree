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
		
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		Boolean isAdmin = (isLoggedIn) ? (Boolean) session.getAttribute("isAdmin") : false;
		String path = httpServletRequest.getServletPath();
		

		boolean isAdminPath = path.startsWith("/admin/");
		boolean isUserPath = path.equals("/AccountServlet") || path.equals("/createOrderServlet");


		//Se la risorsa è nell'area admin...
		if (isAdminPath) {
			if (!isLoggedIn || !isAdmin) {
				// ...e l'utente non è un admin, reindirizza all'index.
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/index.jsp");
				return;
			}
		} 
		// Se la risorsa è un'area protetta per utenti...
		else if (isUserPath) {
		    if (!isLoggedIn) {
		        // ...e l'utente non è loggato, reindirizza all'index.
		        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/index.jsp");
		        return;
		    }
		}

		chain.doFilter(request, response);
	}
}
