package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.model.UserDAO;
import it.unisa.model.UserDTO;
import it.unisa.model.UserDTO.Ruolo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDAO userDao = new UserDAO(ds);
		try {

			UserDTO user = userDao.doRetrieveByMail(email);

			if (user == null) {
				request.setAttribute("errorMessage", "Email o password non corretti. Riprova.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			String hashPassword = HtmlHash.toHash(password);

			if (hashPassword.equals(user.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				if (user.getRuolo() == Ruolo.admin) {
					session.setAttribute("isAdmin", true);
				} else {
					session.setAttribute("isAdmin", false);
				}

				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("errorMessage", "Email o password non corretti. Riprova.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error500.jsp");
		}
	}
}
