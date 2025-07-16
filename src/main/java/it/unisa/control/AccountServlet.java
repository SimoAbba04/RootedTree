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
import it.unisa.model.UserDTO;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
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
			response.sendRedirect("500.jsp");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
    	String action = (String) request.getAttribute("action");
    	
    	  if (session == null) {
              response.sendRedirect("login.jsp");
              return;
          }
    	  
    	  switch(action) {
    	  		case : "updateProfile"
    	  			UserDAO 
    	  }
    	  
    	// COMPLETA TU:
        // Questa è la parte che implementerai tu.
        // 1. Controlla che l'utente sia in sessione.
        // 2. Leggi il parametro "action" per sapere quale form è stato inviato
        //    (es. "updateProfile", "updateAddress", "updatePayment").
        // 3. Usa una struttura switch(action) per gestire i diversi casi.
        // 4. Per ogni caso:
        //    a. Recupera i parametri dal form (request.getParameter).
        //    b. Esegui la validazione dei dati (molto importante!).
        //    c. Crea il DTO corrispondente (UserDTO, AddressDTO, etc.).
        //    d. Decidi se fare un .doSave() (se l'oggetto non esisteva) o un .doUpdate() (se esisteva già).
        //    e. Chiama il metodo del DAO appropriato.
        // 5. Alla fine, fai un redirect a questa stessa servlet per mostrare i dati aggiornati:
        //    response.sendRedirect("account");
    }
}