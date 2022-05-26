package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

@Eager
@ApplicationScoped
public class Aplikacija {
	
	@Inject
	protected ServletContext context;

	PostavkeBazaPodataka konfig;
	static String apikey;
	
    @PostConstruct
    public void init() {
		konfig = (PostavkeBazaPodataka) context.getAttribute("Postavke");
		apikey = konfig.dajPostavku("LocationIQ.token");
    }
}
