package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Klasa Paginacija.
 */
@FacesConfig
@ApplicationScoped
@Named("paginacija")
public class Paginacija {

	/** Kontekst JSF aplikacije. */
	@Inject
	protected FacesContext facesContext;

	/** Postavke baze podataka. */
	PostavkeBazaPodataka konfig;
	
	/** Broj redova. */
	String brojRedova;

	/**
	 * Metoda za inicijalizaciju.
	 */
	@PostConstruct
	void init() {
		konfig = (PostavkeBazaPodataka) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("Postavke");
		brojRedova = konfig.dajPostavku("stranica.brojRedova");
	}

	/**
	 * Vraća broj redova.
	 *
	 * @return the broj redova
	 */
	public String getBrojRedova() {
		return brojRedova;
	}

	/**
	 * Postavlja broj redova.
	 *
	 * @param brojRedova broj redova
	 */
	public void setBrojRedova(String brojRedova) {
		this.brojRedova = brojRedova;
	}
	
}
