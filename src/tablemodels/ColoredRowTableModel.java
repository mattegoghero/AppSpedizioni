package tablemodels;
import java.awt.Color;

import javax.swing.table.AbstractTableModel;

/**
 * Modello astratto che definisce le caratteristiche necessarie per ottenere un colore per ciascuna riga di una tabella.
 * @author Matteo Federico Goghero - 143143
 */
abstract public class ColoredRowTableModel extends AbstractTableModel {
	/**
	 * @param rowIndex Indice di riga della tabella
	 * @return Il {@link Color Colore} della riga indicata.
	 */
	abstract public Color getRowColor(int rowIndex);
}
