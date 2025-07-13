package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.model.UserDAO;
import it.unisa.model.UserDTO;
import it.unisa.model.UserDTO.Ruolo;

@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("loginMail");
		String password = request.getParameter("logPass");
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");
		if (email == null || email.trim().isEmpty()) {
			System.out.println("Email nulla");
			throw new ServletException("Email mancante.");
		}
		if (password == null || password.trim().isEmpty()) {
			System.out.println("Password mancante");
			throw new ServletException("Password mancante.");
		}
		email = email.trim();
		password = password.trim();
		String hashPassword = HtmlHash.toHash(password);
		ServletContext context = getServletContext();
		DataSource ds = (DataSource) context.getAttribute("Datasource");
		
		if (ds == null) {
			System.out.println("DataSource NON inizializzato!");
			throw new ServletException("DataSource mancante!");
		}
		
		UserDAO user = new UserDAO(ds);
		UserDTO retrievingUser = null;
		try {
			retrievingUser = user.doRetrieveByMail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(hashPassword.equals(retrievingUser.getPassword()) && Ruolo.admin.equals(retrievingUser.getRuolo())) {
			request.getSession().setAttribute("isAdmin", Boolean.TRUE);
			response.sendRedirect("common/index.jsp");
		}else if(hashPassword.equals(retrievingUser.getPassword()) && Ruolo.admin.equals(retrievingUser.getRuolo())){
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);
			response.sendRedirect("common/index.jsp");
		}else {
			request.setAttribute("errore","Email e password non corretti.");
			dispatcherToLoginPage.forward(request, response);
		}
		
		
	}


	private static final long serialVersionUID = 1L;

}
