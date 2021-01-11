package tablemodels;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import data.Spedizione;
import data.SpedizioneAssicurata;

/**
 * Implementazione del modello astratto {@link ColoredRowTableModel} che permette di visualizzare le spedizioni in formato tabellare.
 * @author Matteo Federico Goghero - 143143
 *
 */
public class SpedizioniTableModel extends ColoredRowTableModel {
	
	protected Vector<Spedizione> mData;
	
	protected ArrayList<String> columnNames = new ArrayList<String>(Arrays.asList("Codice", "Destinazione", "Peso", "Data", "Valore assicurato", "Stato"));
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setData(Vector<Spedizione> mData) {
		this.mData = mData;
	}
	
	public Vector<Spedizione> getData() {
		return mData;
	}
		
	@Override
	public int getRowCount() {
		return mData != null ? mData.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	@Override
	public Color getRowColor(int rowIndex) {
		return mData.get(rowIndex).getStato().getColor();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Spedizione sped = mData.get(rowIndex);
		switch(columnIndex) {
			case 0:
				return sped.getCodice();
			case 1:
				return sped.getDestinazione();
			case 2:
				return String.format("%1$.3f", sped.getPeso()) + " Kg";
			case 3:
				return dateFormat.format(sped.getData());
			case 4:
				if (sped instanceof SpedizioneAssicurata) {
					double valore =  ((SpedizioneAssicurata)sped).getValoreAssicurato();
					return String.format("%1$.2f", valore) + " €";
				}
				break;
			case 5:
				return sped.getStato().getName();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}
	
}
