package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.model.Cart;
import it.unisa.model.ProductDAO;
import it.unisa.model.ProductDTO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
       
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            
            switch (action) {
                case "add":
                    addProductToCart(productId, cart);
                    break;
                case "remove":
                    cart.removeItem(productId);
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Errore parsing ID o quantità: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Errore DB nel CartServlet: " + e.getMessage());
        }

        
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    private void addProductToCart(int productId, Cart cart) throws SQLException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);
        ProductDTO product = productDao.doRetrieveByKey(productId);
        if (product != null) {
            cart.addItem(product);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
