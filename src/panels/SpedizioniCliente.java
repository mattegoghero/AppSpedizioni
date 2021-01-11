package panels;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import customcomponents.CustomFont;
import data.Spedizione;
import data.SpedizioneAssicurata;
import data.StatoSpedizione;
import tablemodels.SpedizioniTableModel;
import javax.swing.SpringLayout;

/**
 * Implementazione della pannello astratto {@link Spedizioni} che visualizza tutte le spedizioni di un cliente indicato da {@link #username}.
 * Mette a disposizione anche un bottone per richiedere l'inserimento di una nuova spedizione.
 * @author Matteo Federico Goghero - 143143
 *
 */
public class SpedizioniCliente extends Spedizioni {
	private String username;
	private SpedizioniClienteListener listener;
	
	JPopupMenu popupMenu;
	
	public SpedizioniCliente(){
		super(new SpedizioniTableModel());

		addComponentListener (new ComponentAdapter () {
			@Override
			public void componentShown ( ComponentEvent e ){
				updateTableData();
			}
			
			@Override
			public void componentHidden ( ComponentEvent e ){}
		});
		
		JButton btn_nuovaSpedizione = new JButton("Nuova spedizione");
		btn_nuovaSpedizione.setFont(new CustomFont(12));
		btn_nuovaSpedizione.setPreferredSize(new Dimension(150, 40));
		pnl_bottoni.add(btn_nuovaSpedizione, 0);
		
		popupMenu = new JPopupMenu();
		JMenuItem menuItem_richiediRimborso = new JMenuItem("Richiedi rimborso");
		popupMenu.add(menuItem_richiediRimborso);
				
		btn_nuovaSpedizione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.on_nuovaSpedizione();
				}
			}
		});
		
		menuItem_richiediRimborso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refundSelectedSpedizione();
			}
		});
	}
	
	/**
	 * Metodo che si occupa di richiedere il rimborso di una spedizione assicurata fallita.
	 */
	private void refundSelectedSpedizione() {
		Spedizione sped = model.getData().get(table_spedizioni.getSelectedRow());	
		synchronized(daoSpedizione) {					
			sped.setStato(StatoSpedizione.RIMBORSO_RICHIESTO);
		}
		updateTableData();
	}
	
	/**
	 * L'implementazione del metodo astratto permette di aprire il menu contestuale se si è effettutato un click
	 * con il tasto destro su una spedizione assicurata fallita.
	 */
	@Override
	protected void onTableClick(int row, int column, MouseEvent evt) {
		if (SwingUtilities.isRightMouseButton(evt)) {
        	table_spedizioni.setRowSelectionInterval(row, row);
            Spedizione sped = model.getData().get(row);
            if (sped instanceof SpedizioneAssicurata && sped.getStato() == StatoSpedizione.FALLITA) {
            	popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }	
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setListener(SpedizioniClienteListener listener) {
		super.setListener(listener);
		this.listener = listener;
	}

	/**
	 * Metodo che si occupa di aggiornare le spedizioni presenti nella tabella.
	 */
	private void updateTableData() {
		if (username != null) {			
			model.setData(daoSpedizione.getSpedizioniForCliente(username));
			model.fireTableDataChanged();
		}
	}
	
	/**
	 * Interfaccia per comunicare con il {@JFrame} che ospita il pannello.
	 * @author Matteo Federico Goghero - 143143
	 */
	public interface SpedizioniClienteListener extends SpedizioniListener {
		/**
		 * Metodo che viene chiamato quando è necessario inserire una nuova spedizione per il cliente.
		 */
		void on_nuovaSpedizione();
	}
	
}
