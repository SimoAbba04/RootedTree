package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;
import it.unisa.model.ProductDTO;

@WebServlet("/admin/productForm")
public class AdminProductFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);
        String productIdStr = request.getParameter("id");
        //Modalità modifica
        if (productIdStr != null) {
            try {
                int productId = Integer.parseInt(productIdStr);
                ProductDTO product = productDao.doRetrieveByKey(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
                return;
            }
        }
        // Senza id restituisce solo il form vuoto
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product-form.jsp");
        dispatcher.forward(request, response);
    }
}