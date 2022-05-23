package org.foi.nwtis.tskobic.zadaca_3.zrna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.foi.nwtis.tskobic.ws.aerodromi.Aerodrom;
import org.foi.nwtis.tskobic.ws.aerodromi.Aerodromi;
import org.foi.nwtis.tskobic.ws.aerodromi.AvionLeti;
import org.foi.nwtis.tskobic.ws.aerodromi.WsAerodromi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

@RequestScoped
@Named("aerodromiWs")
public class AerodromiWs {

	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/aerodromi?wsdl")
	private Aerodromi service;
	
	private String icao;
	private String dan;
	private String odabir;
	private List<Aerodrom> aerodromi = new ArrayList<>();
	private List<Aerodrom> praceniAerodromi = new ArrayList<>();
	private List<AvionLeti> aerodromLetovi = new ArrayList<>();
	
	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
		MeteoWs.icao = icao;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public String getOdabir() {
		return odabir;
	}

	public void setOdabir(String odabir) {
		this.odabir = odabir;
	}

	public List<Aerodrom> getAerodromi() {
		this.aerodromi = this.dajSveAerodrome();
		return this.aerodromi;
	}

	public void setAerodromi(List<Aerodrom> aerodromi) {
		this.aerodromi = aerodromi;
	}

	public List<Aerodrom> getPraceniAerodromi() {
		this.praceniAerodromi = this.dajAerodromePreuzimanje();
		return this.praceniAerodromi;
	}

	public void setPraceniAerodromi(List<Aerodrom> praceniAerodromi) {
		this.praceniAerodromi = praceniAerodromi;
	}

	public List<AvionLeti> getAerodromLetovi() {
		this.aerodromLetovi = this.dajLetove();
		return this.aerodromLetovi;
	}

	public void setAerodromLetovi(List<AvionLeti> aerodromLetovi) {
		this.aerodromLetovi = aerodromLetovi;
	}

	public List<Aerodrom> dajSveAerodrome() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<Aerodrom> lAerodromi = wsAerodromi.dajSveAerodrome();

		return lAerodromi;
	}
	
	public List<Aerodrom> dajAerodromePreuzimanje() {
		service = new Aerodromi();
		
		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<Aerodrom> lPraceniAerodromi = wsAerodromi.dajAerodromePreuzimanje();
		
		return lPraceniAerodromi;
	}
	
	public boolean dodajAerodromPreuzimanje() {
		service = new Aerodromi();
		
		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		boolean status = wsAerodromi.dodajAerodromPreuzimanje(icao);
		
		return status;
	}
	
	public List<AvionLeti> dajLetove() {
		List<AvionLeti> aerodromLetovi = null;
		
		if(odabir == null) {
			return null;
		} else {
			if(odabir.equals("Polasci")) {
				aerodromLetovi = dajPolaske();
			} else if(odabir.equals("Dolasci")) {
				aerodromLetovi = dajDolaske();
			} else if(odabir.equals("Polasci i dolasci")) {
				aerodromLetovi = dajPolaske();
				aerodromLetovi.addAll(dajDolaske());
				aerodromLetovi.sort(Comparator.comparingInt(AvionLeti::getFirstSeen));
			}	
		}
		
		return aerodromLetovi;
	}
	
	public List<AvionLeti> dajPolaske() {
		service = new Aerodromi();
		
		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<AvionLeti> lAerodromPolasci = wsAerodromi.dajPolaske(icao, dan);
		
		return lAerodromPolasci;
	}
	
	public List<AvionLeti> dajDolaske() {
		service = new Aerodromi();
		
		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<AvionLeti> lAerodromDolasci = wsAerodromi.dajDolaske(icao, dan);
		
		return lAerodromDolasci;
	}
	
}
