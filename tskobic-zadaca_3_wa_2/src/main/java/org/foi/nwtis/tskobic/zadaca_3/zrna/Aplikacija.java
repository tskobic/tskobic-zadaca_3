package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

/**
 * Klasa Aplikacija.
 */
@Pokretanje
@ApplicationScoped
public class Aplikacija {
	
	/** Kontekst servleta. */
	@Inject
	protected ServletContext context;

	/** Postavke baze podataka. */
	PostavkeBazaPodataka konfig;
	
	/** api ključ. */
	static String apikey;
	
    /**
     * Metoda za inicijalizaciju.
     */
    @PostConstruct
    public void init() {
		konfig = (PostavkeBazaPodataka) context.getAttribute("Postavke");
		apikey = konfig.dajPostavku("LocationIQ.token");
    }
}
