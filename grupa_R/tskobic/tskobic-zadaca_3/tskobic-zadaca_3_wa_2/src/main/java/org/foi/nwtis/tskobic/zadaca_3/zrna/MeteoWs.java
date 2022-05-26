package org.foi.nwtis.tskobic.zadaca_3.zrna;

import org.foi.nwtis.tskobic.ws.meteo.Meteo;
import org.foi.nwtis.tskobic.ws.meteo.MeteoPodaci;
import org.foi.nwtis.tskobic.ws.meteo.WsMeteo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.ws.WebServiceRef;

/**
 * Klasa MeteoWs.
 */
@RequestScoped
@Named("meteoWs")
public class MeteoWs {

	/** Meteo servis. */
	@WebServiceRef(wsdlLocation = "http://localhost:9090/tskobic-zadaca_3_wa_1/meteo?wsdl")
	private Meteo service;

	/** Meteo podaci. */
	private MeteoPodaci meteoPodaci = new MeteoPodaci();
	
	/** icao aerodroma. */
	private String icao;
	
	/** ispis. */
	private String ispis;

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
	 * @param icao icao aerodroma
	 */
	public void setIcao(String icao) {
		this.icao = icao;
		AerodromiWs.icao = icao;
	}

	/**
	 * Vraća ispis.
	 *
	 * @return the ispis
	 */
	public String getIspis() {
		if (meteoPodaci != null) {
			this.ispis = "Temperatura: " + meteoPodaci.getTemperatureValue() + " " + meteoPodaci.getTemperatureUnit()
					+ "; Vlaga: " + meteoPodaci.getHumidityValue() + " " + meteoPodaci.getHumidityUnit() + "; Tlak: "
					+ meteoPodaci.getPressureValue() + " " + meteoPodaci.getPressureUnit() + "; Oborine: "
					+ meteoPodaci.getPrecipitationValue() + " " + meteoPodaci.getPrecipitationUnit() + "; Vjetar: "
					+ meteoPodaci.getWindDirectionName() + meteoPodaci.getWindSpeedValue() + " "
					+ meteoPodaci.getWindSpeedName();
			this.ispis = this.ispis.replace("null", "");
		}
		return this.ispis;
	}

	/**
	 * Postavlja ispis.
	 *
	 * @param ispis the new ispis
	 */
	public void setIspis(String ispis) {
		this.ispis = ispis;
	}

	/**
	 * Vraća meteo podatke.
	 *
	 * @return the meteo podaci
	 */
	public MeteoPodaci getMeteoPodaci() {
		this.meteoPodaci = this.dajMeteo();
		return this.meteoPodaci;
	}

	/**
	 * Postavlja meteo podaci.
	 *
	 * @param meteoPodaci meteo podaci
	 */
	public void setMeteoPodaci(MeteoPodaci meteoPodaci) {
		this.meteoPodaci = meteoPodaci;
	}

	/**
	 * Daje meteo podatke sa web servisa.
	 *
	 * @return the meteo podaci
	 */
	public MeteoPodaci dajMeteo() {
		service = new Meteo();

		if (icao != null) {
			WsMeteo wsMeteo = service.getWsMeteoPort();
			MeteoPodaci lMeteoPodaci = wsMeteo.dajMeteo(icao);

			return lMeteoPodaci;
		}

		return null;
	}

}
