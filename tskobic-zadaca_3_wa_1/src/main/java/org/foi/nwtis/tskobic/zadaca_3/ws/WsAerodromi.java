package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.List;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.AvionLeti;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiDAO;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiDolasciDAO;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiPolasciDAO;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiPraceniDAO;

import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

/**
 * Klasa WsAerodromi
 */
@WebService(serviceName = "aerodromi")
public class WsAerodromi {

	/** Kontekst web servisa. */
	@Resource
	private WebServiceContext wsContext;

	/**
	 * Daje sve aerodrome.
	 *
	 * @return the list
	 */
	@WebMethod
	public List<Aerodrom> dajSveAerodrome() {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> aerodromi = null;

		AerodromiDAO aerodromiDAO = new AerodromiDAO();
		aerodromi = aerodromiDAO.dohvatiSveAerodrome(konfig);

		return aerodromi;
	}

	/**
	 * Daje praćene aerodrome.
	 *
	 * @return the list
	 */
	@WebMethod
	public List<Aerodrom> dajAerodromePreuzimanje() {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> praceniAerodromi = null;

		AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();
		praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);

		return praceniAerodromi;
	}

	/**
	 * Dodaje aerodrom za praćenje.
	 *
	 * @param icao icao aerodroma
	 * @return true, ako je uspješno dodavanje
	 */
	@WebMethod
	public boolean dodajAerodromPreuzimanje(@WebParam(name = "icao") String icao) {
		PostavkeBazaPodataka konfig = dajPBP();
		AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();

		boolean status = aerodromiPraceniDAO.dodajAerodromZaPracenje(icao, konfig);

		return status;
	}

	/**
	 * Daje polaske aviona s određenog aerodroma na određeni dan.
	 *
	 * @param icao icao aerodroma
	 * @param dan  dan
	 * @return the list
	 */
	@WebMethod
	public List<AvionLeti> dajPolaske(@WebParam(name = "icao") String icao, @WebParam(name = "dan") String dan) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<AvionLeti> aerodromPolasci = null;

		AerodromiPolasciDAO aerodromiPolasciDAO = new AerodromiPolasciDAO();
		aerodromPolasci = aerodromiPolasciDAO.dohvatiPolaskeNaDan(icao, dan, konfig);

		return aerodromPolasci;
	}

	/**
	 * Daje dolaske aviona s određenog aerodroma na određeni dan.
	 *
	 * @param icao icao aerodroma
	 * @param dan  dan
	 * @return the list
	 */
	@WebMethod
	public List<AvionLeti> dajDolaske(@WebParam(name = "icao") String icao, @WebParam(name = "dan") String dan) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<AvionLeti> aerodromDolasci = null;

		AerodromiDolasciDAO aerodromiDolasciDAO = new AerodromiDolasciDAO();
		aerodromDolasci = aerodromiDolasciDAO.dohvatiDolaskeNaDan(icao, dan, konfig);

		return aerodromDolasci;
	}

	/**
	 * Daje najbliži aerodrom na temelju proslijeđenih koordinata i na temelju
	 * drugog parametra radi li se o praćenom ili svim aerodromima.
	 *
	 * @param lokacija lokacija
	 * @param vrsta    vrsta aerodroma (svi ili praćeni)
	 * @return the aerodrom
	 */
	@WebMethod
	public Aerodrom dajNajbliziAerodrom(@WebParam(name = "lokacija") Lokacija lokacija,
			@WebParam(name = "vrsta") boolean vrsta) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> aerodromi = null;

		if (vrsta) {
			AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();
			aerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);
		} else {
			AerodromiDAO aerodromiDAO = new AerodromiDAO();
			aerodromi = aerodromiDAO.dohvatiSveAerodrome(konfig);
		}

		float minUdaljenost = 0;
		boolean prvi = true;
		Aerodrom najbliziAerodrom = null;
		for (Aerodrom a : aerodromi) {
			float udaljenost = udaljenost(Float.parseFloat(lokacija.getLatitude()),
					Float.parseFloat(lokacija.getLongitude()), Float.parseFloat(a.getLokacija().getLatitude()),
					Float.parseFloat(a.getLokacija().getLongitude()));
			if (prvi) {
				prvi = false;
				minUdaljenost = udaljenost;
				najbliziAerodrom = a;
			} else {
				if (udaljenost < minUdaljenost) {
					minUdaljenost = udaljenost;
					najbliziAerodrom = a;
				}
			}
		}

		return najbliziAerodrom;
	}

	/**
	 * Izračunava udaljenost između dvije koordinate.
	 *
	 * @param gs1 geografska širina prve lokacije
	 * @param gd1 geografska duljina prve lokacije
	 * @param gs2 geografska širina druge lokacije
	 * @param gd2 geograska duljina druge lokacije
	 * @return udaljenost
	 */
	private float udaljenost(float gs1, float gd1, float gs2, float gd2) {
		double polumjerZemlje = 6371000;
		double dGs = Math.toRadians(gs2 - gs1);
		double dGd = Math.toRadians(gd2 - gd1);
		double a = Math.sin(dGs / 2) * Math.sin(dGs / 2)
				+ Math.cos(Math.toRadians(gs1)) * Math.cos(Math.toRadians(gs2)) * Math.sin(dGd / 2) * Math.sin(dGd / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float udalj = (float) (polumjerZemlje * c);
		udalj = udalj / 1000;

		return udalj;
	}

	/**
	 * Daje postavke baze podataka iz konteksta servleta.
	 *
	 * @return postavke baza podataka
	 */
	private PostavkeBazaPodataka dajPBP() {
		ServletContext context = (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		PostavkeBazaPodataka pbp = (PostavkeBazaPodataka) context.getAttribute("Postavke");

		return pbp;
	}

}
