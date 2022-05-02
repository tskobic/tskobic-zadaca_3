package org.foi.nwtis.tskobic.zadaca_3.zrna;

import java.util.List;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.tskobic.ws.aerodromi.Aerodromi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

@RequestScoped
@Named("aerodromiWs")
public class AerodromiWs {

	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/korisnici?wsdl")
	private Aerodromi service;
	
	public List<Aerodrom> dajSveAerodrome() {

		
		service = new Aerodromi();
		
		List<Aerodrom> aerodromi = service.
	}
}
