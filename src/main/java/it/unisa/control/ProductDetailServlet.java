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

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused") //Per sopprimere dead code
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID prodotto non valido");
			return;
		}

		DataSource ds = (DataSource) getServletContext().getAttribute("Datasource");
		ProductDAO product = new ProductDAO(ds);

		try {
			ProductDTO pd = product.doRetrieveByKey(id);
			if (product == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Errore recupero prodotto.");
				return;
			}
			request.setAttribute("product", pd);
		} catch (SQLException e) {
			System.err.println("Errore recupero dettagli prodotto." + e.getMessage());
			request.setAttribute("error", "Errore recupero prodotto.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/product-detail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
