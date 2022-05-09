package org.foi.nwtis.tskobic.zadaca_3.ws;

import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.Lokacija;

import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;

@WebService(serviceName = "aerodromi")
public class WsAerodromi {
	@Resource
	private WebServiceContext wsContext;

	@WebMethod
	public List<Aerodrom> dajSveAerodrome() {
		// TODO preuzimi aerodrome iz bablice AERODROMI_PRACENI
		List<Aerodrom> aerodromi = new ArrayList<>();
		Aerodrom ad = new Aerodrom("LDZA", "Airport Zagreb", "HR", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("LDVA", "Airport Varaždin", "HR", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("EDDF", "Airport Frankfurt", "DE", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("EDDB", "Airport Berlin", "DE", new Lokacija("0", "0"));
		aerodromi.add(ad);
		ad = new Aerodrom("LOWW", "Airport Vienna", "AT", new Lokacija("0", "0"));
		aerodromi.add(ad);

		return aerodromi;
	}
	
	
	/*
	 * TODO maknuti komentar nako što se dodaju 3_lib_03_1 i 3_lib_06_1 public
	 * PostavkeBazaPodataka dajPBP () { ServletContext context = (ServletContext)
	 * wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
	 * PostavkeBazaPodataka pbp = (PostavkeBazaPodataka)
	 * context.getAttribute("Postavke"); return pbp }
	 */

}
