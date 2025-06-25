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
		}
		String cognome = request.getParameter("cognome");
		if (cognome == null || cognome.trim().isEmpty()) {
			System.out.println("Cognome nullo.");
		}
		String email = request.getParameter("email");
		if (email == null || email.trim().isEmpty()) {
			System.out.println("Email null.");
		}
		String password = request.getParameter("password");
		if (password == null || password.trim().isEmpty()) {
			System.out.println("Password nullo.");
		}
		String data = request.getParameter("dataNascita");
		if (data == null || data.trim().isEmpty()) {
			System.out.println("Data nullo.");
		}
		String hashedPw = toHash(password);
		Date dataNascita = Date.valueOf(data);
		System.out.println("  " + hashedPw);// Debug
		UserDTO user = new UserDTO();
		user.setNome(encodeHtmlEntities(name));
		user.setCognome(encodeHtmlEntities(cognome));
		user.setEmail(encodeHtmlEntities(email));
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
			request.getRequestDispatcher("/registrazione.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("messaggio", "Errore durante la registrazione: " + e.getMessage());
			request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
		}
	}

	private String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3);
			}
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return hashString;
	}

	public static String encodeHtmlEntities(String input) {
		if (input == null)
			return null;

		return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
				.replace("'", "&#39").replace(" ", "&nbsp;");
	}
}
