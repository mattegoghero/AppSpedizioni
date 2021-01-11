package tablemodels;

/**
 * Implementazione del modello {@link SpedizioniTableModel} che aggiunge la colonna per l'username del cliente a cui appartiene la spedizione.
 * @author Matteo Federico Goghero - 143143
 */
public class SpedizioniAmministratoreTableModel extends SpedizioniTableModel{
		
	public SpedizioniAmministratoreTableModel() {
		super();
		this.columnNames.add("Cliente");
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == columnNames.size() - 1)
			return mData.get(rowIndex).getCodiceCliente();
		else
			return super.getValueAt(rowIndex, columnIndex);
	}
	
}
