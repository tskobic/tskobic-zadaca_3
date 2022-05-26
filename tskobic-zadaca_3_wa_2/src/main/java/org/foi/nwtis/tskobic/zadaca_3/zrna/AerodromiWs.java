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

/**
 * Klasa AerodromiWs.
 */
@RequestScoped
@Named("aerodromiWs")
public class AerodromiWs {

	/** Aerodromi servis. */
	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/aerodromi?wsdl")
	private Aerodromi service;

	/** icao. */
	public static String icao;
	
	/** dan. */
	private String dan;
	
	/** odabir. */
	private String odabir;
	
	/** adresa. */
	private String adresa;
	
	/** koordinate. */
	private String koordinate;
	
	/** praćeni. */
	private String praceni;
	
	/** lokacija. */
	private Lokacija lokacija;
	
	/** aerodrom. */
	private Aerodrom aerodrom;
	
	/** Aerodromi. */
	private List<Aerodrom> aerodromi = new ArrayList<>();
	
	/** Praćeni aerodromi. */
	private List<Aerodrom> praceniAerodromi = new ArrayList<>();
	
	/** Aerodrom letovi. */
	private List<AvionLeti> aerodromLetovi = new ArrayList<>();

	/**
	 * Metoda za inicijalizaciju.
	 */
	@PostConstruct
	public void reset() {
		icao = null;
	}

	/**
	 * Vraća icao.
	 *
	 * @return the icao
	 */
	public String getIcao() {
		return icao;
	}

	/**
	 * Postavlja icao.
	 *
	 * @param icao the new icao
	 */
	public void setIcao(String icao) {
		AerodromiWs.icao = icao;
	}

	/**
	 * Vraća dan.
	 *
	 * @return the dan
	 */
	public String getDan() {
		return dan;
	}

	/**
	 * Postavlja dan.
	 *
	 * @param dan dan
	 */
	public void setDan(String dan) {
		this.dan = dan;
	}

	/**
	 * Vraća odabir.
	 *
	 * @return the odabir
	 */
	public String getOdabir() {
		return odabir;
	}

	/**
	 * Postavlja odabir.
	 *
	 * @param odabir odabir
	 */
	public void setOdabir(String odabir) {
		this.odabir = odabir;
	}

	/**
	 * Vraća adresu.
	 *
	 * @return the adresa
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * Postavlja adresu.
	 *
	 * @param adresa adresa
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * Vraća koordinate.
	 *
	 * @return the koordinate
	 */
	public String getKoordinate() {
		return this.koordinate;
	}

	/**
	 * Postavlja koordinate.
	 *
	 * @param koordinate koordinate
	 */
	public void setKoordinate(String koordinate) {
		this.koordinate = koordinate;
	}

	/**
	 * Vraća praćene.
	 *
	 * @return the praceni
	 */
	public String getPraceni() {
		return praceni;
	}

	/**
	 * Postavlja praćeni.
	 *
	 * @param praceni the new praceni
	 */
	public void setPraceni(String praceni) {
		this.praceni = praceni;
	}

	/**
	 * Vraća lokaciju.
	 *
	 * @return the lokacija
	 */
	public Lokacija getLokacija() {
		this.lokacija = this.dajLokaciju();
		return this.lokacija;
	}

	/**
	 * Postavlja lokacijo.
	 *
	 * @param lokacija the new lokacija
	 */
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	/**
	 * Vraća aerodromi.
	 *
	 * @return the aerodromi
	 */
	public List<Aerodrom> getAerodromi() {
		this.aerodromi = this.dajSveAerodrome();
		return this.aerodromi;
	}

	/**
	 * Postavlja aerodromi.
	 *
	 * @param aerodromi aerodromi
	 */
	public void setAerodromi(List<Aerodrom> aerodromi) {
		this.aerodromi = aerodromi;
	}

	/**
	 * Vraća praćene aerodrome.
	 *
	 * @return the praceni aerodromi
	 */
	public List<Aerodrom> getPraceniAerodromi() {
		this.praceniAerodromi = this.dajAerodromePreuzimanje();
		return this.praceniAerodromi;
	}

	/**
	 * Postavlja praćene aerodrome.
	 *
	 * @param praceniAerodromi the new praceni aerodromi
	 */
	public void setPraceniAerodromi(List<Aerodrom> praceniAerodromi) {
		this.praceniAerodromi = praceniAerodromi;
	}

	/**
	 * Vraća letove aviona .
	 *
	 * @return the aerodrom letovi
	 */
	public List<AvionLeti> getAerodromLetovi() {
		this.aerodromLetovi = this.dajLetove();
		return this.aerodromLetovi;
	}

	/**
	 * Postavlja letove aviona.
	 *
	 * @param aerodromLetovi aerodrom letovi
	 */
	public void setAerodromLetovi(List<AvionLeti> aerodromLetovi) {
		this.aerodromLetovi = aerodromLetovi;
	}

	/**
	 * Vraća aerodrom.
	 *
	 * @return the aerodrom
	 */
	public Aerodrom getAerodrom() {
		return this.aerodrom;
	}

	/**
	 * Postavlja aerodrom.
	 *
	 * @param aerodrom the new aerodrom
	 */
	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
	}

	/**
	 * Daje sve aerodrome.
	 *
	 * @return the list
	 */
	public List<Aerodrom> dajSveAerodrome() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<Aerodrom> lAerodromi = wsAerodromi.dajSveAerodrome();

		return lAerodromi;
	}

	/**
	 * Daje praćene aaerodrome.
	 *
	 * @return the list
	 */
	public List<Aerodrom> dajAerodromePreuzimanje() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<Aerodrom> lPraceniAerodromi = wsAerodromi.dajAerodromePreuzimanje();

		return lPraceniAerodromi;
	}

	/**
	 * Dodaje aerodrom za praćenje.
	 *
	 * @return true, ako uspješno
	 */
	public boolean dodajAerodromPreuzimanje() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		boolean status = wsAerodromi.dodajAerodromPreuzimanje(icao);

		return status;
	}

	/**
	 * Daje letove.
	 *
	 * @return the list
	 */
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

	/**
	 * Daje polaske.
	 *
	 * @return the list
	 */
	public List<AvionLeti> dajPolaske() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<AvionLeti> lAerodromPolasci = wsAerodromi.dajPolaske(icao, dan);

		return lAerodromPolasci;
	}

	/**
	 * Daje dolaske.
	 *
	 * @return the list
	 */
	public List<AvionLeti> dajDolaske() {
		service = new Aerodromi();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		List<AvionLeti> lAerodromDolasci = wsAerodromi.dajDolaske(icao, dan);

		return lAerodromDolasci;
	}

	/**
	 * Daje najbliži aerodrom.
	 */
	public void dajNajbliziAerodrom() {
		service = new Aerodromi();

		Lokacija lokacija = dajLokaciju();

		WsAerodromi wsAerodromi = service.getWsAerodromiPort();
		Aerodrom aerodrom = wsAerodromi.dajNajbliziAerodrom(lokacija, Boolean.parseBoolean(praceni));

		this.aerodrom = aerodrom;
	}

	/**
	 * Daje lokaciju.
	 *
	 * @return the lokacija
	 */
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

	/**
	 * Pretvara lokaciju iz jednog tipa u drugi.
	 *
	 * @param l lokacija
	 * @return the lokacija
	 */
	public Lokacija pretvoriLokaciju(org.foi.nwtis.rest.podaci.Lokacija l) {
		Lokacija lokacija = new Lokacija();
		lokacija.setLatitude(l.getLatitude());
		lokacija.setLongitude(l.getLongitude());

		return lokacija;
	}

}
