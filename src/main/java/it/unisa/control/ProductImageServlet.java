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
            // Se l'ID non è un numero, non è una richiesta valida
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID prodotto non valido.");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);

        try {
            byte[] imageData = productDao.getImmagine(id);

            if (imageData != null && imageData.length > 0) {
                // Imposta il tipo di contenuto corretto. Il browser capirà che è un'immagine.
                // Per una maggiore flessibilità, potresti salvare il content-type nel DB
                // insieme all'immagine (es. "image/jpeg", "image/png"). Per ora, usiamo jpeg.
                response.setContentType("image/jpeg");
                response.setContentLength(imageData.length);
                
                // Scrive i dati binari dell'immagine direttamente nello stream di output della risposta
                response.getOutputStream().write(imageData);
            } else {
                // Se non c'è immagine, potresti inviare un'immagine di default (placeholder)
                // o semplicemente un errore 404.
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Immagine non trovata.");
            }
        } catch (SQLException e) {
            System.err.println("Errore SQL durante il recupero dell'immagine: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero dell'immagine.");
        }
    }
}
