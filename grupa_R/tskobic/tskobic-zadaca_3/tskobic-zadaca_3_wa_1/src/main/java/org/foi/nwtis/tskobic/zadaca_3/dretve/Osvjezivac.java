package org.foi.nwtis.tskobic.zadaca_3.dretve;

import java.util.Date;

import org.foi.nwtis.tskobic.zadaca_3.wsock.Info;

import jakarta.inject.Inject;

public class Osvjezivac extends Thread {

	boolean kraj = false;
	int vrijemeSpavanja = 0;
	
	@Inject Info info;
	
	@Override
	public synchronized void start() {
		// TODO preuzeti iz konfiguracijskih podataka
		vrijemeSpavanja = 20000;

		super.start();
	}

	@Override
	public void run() {
		while(! kraj) {
			String vrijeme = new Date().toString();
			int brojAerodroma = 7;
			
			info.posaljiPoruku(vrijeme + ", " + brojAerodroma);
			
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
