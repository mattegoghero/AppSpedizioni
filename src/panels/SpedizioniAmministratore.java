package panels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import data.Spedizione;
import data.StatoSpedizione;
import tablemodels.SpedizioniAmministratoreTableModel;
import threads.UpdateSpedizioniThread;

/**
 * Implementazione della pannello astratto {@link Spedizioni} che visualizza tutte le spedizioni di tutti i clienti.
 * @author Matteo Federico Goghero - 143143
 *
 */
public class SpedizioniAmministratore extends Spedizioni implements UpdateSpedizioniThread.UpdateSpedizioniThreadListener {

	JPopupMenu popupMenu;
	
	UpdateSpedizioniThread updateThread;
	
	public SpedizioniAmministratore(){
		super(new SpedizioniAmministratoreTableModel());
		popupMenu = new JPopupMenu();
		JMenuItem menuItem_cancella = new JMenuItem("Cancella");
		popupMenu.add(menuItem_cancella);
		
		addComponentListener (new ComponentAdapter () {
	        @Override
			public void componentShown ( ComponentEvent e ){
	            updateTableData();
	            startUpdateThread();
	        }

	        @Override
			public void componentHidden ( ComponentEvent e ){
	        	stopUpdateThread();
	        }
	    });
		
		menuItem_cancella.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelectedSpedizione();
			}
		});
	}
	
	/**
	 * Metodo che avvia il {@link #updateThread}.
	 */
	private void startUpdateThread() {
		updateThread = new UpdateSpedizioniThread();
        updateThread.setListener(SpedizioniAmministratore.this);
        updateThread.start();
	}
	
	/**
	 * Metodo che comunica al {@link #updateThread} di terminare la sua esecuzione.
	 */
	private void stopUpdateThread() {
		if (updateThread != null)
    		updateThread.terminate();
	}
	
	/**
	 * Metodo che si occupa di eliminare la spedizione correntemente selezionata nella tabella.
	 */
	private void deleteSelectedSpedizione() {
		Spedizione sped = model.getData().get(table_spedizioni.getSelectedRow());	
		daoSpedizione.deleteSpedizione(sped.getCodice());
		updateTableData();
	}
	
	/**
	 * Metodo che si occupa di aggiornare le spedizioni presenti nella tabella.
	 */
	private void updateTableData() {
		model.setData(daoSpedizione.getAllSpedizioni());
		model.fireTableDataChanged();
	}
	
	/**
	 * L'implementazione del metodo astratto permette di aprire il menu contestuale se si è effettutato un click
	 * con il tasto destro su una spedizione che si trova in uno stato finale.
	 */
	@Override
	protected void onTableClick(int row, int column, MouseEvent evt) {
		if (SwingUtilities.isRightMouseButton(evt)) {
        	table_spedizioni.setRowSelectionInterval(row, row);
            Spedizione sped = model.getData().get(row);
            StatoSpedizione stato = sped.getStato();
            if (stato == StatoSpedizione.RICEVUTA || stato == StatoSpedizione.RIMBORSO_EROGATO) {
            	popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }	
	}

	/**
	 * Implementazione di {@link UpdateSpedizioniThread.UpdateSpedizioniThreadListener} che aggiorna i dati della tabella delle spedizioni
	 * al verificarsi dell'aggiornamento di una spedizione da parte del {@link #updateThread}
	 */
	@Override
	public void on_spedizioneUpdated() {
		updateTableData();		
	}
}
