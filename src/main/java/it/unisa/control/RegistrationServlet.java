package it.unisa.control;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.*;
import java.sql.Date;

import javax.sql.DataSource;

import it.unisa.model.UserDAO;
import it.unisa.model.UserDTO;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String data = request.getParameter("dataNascita");

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UserDAO userDao = new UserDAO(ds);

        try {
            if (userDao.doRetrieveByMail(email) != null) {
                request.setAttribute("errorMessage", "Un account con questa email esiste già. Prova ad accedere.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("./common/register.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String hashedPw = HtmlHash.toHash(password);
            Date dataNascita = Date.valueOf(data);

            UserDTO newUser = new UserDTO();
            newUser.setNome(HtmlDecoder.encodeHtmlEntities(name));
            newUser.setCognome(HtmlDecoder.encodeHtmlEntities(cognome));
            newUser.setEmail(HtmlDecoder.encodeHtmlEntities(email));
            newUser.setPassword(hashedPw);
            newUser.setDataNascita(dataNascita);
            
            userDao.doSave(newUser);
            
            request.setAttribute("successMessage", "Registrazione completata! Ora puoi accedere.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./common/register.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() +"/common/error500.jsp");
        }
    }
}