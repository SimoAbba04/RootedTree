package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;
import it.unisa.model.ProductDTO;

@WebServlet("/admin/productControlServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminProductControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);
        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.equals("edit")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("product", productDao.doRetrieveByKey(id));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/productForm.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else if (action.equals("add")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/productForm.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else if (action.equals("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    productDao.doDelete(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/SearchServlet?source=admin");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO productDao = new ProductDAO(ds);
        
        try {
            String idStr = request.getParameter("id");
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String categoria = request.getParameter("categoria");

            InputStream imageStream = null;
            Part filePart = request.getPart("immagine");
            if (filePart != null && filePart.getSize() > 0) {
                imageStream = filePart.getInputStream();
            }

            ProductDTO product = new ProductDTO();
            product.setNome(nome);
            product.setDescrizione(descrizione);
            product.setPrezzo(prezzo);
            product.setDisponibilità(stock);
            product.setCategoria(ProductDTO.Categoria.fromString(categoria));

            if (idStr != null && !idStr.isEmpty()) {
                product.setId(Integer.parseInt(idStr));
                productDao.doUpdate(product, imageStream);
            } else {
                productDao.doSave(product, imageStream);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/common/error500.jsp");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/SearchServlet?source=admin");
    }
}