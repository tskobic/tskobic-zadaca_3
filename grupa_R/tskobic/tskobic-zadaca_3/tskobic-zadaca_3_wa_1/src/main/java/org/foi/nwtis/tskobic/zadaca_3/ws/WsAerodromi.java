package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiDAO;
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
	public boolean  dodajAerodromPreuzimanje(@WebParam(name = "icao") String icao) {
		PostavkeBazaPodataka konfig = dajPBP();
		
		AerodromiDAO aerodromiDAO = new AerodromiDAO();
		AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();
		List<Aerodrom> aerodromi = aerodromiDAO.dohvatiSveAerodrome(konfig);
		List<Aerodrom> praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);
		
		boolean status;
		
		List<Aerodrom> fAerodromi = aerodromi.stream().filter(x -> x.getIcao().equals(icao))
				.collect(Collectors.toList());
		if (fAerodromi.isEmpty()) {
			status = false;
		} else {
			List<Aerodrom> fPraceniAerodromi = praceniAerodromi.stream().filter(x -> x.getIcao().equals(icao))
					.collect(Collectors.toList());
			if (!fPraceniAerodromi.isEmpty()) {
				status = false;
			} else {
				status = aerodromiPraceniDAO.dodajAerodromZaPracenje(icao, konfig);
			}
		}

		return status;
	}

	private PostavkeBazaPodataka dajPBP() {
		ServletContext context = (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		PostavkeBazaPodataka pbp = (PostavkeBazaPodataka) context.getAttribute("Postavke");
		
		return pbp;
	}

}
