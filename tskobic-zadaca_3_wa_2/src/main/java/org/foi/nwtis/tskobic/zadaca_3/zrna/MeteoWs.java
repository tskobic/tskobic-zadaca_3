package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.ws.meteo.Meteo;
import org.foi.nwtis.tskobic.ws.meteo.MeteoPodaci;
import org.foi.nwtis.tskobic.ws.meteo.WsMeteo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

@RequestScoped
@Named("meteoWs")
public class MeteoWs {

	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/meteo?wsdl")
	private Meteo service;
	
	private MeteoPodaci meteoPodaci = new MeteoPodaci();
	private String icao = "LDZA";

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public MeteoPodaci getMeteoPodaci() {
		this.meteoPodaci = this.dajMeteo();
		return this.meteoPodaci;
	}

	public void setMeteoPodaci(MeteoPodaci meteoPodaci) {
		this.meteoPodaci = meteoPodaci;
	}

	public MeteoPodaci dajMeteo() {
		service = new Meteo();

		WsMeteo wsMeteo = service.getWsMeteoPort();
		MeteoPodaci lMeteoPodaci = wsMeteo.dajMeteo(icao);

		return lMeteoPodaci;
	}
}
