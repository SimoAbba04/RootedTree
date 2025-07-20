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
import it.unisa.model.*;

@WebServlet("/admin/OrderServlet")
public class AdminOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        OrderDAO orderDao = new OrderDAO(ds);
        UserDAO userDao = new UserDAO(ds);
        OrderDetailDAO detailDao = new OrderDetailDAO(ds);

        try {
            Collection<OrderDTO> orders = null;
            String userEmail = request.getParameter("userEmail");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            if (userEmail != null && !userEmail.isEmpty()) {
                // Filtra per cliente
                UserDTO user = userDao.doRetrieveByMail(userEmail);
                if (user != null) {
                    orders = orderDao.doRetrieveByAccount(user.getID());
                }
            } else if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                // Filtra per intervallo di date
                orders = orderDao.doRetrieveByDateRange(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
            } else {
                // Nessun filtro, recupera tutti gli ordini
                orders = orderDao.doRetrieveAll("DataOrdine DESC");
            }

            // Per ogni ordine, recupera i suoi dettagli (prodotti, quantità, etc.)
            if (orders != null) {
                for (OrderDTO order : orders) {
                    Collection<OrderDetailDTO> details = detailDao.doRetrieveByOrdine(order.getId());
                    order.setDetails(details);
                }
            }

            request.setAttribute("orders", orders);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/orderManagement.jsp");
        dispatcher.forward(request, response);
    }
}
