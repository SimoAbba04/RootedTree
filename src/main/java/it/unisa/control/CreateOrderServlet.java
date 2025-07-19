package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import it.unisa.model.*;

@WebServlet("/create-order")
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/common/cart.jsp");
            return;
        }
        
        if (session.getAttribute("address") == null || session.getAttribute("payment") == null) {
            session.setAttribute("errorMessage", "Per procedere, completa il tuo profilo con un indirizzo e un metodo di pagamento.");
            response.sendRedirect(request.getContextPath() + "/account");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

        try {
            OrderDTO ordine = new OrderDTO();
            ordine.setIdAccount(user.getID());
            ordine.setDataOrdine(new Date()); 
            ordine.setStato(OrderDTO.Stato.lavorazione);
            ordine.setTotale(cart.getTotal());

            OrderDAO ordineDao = new OrderDAO(ds);
            int idOrdine = ordineDao.doSaveOrder(ordine);

            // Salva i dettagli dell'ordine (i singoli prodotti)
            OrderDetailDAO dettaglioDao = new OrderDetailDAO(ds);
            ProductDAO productDao = new ProductDAO(ds);
            for (CartItem item : cart.getItems()) {
                OrderDetailDTO dettaglio = new OrderDetailDTO();
                dettaglio.setIdOrdine(idOrdine);
                dettaglio.setIdProdotto(item.getProduct().getId());
                dettaglio.setQta(item.getQuantity());
                dettaglio.setPrezzoUnitario(item.getProduct().getPrezzo());
                dettaglioDao.doSave(dettaglio);
                
                // Aggiorna la disponibilità del prodotto nel DB
                productDao.updateStock(item.getProduct().getId(), item.getQuantity());
            }


            session.removeAttribute("cart"); // Svuota il carrello
            session.setAttribute("orderId", idOrdine); // Passa l'ID alla pagina di conferma
            response.sendRedirect(request.getContextPath() + "/common/orderConfirmation.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
        }
    }
}
