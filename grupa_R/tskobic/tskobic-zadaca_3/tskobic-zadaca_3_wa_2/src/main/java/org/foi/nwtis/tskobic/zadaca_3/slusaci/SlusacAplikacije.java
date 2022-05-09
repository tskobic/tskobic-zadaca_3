package org.foi.nwtis.tskobic.zadaca_3.slusaci;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SlusacAplikacije
 *
 */
@WebListener
public class SlusacAplikacije implements ServletContextListener {
	
    /**
     * Default constructor. 
     */
    public SlusacAplikacije() {
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext context = sce.getServletContext();
//		String nazivDatoteke = context.getInitParameter("konfiguracija");
//		KonfiguracijaBP konfig = null;
//		String putanja = context.getRealPath("/WEB-INF") + java.io.File.separator; 
//		try {
//			konfig = new PostavkeBazaPodataka(putanja + nazivDatoteke);
//			konfig.ucitajKonfiguraciju();
//			context.setAttribute("Postavke", konfig);
//			System.out.println("Učitana konfiguracijska datoteka.");
//		} catch (NeispravnaKonfiguracija e1) {
//			e1.printStackTrace();
//			return;
//		}

		ServletContextListener.super.contextInitialized(sce);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.removeAttribute("Postavke");
		
		ServletContextListener.super.contextDestroyed(sce);
	}

}
