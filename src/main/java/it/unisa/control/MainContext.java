package it.unisa.control;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.InputStream;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;

@WebListener
public class MainContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        // 1. Creazione del DataSource
        DataSource ds = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/ROOTED_TREE");
        } catch (NamingException e) {
            System.err.println("Errore durante la creazione del DataSource: " + e.getMessage());
        }
        
        context.setAttribute("DataSource", ds);
        System.out.println("DataSource creato e salvato nel contesto.");

        if (ds != null) {
            System.out.println("Avvio caricamento immagini prodotti...");
            try {
                updateProductImages(context, ds);
            } catch (SQLException e) {
                System.err.println("Errore SQL durante il seeding delle immagini.");
                e.printStackTrace();
            }
        }
    }

    private void updateProductImages(ServletContext context, DataSource ds) throws SQLException {
        ProductDAO productDao = new ProductDAO(ds);
        int updatedCount = 0;
        
        // Ciclo per caricare le 25 immagini
        for (int i = 1; i <= 25; i++) {
            InputStream imgStream = null;
            

            String imagePath = "/images/1.png"; 
            imgStream = context.getResourceAsStream(imagePath);
            
            if (imgStream != null) {
                productDao.updateImage(i, imgStream);
                updatedCount++;
            } else {
                System.err.println("ATTENZIONE: Immagine non trovata al percorso: " + imagePath + " per il prodotto con ID: " + i);
            }
        }
        System.out.println("Aggiornamento immagini completato. Prodotti aggiornati: " + updatedCount);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Logica di pulizia se necessaria
    }
}
