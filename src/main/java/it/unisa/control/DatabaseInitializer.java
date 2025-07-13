package it.unisa.control;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import it.unisa.model.ProductDAO;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DataSource ds = (DataSource) context.getAttribute("DataSource");
        System.out.println("Avvio caricamento immagini prodotti...");

        try {
            updateProductImages(context, ds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProductImages(ServletContext context, DataSource ds) throws SQLException {
        ProductDAO productDao = new ProductDAO(ds);
        int updatedCount = 0;
        int i=0;
        for (i=1; i<=25; i++) {
            String[] extensions = {".jpg", ".png"};
            InputStream imgStream = null;
            
            for(String ext : extensions) {
                String imagePath = "./images/" + i + ext;
                imgStream = context.getResourceAsStream(imagePath);
                if (imgStream != null) {
                    break; // Trovata immagine, esci dal ciclo delle estensioni
                }
            }

            if (imgStream != null) {
                productDao.updateImage(i, imgStream);
                updatedCount++;
            } else {
                System.err.println("ATTENZIONE: Immagine non trovata per il prodotto con ID: " + i);
            }
        }
        System.out.println("Aggiornamento immagini completato. Prodotti aggiornati: " + updatedCount);
    }
    
    public void contextDestroyed(ServletContextEvent sce) {
    	//Nothing
    }

}