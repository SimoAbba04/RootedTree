package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.sql.DataSource;

import it.unisa.model.AddressDAO;
import it.unisa.model.AddressDTO;
import it.unisa.model.PayMethodDAO;
import it.unisa.model.PayMethodDTO;
import it.unisa.model.UserDAO;
import it.unisa.model.UserDTO;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
			return;
		}

		UserDTO user = (UserDTO) session.getAttribute("user");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

		AddressDAO addressDao = new AddressDAO(ds);
		PayMethodDAO paymentDao = new PayMethodDAO(ds);

		try {
			AddressDTO address = addressDao.doRetrieveByAccount(user.getID());
			PayMethodDTO payment = paymentDao.doRetrieveByAccount(user.getID());

			request.setAttribute("address", address);
			request.setAttribute("payment", payment);

		} catch (SQLException e) {
			System.err.println("Errore recupero dati account: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/common/account.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
			return;
		}

		String action = request.getParameter("action");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDTO userInSession = (UserDTO) session.getAttribute("user");

		try {
			switch (action) {
			case "updateProfile":
				UserDAO userDao = new UserDAO(ds);
				UserDTO updatedUser = new UserDTO();
				updatedUser.setID(userInSession.getID());
				updatedUser.setNome(request.getParameter("nome"));
				updatedUser.setCognome(request.getParameter("cognome"));
				updatedUser.setEmail(request.getParameter("email"));
				updatedUser.setDataNascita(Date.valueOf(request.getParameter("dataNascita")));

				String oldPass = request.getParameter("oldPassword");
				String newPass = request.getParameter("newPassword");
				if (newPass != null && !newPass.isEmpty()) {
					if (HtmlHash.toHash(oldPass).equals(userInSession.getPassword())) {
						updatedUser.setPassword(HtmlHash.toHash(newPass));
					} else {
						session.setAttribute("errorMessage", "La vecchia password non è corretta.");
						response.sendRedirect(request.getContextPath() + "/common/account.jsp");
						return;
					}
				} else {
					updatedUser.setPassword(userInSession.getPassword());
				}

				userDao.doUpdate(updatedUser);
				session.setAttribute("user", updatedUser);
				session.setAttribute("successMessage", "Dati del profilo aggiornati con successo!");
				break;

			case "updateAddress":
				AddressDAO addressDao = new AddressDAO(ds);
				AddressDTO address = new AddressDTO();
				address.setStato(request.getParameter("stato"));
				address.setVia(request.getParameter("via"));
				address.setCittà(request.getParameter("citta"));
				address.setProvincia(request.getParameter("provincia"));
				address.setCAP(request.getParameter("cap"));
				address.setNumeroTelefono(request.getParameter("numeroTelefono"));
				address.setDescrizione(request.getParameter("descrizione"));
				address.setIdUtente(userInSession.getID());

				String addressId = request.getParameter("addressId");
				if (addressId != null && !addressId.isEmpty()) {
					address.setId(Integer.parseInt(addressId));
					addressDao.doUpdate(address);
				} else {
					addressDao.doSave(address);
				}
				session.setAttribute("successMessage", "Indirizzo salvato con successo!");
				break;

			case "updatePayment":
				PayMethodDAO paymentDao = new PayMethodDAO(ds);
				PayMethodDTO payment = new PayMethodDTO();
				payment.setTitolare(request.getParameter("titolare"));
				payment.setNumeroCarta(request.getParameter("numeroCarta"));
				payment.setDataScadenza(Date.valueOf(request.getParameter("dataScadenza")));
				payment.setCodiceSicurezza(request.getParameter("cvv"));
				payment.setIdAccount(userInSession.getID());

				String paymentId = request.getParameter("paymentId");
				if (paymentId != null && !paymentId.isEmpty()) {
					payment.setId(Integer.parseInt(paymentId));
					paymentDao.doUpdate(payment);
				} else {
					paymentDao.doSave(payment);
				}
				session.setAttribute("successMessage", "Metodo di pagamento salvato con successo!");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", "Si è verificato un errore con il database.");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", "Si è verificato un errore inaspettato.");
		}

		response.sendRedirect(request.getContextPath() + "/common/account.jsp");
	}
}
