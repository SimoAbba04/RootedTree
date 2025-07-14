package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;
import it.unisa.model.ProductDTO;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);
        
        try {
            Collection<ProductDTO> products;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                products = productDao.doRetrieveByName(searchQuery);
            } else {
                // Se la ricerca è vuota, mostra tutti i prodotti
                products = productDao.doRetrieveAll(""); 
            }
            
            request.setAttribute("products", products);
            request.setAttribute("searchQuery", searchQuery); 

        } catch (SQLException e) {
            System.err.println("Errore durante la ricerca del prodotto: " + e.getMessage());
            // Potresti reindirizzare a una pagina di errore
            request.setAttribute("error", "Errore del database durante la ricerca.");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/search-results.jsp");
        dispatcher.forward(request, response);
    }
}
