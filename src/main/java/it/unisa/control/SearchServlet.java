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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchQuery = request.getParameter("searchQuery");
        String categoryQuery = request.getParameter("category");
        String source = request.getParameter("source"); // Legge il parametro nascosto per essere utilizzata sia
        												//nella parte pubblica che privata del sito
        
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);

        try {
            Collection<ProductDTO> products;
            String pageTitle = "";

            if (categoryQuery != null && !categoryQuery.trim().isEmpty()) {
                products = productDao.doRetrieveByCategory(categoryQuery);
                pageTitle = "Categoria: " + categoryQuery.substring(0, 1).toUpperCase() + categoryQuery.substring(1);
            } else if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                products = productDao.doRetrieveByName(searchQuery);
                pageTitle = "Risultati per: \"" + searchQuery + "\"";
            } else {
                products = productDao.doRetrieveAll("IdProdotto"); //Ordina in base all'id prodotto
                pageTitle = "Tutti i Prodotti";
            }
            
            request.setAttribute("products", products);
            request.setAttribute("pageTitle", pageTitle); 

        } catch (SQLException e) {
            System.err.println("Errore durante la ricerca del prodotto: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
            return; 
        }
        //Indirizza nella corretta visualizzazione i prodotti
        String forwardPage;
        if ("admin".equals(source)) {
            forwardPage = "/admin/productManagement.jsp";
        } else {
            forwardPage = "/common/searchResult.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPage);
        dispatcher.forward(request, response);
    }
}
