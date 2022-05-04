package org.foi.nwtis.tskobic.zadaca_3.zrna;

import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.tskobic.ws.aerodromi.Aerodrom;
import org.foi.nwtis.tskobic.ws.aerodromi.Aerodromi;
import org.foi.nwtis.tskobic.ws.aerodromi.WsAerodromi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

@RequestScoped
@Named("aerodromiWs")
public class AerodromiWs {

	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/korisnici?wsdl")
	private Aerodromi service;
	
	private List<Aerodrom> aerodromi = new ArrayList<>();
	
	public List<Aerodrom> getAerodromi() {
		this.aerodromi = this.dajSveAerodrome();
		return this.aerodromi;
	}
	
	public void setAerodromi(List<Aerodrom> aerodromi) {
		this.aerodromi = aerodromi;
	}

	public List<Aerodrom> dajSveAerodrome() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<Aerodrom> lAerodromi = wsAerodromi.dajSveAerodrome();

		return lAerodromi;
	}
}
