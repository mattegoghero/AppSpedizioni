package threads;
import java.util.Vector;

import javax.swing.SwingUtilities;

import dao.DaoSpedizione;
import data.Spedizione;
import data.StatoSpedizione;

/**
 * {@link Thread} che si occupa di aggiornare periodicamente lo stato di una spedizione selezionata casualmente.
 * Non viene fatta alcuna decisione preliminare su quale tipo di spedizione selezionare casualmente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */
public class UpdateSpedizioniThread extends Thread {

	private DaoSpedizione daoSpedizione = DaoSpedizione.getInstance();
	
	private boolean run;
	
	private UpdateSpedizioniThreadListener listener;
	
	@Override
	public void run() {
		run = true;
		sleep();
		while (run) {
			Vector <Spedizione> spedizioni = daoSpedizione.getAllSpedizioni();
			int index = (int) Math.round(Math.random() * (spedizioni.size() - 1));
			if (index >= 0 && index < spedizioni.size()) {		
				System.out.println("Spedizione "+ index);
				Spedizione sped = spedizioni.get(index);
				synchronized(daoSpedizione) {			
					if (sped.getStato() != StatoSpedizione.RICEVUTA && sped.getStato() != StatoSpedizione.RIMBORSO_EROGATO && sped.getStato() != StatoSpedizione.FALLITA) {
						boolean fallita = Math.random() > 0.8;
						StatoSpedizione nuovoStato;
						if (fallita && sped.getStato() != StatoSpedizione.RIMBORSO_RICHIESTO) {
							nuovoStato = StatoSpedizione.FALLITA;
						} else {
							switch(sped.getStato()) {
							case IN_PREPARAZIONE:
								nuovoStato = StatoSpedizione.IN_TRANSITO;
								break;
							case IN_TRANSITO:
								nuovoStato = StatoSpedizione.RICEVUTA;
								break;
							case RIMBORSO_RICHIESTO:
								nuovoStato = StatoSpedizione.RIMBORSO_EROGATO;
								break;
							default:
								nuovoStato = null;							
							}
						}
						if (nuovoStato != null) {
							sped.setStato(nuovoStato);
							if (listener != null) {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										listener.on_spedizioneUpdated();										
									}
								});
							}
						}
					}
				}
				sleep();		
			}
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			run = false;
		}
	}
	
	public void terminate() {
		this.run = false;
	}
	
	public void setListener(UpdateSpedizioniThreadListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Interfaccia per comunicare quando lo stato di una spedizione è stato aggiornato.
	 * @author Matteo Federico Goghero - 143143
	 *
	 */
	public interface UpdateSpedizioniThreadListener {
		/**
		 * Metodo chiamato per comunicare quando lo stato di una spedizione è stato aggiornato.
		 */
		void on_spedizioneUpdated();
	}

}
