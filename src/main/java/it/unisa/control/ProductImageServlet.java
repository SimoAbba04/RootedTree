package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;

@WebServlet("/ProductImageServlet")
public class ProductImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID prodotto non valido.");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);

        try {
            byte[] imageData = productDao.getImmagine(id);

            if (imageData != null && imageData.length > 0) {
                response.setContentType("image/png");
                response.setContentLength(imageData.length);
                
                response.getOutputStream().write(imageData);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Immagine non trovata.");
            }
        } catch (SQLException e) {
            System.err.println("Errore SQL durante il recupero dell'immagine: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero dell'immagine.");
        }
    }
}
