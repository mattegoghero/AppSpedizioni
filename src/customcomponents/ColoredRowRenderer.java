package customcomponents;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import tablemodels.ColoredRowTableModel;

/**
 * Implementazione di {@link DefaultTableCellRender} che colora la righe di una tabella in base al colore fornito da un {@link ColoredRowTableModel}
 * @author Matteo Federico Goghero - 143143
 */
public class ColoredRowRenderer extends DefaultTableCellRenderer {
	/**
	 * Il costruttore applica un allineamento orizzontale al testo contenuto nella cella.
	 */
	public ColoredRowRenderer(){
		super();
		this.setHorizontalAlignment(CENTER);
	}
	
	/**
	 * return {@link Component} il cui stile dipende da:
	 * <ul>
	 * 	<li> Il colore ottenuto per la riga corrente </li>
	 * 	<li> La selezione di riga corrente nella tabella </li>
	 * </ul>
	 */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	ColoredRowTableModel model = (ColoredRowTableModel) table.getModel();
    	int columnCount = model.getColumnCount();
        JLabel cellComponent = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color color = model.getRowColor(row);
        
        if (isSelected) {
        	cellComponent.setBorder(BorderFactory.createMatteBorder(
    			2, //top
    			column == 0 ? 2 : 0, //left - solo se è la prima colonna
    			2, //bottom
    			column == columnCount - 1 ? 2 : 0, //right - solo se è l'ultima colonna 
    			Color.black
        	));
        }
               
        cellComponent.setBackground(color);
        return cellComponent;
    }

}