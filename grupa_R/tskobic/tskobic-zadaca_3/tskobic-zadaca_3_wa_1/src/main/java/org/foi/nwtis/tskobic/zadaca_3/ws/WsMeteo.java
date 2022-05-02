package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.NwtisRestIznimka;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rest.podaci.MeteoPodaci;

import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;

@WebService(serviceName = "meteo")
public class WsMeteo {

	@Resource
	private WebServiceContext wsContext;
	
	// TODO preuzmi podatak iz postavki
	String apiKey = "xxxxx";

	@WebMethod
	public MeteoPodaci dajMeteo(String icao){
		// TODO preuzmi aerodrome iz tablice AERODROMI_PRACENI
		List<Aerodrom> aerodromi = new ArrayList<>();
		Aerodrom ad = new Aerodrom("LDZA", "Airport Zagreb", "HR", new Lokacija("45.743056", "16.068889"));
		aerodromi.add(ad);
		ad = new Aerodrom("LDVA", "Airport Varaždin", "HR", new Lokacija("46.2946472", "16.3829327"));
		aerodromi.add(ad);
		ad = new Aerodrom("EDDF", "Airport Frankfurt", "DE", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("EDDB", "Airport Berlin", "DE", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("LOWW", "Airport Vienna", "AT", new Lokacija("48.1102982", "16.5697002"));
		aerodromi.add(ad);

		Aerodrom aerodrom = null;
		for(Aerodrom a: aerodromi) {
			if(a.getIcao().compareTo(icao) == 0) {
				aerodrom = a;
			}
		}

		Lokacija lokacija = aerodrom.getLokacija();
		
		OWMKlijent owmKlijent = new OWMKlijent(apiKey);
		MeteoPodaci meteoPodaci = null;
		
		try {
			meteoPodaci = owmKlijent.getRealTimeWeather(lokacija.getLatitude(), lokacija.getLongitude());
		} catch (NwtisRestIznimka e) {
			e.printStackTrace();
		}
		
		return meteoPodaci;
	}
	
	

// TODO maknuti komentar nakon što se dodaju 3_lib_03_1 i 3_lib_06_1
//	public PostavkeBazaPodataka dajPBP() {
//		ServletContext context = (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		PostavkeBazaPodatak pbp = (PostavkeBazaPodataka) context.getAttribute("Postavke");
//		return pbp;
//	}
}
