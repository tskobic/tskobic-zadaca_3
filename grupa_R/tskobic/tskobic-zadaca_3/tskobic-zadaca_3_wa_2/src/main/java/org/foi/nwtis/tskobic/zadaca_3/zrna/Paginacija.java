package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@FacesConfig
@ApplicationScoped
@Named("paginacija")
public class Paginacija {

	@Inject
	protected FacesContext facesContext;

	PostavkeBazaPodataka konfig;
	String brojRedova;

	@PostConstruct
	void init() {
		konfig = (PostavkeBazaPodataka) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("Postavke");
		brojRedova = konfig.dajPostavku("stranica.brojRedova");
	}

	public String getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(String brojRedova) {
		this.brojRedova = brojRedova;
	}
	
}
