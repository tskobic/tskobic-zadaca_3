package org.foi.nwtis.tskobic.zadaca_3.zrna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.foi.nwtis.rest.klijenti.LIQKlijent;
import org.foi.nwtis.rest.klijenti.NwtisRestIznimka;
import org.foi.nwtis.tskobic.ws.aerodromi.Aerodrom;
import org.foi.nwtis.tskobic.ws.aerodromi.Aerodromi;
import org.foi.nwtis.tskobic.ws.aerodromi.AvionLeti;
import org.foi.nwtis.tskobic.ws.aerodromi.WsAerodromi;
import org.foi.nwtis.tskobic.ws.aerodromi.Lokacija;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

@RequestScoped
@Named("aerodromiWs")
public class AerodromiWs {

	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/aerodromi?wsdl")
	private Aerodromi service;

	public static String icao;
	private String dan;
	private String odabir;
	private String adresa;
	private String koordinate;
	private String praceni;
	private Lokacija lokacija;
	private Aerodrom aerodrom;
	private List<Aerodrom> aerodromi = new ArrayList<>();
	private List<Aerodrom> praceniAerodromi = new ArrayList<>();
	private List<AvionLeti> aerodromLetovi = new ArrayList<>();

	@PostConstruct
	public void reset() {
		icao = null;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		AerodromiWs.icao = icao;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKoordinate() {
		return this.koordinate;
	}

	public void setKoordinate(String koordinate) {
		this.koordinate = koordinate;
	}

	public String getPraceni() {
		return praceni;
	}

	public void setPraceni(String praceni) {
		this.praceni = praceni;
	}

	public Lokacija getLokacija() {
		this.lokacija = this.dajLokaciju();
		return this.lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
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

	public Aerodrom getAerodrom() {
		return this.aerodrom;
	}

	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
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

		if (odabir == null) {
			return null;
		} else {
			if (odabir.equals("Polasci")) {
				aerodromLetovi = dajPolaske();
			} else if (odabir.equals("Dolasci")) {
				aerodromLetovi = dajDolaske();
			} else if (odabir.equals("Polasci i dolasci")) {
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

	public void dajNajbliziAerodrom() {
		service = new Aerodromi();

		Lokacija lokacija = dajLokaciju();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		Aerodrom aerodrom = wsAerodromi.dajNajbliziAerodrom(lokacija, Boolean.parseBoolean(praceni));

		this.aerodrom = aerodrom;
	}

	public Lokacija dajLokaciju() {
		service = new Aerodromi();

		LIQKlijent liqKlijent = new LIQKlijent(Aplikacija.apikey);
		Lokacija lokacija = null;

		try {
			lokacija = pretvoriLokaciju(liqKlijent.getGeoLocation(adresa));
		} catch (NwtisRestIznimka e) {
			e.printStackTrace();
		}

		this.koordinate = lokacija.getLatitude() + " " + lokacija.getLongitude();

		return lokacija;
	}

	public Lokacija pretvoriLokaciju(org.foi.nwtis.rest.podaci.Lokacija l) {
		Lokacija lokacija = new Lokacija();
		lokacija.setLatitude(l.getLatitude());
		lokacija.setLongitude(l.getLongitude());

		return lokacija;
	}

}
