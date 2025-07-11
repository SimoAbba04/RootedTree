package it.unisa.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.unisa.project.UserDTO;

@WebServlet("/account")
public class AccountDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        // Se l'utente non è in sessione, reindirizzalo al login
        if (user == null) {
            response.sendRedirect("accesso.jsp");
            return;
        }
        
        // Qui potresti anche caricare altri dati, come l'indirizzo, usando un AddressDAO
        // AddressDAO addressDao = new AddressDAO((DataSource) getServletContext().getAttribute("DataSource"));
        // AddressDTO address = addressDao.doRetrieveByUserId(user.getId());
        // request.setAttribute("address", address);

        // Inoltra alla pagina JSP che mostrerà i dati
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }
}