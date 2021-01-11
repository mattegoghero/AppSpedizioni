package panels;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import customcomponents.CustomFont;
import customcomponents.CustomPanel;
import dao.DaoCliente;
import data.Cliente;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
/**
 * Pannello che si occupa di registrare un nuovo cliente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */
public class Registrazione extends CustomPanel {
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JPasswordField txt_confermaPassword;
	private JTextField txt_indirizzo;
	
	private RegistrazioneListener listener;

	public Registrazione() {
		setSize(new Dimension(1080, 720));
		
		addComponentListener (new ComponentAdapter () {
	        @Override
			public void componentShown ( ComponentEvent e ){
	            clearFields();
	        }

	        @Override
			public void componentHidden ( ComponentEvent e ){
	        	clearFields();
	        }
	    });
		setLayout(new BorderLayout(0, 0));
		
		Insets lbl_insets = new Insets(16, 8, 16, 8);
		Insets txt_insets = new Insets(8, 8, 8, 8);
		Dimension buttonDimension = new Dimension(150, 40);
		
		JPanel pnl_input = new JPanel();
		add(pnl_input);
		GridBagLayout gbl_pnl_input = new GridBagLayout();
		gbl_pnl_input.columnWidths = new int[] {150, 250, 150};
		gbl_pnl_input.rowHeights = new int[] {30, 30, 30, 30};
		pnl_input.setLayout(gbl_pnl_input);
		
		JLabel lbl_username = new JLabel("Username");
		GridBagConstraints gbc_lbl_username = new GridBagConstraints();
		gbc_lbl_username.anchor = GridBagConstraints.EAST;
		gbc_lbl_username.gridx = 0;
		gbc_lbl_username.gridy = 0;
		gbc_lbl_username.insets = lbl_insets;
		pnl_input.add(lbl_username, gbc_lbl_username);
		lbl_username.setFont(new CustomFont(15));
		
		txt_username = new JTextField();
		GridBagConstraints gbc_txt_username = new GridBagConstraints();
		gbc_txt_username.fill = GridBagConstraints.BOTH;
		gbc_txt_username.gridx = 1;
		gbc_txt_username.gridy = 0;
		gbc_txt_username.insets = txt_insets;

		pnl_input.add(txt_username, gbc_txt_username);
		txt_username.setColumns(10);
		
		JLabel lbl_password = new JLabel("Password");
		GridBagConstraints gbc_lbl_password = new GridBagConstraints();
		gbc_lbl_password.anchor = GridBagConstraints.EAST;
		gbc_lbl_password.gridx = 0;
		gbc_lbl_password.gridy = 1;
		gbc_lbl_password.insets = lbl_insets;
		pnl_input.add(lbl_password, gbc_lbl_password);
		lbl_password.setFont(new CustomFont(15));
		
		txt_password = new JPasswordField();
		GridBagConstraints gbc_txt_password = new GridBagConstraints();
		gbc_txt_password.fill = GridBagConstraints.BOTH;
		gbc_txt_password.gridx = 1;
		gbc_txt_password.gridy = 1;
		gbc_txt_password.insets = txt_insets;

		pnl_input.add(txt_password, gbc_txt_password);
		txt_password.setColumns(10);
		
		JLabel lbl_confermaPassword = new JLabel("Conferma password");
		GridBagConstraints gbc_lbl_confermaPassword = new GridBagConstraints();
		gbc_lbl_confermaPassword.anchor = GridBagConstraints.EAST;
		gbc_lbl_confermaPassword.gridx = 0;
		gbc_lbl_confermaPassword.gridy = 2;
		gbc_lbl_confermaPassword.insets = lbl_insets;

		pnl_input.add(lbl_confermaPassword, gbc_lbl_confermaPassword);
		lbl_confermaPassword.setFont(new CustomFont(15));
		
		txt_confermaPassword = new JPasswordField();
		GridBagConstraints gbc_txt_confermaPassword = new GridBagConstraints();
		gbc_txt_confermaPassword.fill = GridBagConstraints.BOTH;
		gbc_txt_confermaPassword.gridx = 1;
		gbc_txt_confermaPassword.gridy = 2;
		gbc_txt_confermaPassword.insets = txt_insets;

		pnl_input.add(txt_confermaPassword, gbc_txt_confermaPassword);
		txt_confermaPassword.setColumns(10);
		
		JLabel lbl_indirizzo = new JLabel("Indirizzo");
		GridBagConstraints gbc_lbl_indirizzo = new GridBagConstraints();
		gbc_lbl_indirizzo.anchor = GridBagConstraints.EAST;
		gbc_lbl_indirizzo.gridx = 0;
		gbc_lbl_indirizzo.gridy = 3;
		gbc_lbl_indirizzo.insets = lbl_insets;

		pnl_input.add(lbl_indirizzo, gbc_lbl_indirizzo);
		lbl_indirizzo.setFont(new CustomFont(15));
		
		txt_indirizzo = new JTextField();
		GridBagConstraints gbc_txt_indirizzo = new GridBagConstraints();
		gbc_txt_indirizzo.fill = GridBagConstraints.BOTH;
		gbc_txt_indirizzo.gridx = 1;
		gbc_txt_indirizzo.gridy = 3;
		gbc_txt_indirizzo.insets = txt_insets;

		pnl_input.add(txt_indirizzo, gbc_txt_indirizzo);
		txt_indirizzo.setColumns(10);
		
		JPanel pnl_bottoni = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnl_bottoni.getLayout();
		flowLayout.setHgap(20);
		pnl_bottoni.setBorder(new EmptyBorder(0, 0, 30, 0));
		add(pnl_bottoni, BorderLayout.SOUTH);
		
		JButton btn_conferma = new JButton("Conferma");
		
		btn_conferma.setPreferredSize(buttonDimension);
		btn_conferma.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnl_bottoni.add(btn_conferma);
		
		JButton btn_annulla = new JButton("Annulla");
		btn_annulla.setPreferredSize(buttonDimension);
		btn_annulla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnl_bottoni.add(btn_annulla);
		
		JLabel lbl_registrazione = new JLabel("Registrazione cliente");
		lbl_registrazione.setBorder(new EmptyBorder(30, 0, 0, 0));
		lbl_registrazione.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_registrazione.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(lbl_registrazione, BorderLayout.NORTH);
		
		btn_annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.on_exit();
				}
			}
		});
		
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registraCliente();
			}
		});
		
	}
	
	/**
	 * Metodo che si occupa di registrare un nuovo cliente a partire dai valori indicati nei campi di testo corrispondenti.
	 */
	private void registraCliente() {
		if (checkFields()) {
			Cliente c = new Cliente(txt_username.getText(), new String(txt_password.getPassword()), txt_indirizzo.getText());
			if (DaoCliente.getInstance().insertCliente(c)) {
				showDialog("Registrazione avvenuta con successo", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
				if (listener != null) {
					listener.on_exit();
				}
			} else {
				showWarningDialog("Username già utilizzato");
			}
		}
	}
	
	/**
	 * Metodo che resetta i campi da compilare con valori di default.
	 */
	private void clearFields() {
		txt_username.setText("");
		txt_password.setText("");
		txt_confermaPassword.setText("");
		txt_indirizzo.setText("");
	}
	
	/**
	 * Metodo che si occupa di controllare la correttezza dei valori inseriti nei vari campi presenti nel pannello.
	 * @return div><code>true</code> se i dati inseriti sono corretti <br/><code>false</code> altrimenti </div>
	 */
	public boolean checkFields() {
		String password = new String(txt_password.getPassword());
		String confirmPassword = new String(txt_confermaPassword.getPassword());
		
		if (txt_username.getText().isBlank()) {
			showWarningDialog("L'username non può essere vuoto");
			return false;
		} else if (password.isBlank()) {
			showWarningDialog("La password non può essere vuota");
			return false;
		} else if (!password.contentEquals(confirmPassword)) {
			showWarningDialog("Le password non coindono");
			return false;
		} else if (txt_indirizzo.getText().isBlank()) {
			showWarningDialog("L'indirizzo non può essere vuoto");
			return false;
		}
		return true;
	}
	
	
	
	public void setListener(RegistrazioneListener listener) {
		this.listener = listener;
	}

	/**
	 * Interfaccia per comunicare con il {@JFrame} che ospita il pannello.
	 * @author Matteo Federico Goghero - 143143
	 */
	public interface RegistrazioneListener {
		/**
		 * Metodo che viene chiamato per comunicare il termine dell'operazione di registrazione di un nuovo cliente (sia che abbia avuto successo o sia stata annullata).
		 */
		void on_exit();
	}
}
