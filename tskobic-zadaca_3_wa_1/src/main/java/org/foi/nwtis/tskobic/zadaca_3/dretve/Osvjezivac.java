package org.foi.nwtis.tskobic.zadaca_3.dretve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiPraceniDAO;
import org.foi.nwtis.tskobic.zadaca_3.wsock.Info;

import jakarta.servlet.ServletContext;

/**
 * Dretva Osvjezivac koja radi u ciklusima i obavještava korisnike o trenutnom vremenu i broju praćenih aerodroma.
 */
public class Osvjezivac extends Thread {

	/** Provjera kraja rada dretve. */
	boolean kraj = false;
	
	/** Vrijeme spavanja. */
	int vrijemeSpavanja = 0;
	
	/** Kontekst servleta. */
	ServletContext context;
	
	/** Postavke baze podataka. */
	public static PostavkeBazaPodataka konfig;
	
	/** Aerodromi praćeni DAO. */
	AerodromiPraceniDAO aerodromiPraceniDAO;

	/**
	 * Konstruktor dretve.
	 *
	 * @param context kontekst servleta
	 */
	public Osvjezivac(ServletContext context) {
		this.context = context;
		Osvjezivac.konfig = (PostavkeBazaPodataka) context.getAttribute("Postavke");
		this.aerodromiPraceniDAO = new AerodromiPraceniDAO();
	}

	/**
	 * Metoda za pokretanje dretve.
	 */
	@Override
	public synchronized void start() {
		vrijemeSpavanja = Integer.parseInt(konfig.dajPostavku("ciklus.spavanje")) * 1000;

		super.start();
	}

	/**
	 * Glavna metoda za rad dretve.
	 */
	@Override
	public void run() {
		while (!kraj) {
			String vrijeme = trenutnoVrijeme("dd.MM.yyyy HH:mm:ss");
			List<Aerodrom> praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);
			int brojAerodroma = praceniAerodromi.size();

			Info.informiraj(vrijeme + ", " + brojAerodroma);

			try {
				Thread.sleep(vrijemeSpavanja);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metoda za prekidanje rada dretve.
	 */
	@Override
	public void interrupt() {
		kraj = true;
		super.interrupt();
	}

	
	/**
	 * Metoda za prikaz trenutnog vremena u proslijeđenom formatu.
	 *
	 * @param format format datuma
	 * @return the string
	 */
	public String trenutnoVrijeme(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String datum = sdf.format(new Date());

		return datum;
	}
}
