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

import it.unisa.model.AddressDAO;
import it.unisa.model.AddressDTO;
import it.unisa.model.PayMethodDAO;
import it.unisa.model.PayMethodDTO;
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/login.jsp");
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

				AddressDAO addressDao = new AddressDAO(ds);
				PayMethodDAO paymentDao = new PayMethodDAO(ds);

				AddressDTO address = addressDao.doRetrieveByAccount(user.getID());
				PayMethodDTO payment = paymentDao.doRetrieveByAccount(user.getID());

				if (address != null) {
					session.setAttribute("address", address);
				}
				if (payment != null) {
					session.setAttribute("payment", payment);
				}

				response.sendRedirect(request.getContextPath() + "/common/index.jsp");
			} else {
				request.setAttribute("Errore", "Email o password non corretti. Riprova.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/login.jsp");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
		}
	}
}
