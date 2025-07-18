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
import it.unisa.model.ProductDTO.Categoria;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchQuery = request.getParameter("searchQuery");
		String categoryQuery = request.getParameter("category");

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
				products = productDao.doRetrieveAll("Nome");
				pageTitle = "Tutti i Prodotti";

			}

			request.setAttribute("products", products);
			request.setAttribute("pageTitle", pageTitle);

		} catch (SQLException e) {
			System.err.println("Errore durante la ricerca del prodotto: " + e.getMessage());
			request.setAttribute("error", "Errore del database durante la ricerca.");
			response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/common/searchResult.jsp");
		dispatcher.forward(request, response);
	}
}
