package org.foi.nwtis.tskobic.zadaca_3.dretve;

import java.util.Date;

import org.foi.nwtis.tskobic.vjezba_06.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.tskobic.zadaca_3.wsock.Info;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

public class Osvjezivac extends Thread {

	boolean kraj = false;
	int vrijemeSpavanja = 0;
	ServletContext context;
	PostavkeBazaPodataka konfig;
	
	@Inject
	static Info info;

	public Osvjezivac(ServletContext context) {
		this.context = context;
		this.konfig = (PostavkeBazaPodataka) context.getAttribute("Postavke");
	}

	@Override
	public synchronized void start() {
		vrijemeSpavanja = Integer.parseInt(konfig.dajPostavku("ciklus.spavanje")) * 1000;

		super.start();
	}

	@Override
	public void run() {
		while (!kraj) {
			String vrijeme = new Date().toString();
			int brojAerodroma = 7;

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

}
