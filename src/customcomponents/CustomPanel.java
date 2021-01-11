package customcomponents;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * Pannello personalizzato che mette a disposizione due metodi per visualizzare dei {@link Dialog} di avviso
 * @author Matteo Federico Goghero - 143143
 */
public class CustomPanel extends JPanel {
	
	/**
	 * Metodo che mostra un dialog di avviso.
	 * @param message Il messaggio del dialog.
	 * @param title Il titolo del dialog.
	 * @param severity Il grado di severità del dialog.
	 * 
	 * @see JOptionPane
	 */
	protected void showDialog(String message, String title, int severity) {
		JOptionPane.showMessageDialog(
				SwingUtilities.getWindowAncestor(this),
				message,
			    "Attenzione",
			    severity
		);
	}
	
	/**
	 * Metodo che mostra un dialog di warning con titolo e severità preimpostati.
	 * @param message Il messaggio del dialog.
	 * 
	 * @see JOptionPane
	 * @see #showDialog(String, String, int)
	 */
	protected void showWarningDialog(String message) {
		showDialog(message, "Attenzione", JOptionPane.WARNING_MESSAGE);
	}
}
