package it.unisa.control;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.*;
import java.sql.Date;

import javax.sql.DataSource;

import it.unisa.project.UserDAO;
import it.unisa.project.UserDTO;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empty = "";
		String name = request.getParameter("nome");
		if (name == null || name.trim().isEmpty()) {
			System.out.println("Nome nullo.");
			throw new ServletException("Nome mancante!");
		}
		String cognome = request.getParameter("cognome");
		if (cognome == null || cognome.trim().isEmpty()) {
			System.out.println("Cognome nullo.");
			throw new ServletException("Cognome mancante!");
		}
		String email = request.getParameter("email");
		if (email == null || email.trim().isEmpty()) {
			System.out.println("Email null.");
			throw new ServletException("Email mancante!");
		}
		String password = request.getParameter("password");
		if (password == null || password.trim().isEmpty()) {
			System.out.println("Password null.");
			throw new ServletException("Password mancante!");
		}
		String data = request.getParameter("dataNascita");
		if (data == null || data.trim().isEmpty()) {
			System.out.println("Data nullo.");
			throw new ServletException("Data mancante!");
		}
		String hashedPw = HtmlHash.toHash(password);
		Date dataNascita = Date.valueOf(data);
		System.out.println("  " + hashedPw);// Debug
		UserDTO user = new UserDTO();
		user.setNome(HtmlDecoder.encodeHtmlEntities(name));
		user.setCognome(HtmlDecoder.encodeHtmlEntities(cognome));
		user.setEmail(HtmlDecoder.encodeHtmlEntities(email));
		user.setPassword(hashedPw);
		user.setDataNascita(dataNascita);

		ServletContext context = getServletContext();
		DataSource ds = (DataSource) context.getAttribute("DataSource");

		if (ds == null) {
			System.out.println("DataSource NON inizializzato!");
			throw new ServletException("DataSource mancante!");
		}

		UserDAO savingUser = new UserDAO(ds);

		try {
			savingUser.doSave(user);
			request.setAttribute("messaggio", "Registrazione avvenuta con successo!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("messaggio", "Errore durante la registrazione: " + e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
