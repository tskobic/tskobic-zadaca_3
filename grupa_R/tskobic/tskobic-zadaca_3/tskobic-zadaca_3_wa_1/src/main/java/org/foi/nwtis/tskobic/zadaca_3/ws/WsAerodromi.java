package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.AvionLeti;
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
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

@WebService(serviceName = "aerodromi")
public class WsAerodromi {

	@Resource
	private WebServiceContext wsContext;

	@WebMethod
	public List<Aerodrom> dajSveAerodrome() {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> aerodromi = null;

		AerodromiDAO aerodromiDAO = new AerodromiDAO();
		aerodromi = aerodromiDAO.dohvatiSveAerodrome(konfig);

		return aerodromi;
	}

	@WebMethod
	public List<Aerodrom> dajAerodromePreuzimanje() {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> praceniAerodromi = null;

		AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();
		praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);

		return praceniAerodromi;
	}

	@WebMethod
	public boolean dodajAerodromPreuzimanje(@WebParam(name = "icao") String icao) {
		PostavkeBazaPodataka konfig = dajPBP();
		AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();

		boolean status = aerodromiPraceniDAO.dodajAerodromZaPracenje(icao, konfig);

		return status;
	}

	@WebMethod
	public List<AvionLeti> dajPolaske(@WebParam(name = "icao") String icao, @WebParam(name = "dan") String dan) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<AvionLeti> aerodromPolasci = null;

		AerodromiPolasciDAO aerodromiPolasciDAO = new AerodromiPolasciDAO();
		aerodromPolasci = aerodromiPolasciDAO.dohvatiPolaskeNaDan(icao, dan, konfig);

		return aerodromPolasci;
	}

	@WebMethod
	public List<AvionLeti> dajDolaske(@WebParam(name = "icao") String icao, @WebParam(name = "dan") String dan) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<AvionLeti> aerodromDolasci = null;

		AerodromiDolasciDAO aerodromiDolasciDAO = new AerodromiDolasciDAO();
		aerodromDolasci = aerodromiDolasciDAO.dohvatiDolaskeNaDan(icao, dan, konfig);

		return aerodromDolasci;
	}

	private PostavkeBazaPodataka dajPBP() {
		ServletContext context = (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		PostavkeBazaPodataka pbp = (PostavkeBazaPodataka) context.getAttribute("Postavke");

		return pbp;
	}

}
