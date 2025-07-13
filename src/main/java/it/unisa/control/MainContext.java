package it.unisa.control;

import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {
	
	
	    public void contextInitialized(ServletContextEvent sce) {
	        ServletContext context = sce.getServletContext();
	        
	        DataSource ds = null;
	        try {
	        	Context initCtx = new InitialContext();
	        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	        	
	        	ds = (DataSource) envCtx.lookup("jdbc/ROOTED_TREE");
	        }catch(NamingException e) {
	        	System.out.println("Error" + e.getMessage() );
	        }
	        
	        context.setAttribute("DataSource",ds);
	        System.out.println("DataSource creation..."+ds.toString());
	    }

	   
	    public void contextDestroyed(ServletContextEvent sce) {
	    	//Nothing
	    }

}
