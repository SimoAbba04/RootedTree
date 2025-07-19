package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Boolean isAdmin = null;

		if (session != null) {
			isAdmin = (Boolean) session.getAttribute("isAdmin");
		}

		if (isAdmin == null || !isAdmin) {
			response.sendRedirect(request.getContextPath() + "/common/index.jsp");
			return;
		}

		// Se è un admin, lo inoltra alla dashboard
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
		dispatcher.forward(request, response);
	}
}