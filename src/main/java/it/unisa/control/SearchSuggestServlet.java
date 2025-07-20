package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject; // Aggiunto import per gli oggetti JSON

import it.unisa.model.ProductDAO;
import it.unisa.model.ProductDTO;

@WebServlet("/SearchSuggestServlet")
public class SearchSuggestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (query != null && !query.trim().isEmpty()) {
            DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
            ProductDAO productDao = new ProductDAO(ds);
            
            try {
                Collection<ProductDTO> products = productDao.doRetrieveByPartialName(query);
                
                // Creiamo un array di oggetti JSON
                JSONArray jsonArray = new JSONArray();
                for (ProductDTO p : products) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", p.getId());//Con id...
                    jsonObject.put("name", p.getNome());//... e nome prodotti
                    jsonArray.put(jsonObject);
                }
                
                response.getWriter().write(jsonArray.toString());
                
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore del database.");
            }
        }
    }
}
