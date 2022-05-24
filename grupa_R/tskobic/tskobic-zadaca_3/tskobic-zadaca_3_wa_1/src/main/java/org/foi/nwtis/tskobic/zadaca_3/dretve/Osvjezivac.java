package org.foi.nwtis.tskobic.zadaca_3.dretve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.podaci.AerodromiPraceniDAO;
import org.foi.nwtis.tskobic.zadaca_3.wsock.Info;

import jakarta.servlet.ServletContext;

public class Osvjezivac extends Thread {

	boolean kraj = false;
	int vrijemeSpavanja = 0;
	ServletContext context;
	public static PostavkeBazaPodataka konfig;
	AerodromiPraceniDAO aerodromiPraceniDAO;

	public Osvjezivac(ServletContext context) {
		this.context = context;
		Osvjezivac.konfig = (PostavkeBazaPodataka) context.getAttribute("Postavke");
		this.aerodromiPraceniDAO = new AerodromiPraceniDAO();
	}

	@Override
	public synchronized void start() {
		vrijemeSpavanja = Integer.parseInt(konfig.dajPostavku("ciklus.spavanje")) * 1000;

		super.start();
	}

	@Override
	public void run() {
		while (!kraj) {
			String vrijeme = trenutnoVrijeme("dd.MM.yyyy HH:mm:ss");
			List<Aerodrom> praceniAerodromi = aerodromiPraceniDAO.dohvatiPraceneAerodrome(konfig);
			int brojAerodroma = praceniAerodromi.size();

			Info.posaljiPoruku(vrijeme + ", " + brojAerodroma);

			try {
				Thread.sleep(vrijemeSpavanja);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interrupt() {
		kraj = true;
		super.interrupt();
	}

	
	public String trenutnoVrijeme(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String datum = sdf.format(new Date());

		return datum;
	}
}
