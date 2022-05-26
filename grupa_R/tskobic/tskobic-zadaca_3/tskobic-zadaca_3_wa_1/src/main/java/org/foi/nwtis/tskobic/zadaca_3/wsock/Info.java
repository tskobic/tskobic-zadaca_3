package org.foi.nwtis.tskobic.zadaca_3.wsock;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.dretve.Osvjezivac;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiPraceniDAO;

import jakarta.websocket.CloseReason;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * Klasa Info koja predstavlja web socket krajnju točku.
 */
@ServerEndpoint("/info")
public class Info {
	
	/** Kolekcija sesija. */
	static Set<Session> sesije = new HashSet<>();
	
	/**
	 * Metoda koja svim korisnicima šalje proslijeđenu poruku.
	 *
	 * @param poruka poruka
	 */
	static public void informiraj(String poruka) {
		for(Session sesija: sesije) {
			if(sesija.isOpen()) {
				try {
					sesija.getBasicRemote().sendText(poruka);
				} catch (IOException e) {
					System.out.println("Pogreška kod slanja poruke za sesiju: " + sesija.getId());
				}
			}
		}
	}
	
	/**
	 * Metoda koja se izvršava pri otvaranju web socket sesije.
	 *
	 * @param sesija sesija
	 * @param konfig konfiguracija krajnje točke
	 */
	@OnOpen
	public void otvori(Session sesija, EndpointConfig konfig) {
		sesije.add(sesija);
		System.out.println("Otvorena veza: " + sesija.getId());
	}

	/**
	 * Metoda koja se izvršava pri zatvaranju web socekt sesije.
	 *
	 * @param sesija sesija
	 * @param razlog razlog zatvaranja sesije
	 */
	@OnClose
	public void zatvori(Session sesija, CloseReason razlog) {
		System.out.println("Zatvorena veza: " + sesija.getId() + " Razlog: " + razlog.getReasonPhrase());
		sesije.remove(sesija);
	}

	/**
	 * Metoda koja se izvršava pri primanju web poruke.
	 *
	 * @param sesija the sesija
	 * @param poruka the poruka
	 */
	@OnMessage
	public void stiglaPoruka(Session sesija, String poruka) {
		if(poruka.equals("info")) {
			PostavkeBazaPodataka konfig = Osvjezivac.konfig;
			AerodromiPraceniDAO aerodromiPraceniDAO = new AerodromiPraceniDAO();
			
			String vrijeme = trenutnoVrijeme("dd.MM.yyyy HH:mm:ss");
			List<Aerodrom> praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);
			int brojAerodroma = praceniAerodromi.size();
			
			informiraj(vrijeme + ", " + brojAerodroma);
		}
		System.out.println("Veza: " + sesija.getId() + " Poruka: " + poruka);
	}

	/**
	 * Metoda koja se izvršava kada dođe do pogreške.
	 *
	 * @param sesija sesija
	 * @param iznimka iznimka
	 */
	@OnError
	public void pogreska(Session sesija, Throwable iznimka) {
		System.out.println("Veza: " + sesija.getId() + " Pogreška: " + iznimka.getMessage());
	}
	
	/**
	 * Vraća trenutno vrijeme u proslijeđenom formatu.
	 *
	 * @param format format
	 * @return the string
	 */
	public String trenutnoVrijeme(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String datum = sdf.format(new Date());

		return datum;
	}
}
