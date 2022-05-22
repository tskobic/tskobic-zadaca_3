package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.List;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.NwtisRestIznimka;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rest.podaci.MeteoPodaci;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiDAO;

import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

@WebService(serviceName = "meteo")
public class WsMeteo {
	@Resource
	private WebServiceContext wsContext;

	@WebMethod
	public MeteoPodaci dajMeteo(String icao) {
		PostavkeBazaPodataka konfig = dajPBP();
		List<Aerodrom> aerodromi = null;

		AerodromiDAO aerodromiDAO = new AerodromiDAO();
		aerodromi = aerodromiDAO.dohvatiSveAerodrome(konfig);

		Aerodrom aerodrom = null;

		for (Aerodrom a : aerodromi) {
			if (a.getIcao().compareTo(icao) == 0) {
				aerodrom = a;
			}
		}

		Lokacija lokacija = aerodrom.getLokacija();
		
		String apiKey = konfig.dajPostavku("OpenWeatherMap.apikey");

		OWMKlijent owmKlijent = new OWMKlijent(apiKey);
		MeteoPodaci meteoPodaci = null;
		try {
			meteoPodaci = owmKlijent.getRealTimeWeather(lokacija.getLatitude(), lokacija.getLongitude());
		} catch (NwtisRestIznimka e) {
			e.printStackTrace();
		}

		return meteoPodaci;
	}

	private PostavkeBazaPodataka dajPBP() {
		ServletContext context = (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		PostavkeBazaPodataka pbp = (PostavkeBazaPodataka) context.getAttribute("Postavke");

		return pbp;
	}

}
