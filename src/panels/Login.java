package panels;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import customcomponents.CustomFont;
import customcomponents.CustomPanel;
import dao.DaoCliente;
import data.Cliente;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

/**
 * Pannello che si occupa di visualizzare la schermata di login per i clienti e l'amministratore.
 * Da questa schermata è possibile inviare anche una richiesta di registrazione di un nuovo cliente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */

public class Login extends CustomPanel {
	private JTextField txt_username;
	private JTextField txt_password;
	private LoginListener listener;

	private String usernameAdmin = "admin";
	private String passwordAdmin = "admin";
	
	public Login() {
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
		setLayout(new BorderLayout(16, 16));
		
		JLabel lbl_login = new JLabel("Login");
		lbl_login.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		lbl_login.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login.setFont(new CustomFont(30));
		add(lbl_login, BorderLayout.NORTH);
		
		Insets lbl_Insets = new Insets(16, 8, 16, 8);
		Insets txt_insets = new Insets(8, 8, 8, 8);
		
		JPanel pnl_input = new JPanel();
		add(pnl_input, BorderLayout.CENTER);
		
		GridBagLayout gbl_pnl_input = new GridBagLayout();
		gbl_pnl_input.columnWidths = new int[] {150, 250, 150};
		gbl_pnl_input.rowHeights = new int[] {30, 30};
		pnl_input.setLayout(gbl_pnl_input);
		
		JLabel lbl_username = new JLabel("Username");
		GridBagConstraints gbc_lbl_username = new GridBagConstraints();
		gbc_lbl_username.anchor = GridBagConstraints.EAST;
		gbc_lbl_username.gridx = 0;
		gbc_lbl_username.gridy = 0;
		gbc_lbl_username.insets = lbl_Insets;
		
		lbl_username.setFont(new CustomFont(15));
		pnl_input.add(lbl_username, gbc_lbl_username);
		
		txt_username = new JTextField();
		GridBagConstraints gbc_txt_username = new GridBagConstraints();
		gbc_txt_username.fill = GridBagConstraints.BOTH;
		gbc_txt_username.gridx = 1;
		gbc_txt_username.gridy = 0;
		gbc_txt_username.insets = txt_insets;

		txt_username.setColumns(10);
		pnl_input.add(txt_username, gbc_txt_username);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		gbc_lblPassword.insets = lbl_Insets;
		
		lblPassword.setFont(new CustomFont(15));
		pnl_input.add(lblPassword, gbc_lblPassword);
		
		txt_password = new JPasswordField();
		GridBagConstraints gbc_txt_password = new GridBagConstraints();
		gbc_txt_password.fill = GridBagConstraints.BOTH;
		gbc_txt_password.gridx = 1;
		gbc_txt_password.gridy = 1;
		gbc_txt_password.insets = txt_insets;
		
		pnl_input.add(txt_password, gbc_txt_password);
		txt_password.setColumns(10);
		
		JPanel pnl_bottoni = new JPanel();
		pnl_bottoni.setBorder(new EmptyBorder(0, 0, 30, 0));

		pnl_bottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		add(pnl_bottoni, BorderLayout.SOUTH);
		
		Dimension buttonDimension = new Dimension(150, 40);
		
		JButton btn_accediUtente = new JButton("Accedi utente");
		btn_accediUtente.setFont(new CustomFont(12));
		btn_accediUtente.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_accediUtente);
		
		JButton btn_accediAmministratore = new JButton("Accedi amministratore");
		btn_accediAmministratore.setFont(new CustomFont(12));
		btn_accediAmministratore.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_accediAmministratore);
		
		JButton btn_registrati = new JButton("Registrati");
		btn_registrati.setFont(new CustomFont(12));
		btn_registrati.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_registrati);
		
		

		btn_registrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.on_registrazioneRichiesta();
				}
			}
		});
		
		btn_accediAmministratore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginAmministratore();			
			}
		});
		
		btn_accediUtente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCliente();
			}
		});
	}
	
	/**
	 * Metodo che tenta di effettuare il login di un cliente usando username e password indicati nei campi di testo.
	 * Se il login ha successo notifica il {@link #listener} con il cliente autenticato.
	 */
	private void loginCliente() {
		if (checkFields()) {
			Cliente c = DaoCliente.getInstance().getCliente(txt_username.getText());
			if (c != null && c.getPassword().contentEquals(txt_password.getText())) {
				if (listener != null) {
					listener.on_loginCliente(c);
				}
			} else {
				showBadLoginAlert();
			}
		}
	}
	
	/**
	 * Metodo che tenta di effettuare il login dell'amministratire usando username e password indicati nei campi di testo.
	 * Se il login ha successo notifica il {@link #listener}.
	 */
	private void loginAmministratore() {
		if (checkFields()) {
			if (usernameAdmin.contentEquals(txt_username.getText()) && passwordAdmin.contentEquals(txt_password.getText())) {
				if (listener != null) {
					listener.on_loginAmministratore();
				}
			} else {
				showBadLoginAlert();
			}
		}
	}
	
	/**
	 * Metodo per visualizzare un {@link Dialog} di warning che indica che il login è fallito.
	 */
	private void showBadLoginAlert() {
		showWarningDialog("Nome utente o password errati");
	}
	
	/**
	 * Metodo per controllare che i campi da compilare abbiano valori corretti. 
	 * Nel caso i dati inseriti non siano corretti il metodo visualizzerà un {@link Dialog} di avviso.
	 * Non controlla l'effettiva corrispondenza di username e password dell'utente.
	 * @return <div><code>true</code> se i dati inseriti sono corretti <br/><code>false</code> altrimenti </div>
	 */
	private boolean checkFields() {
		if (txt_username.getText().isBlank()) {
			showWarningDialog("L'username non può essere vuoto");
			return false;
		} else if (txt_password.getText().isBlank()) {
			showWarningDialog("La password non può essere vuota");
			return false;
		}
		return true;
	}
	
	public void setListener(LoginListener listener) {
		this.listener = listener;
	}

	/**
	 * Metodo che resetta i campi da compilare con valori di default.
	 */
	private void clearFields() {
		txt_username.setText("");
		txt_password.setText("");
	}
	
	/**
	 * Interfaccia per comunicare con il {@JFrame} che ospita il pannello.
	 * @author Matteo Federico Goghero - 143143
	 */
	public interface LoginListener {
		
		/**
		 * Metodo chiamato quando è richiesta la registrazione di un nuovo cliente.
		 */
		void on_registrazioneRichiesta();
		
		/**
		 * Metodo chiamato per comunicare il login di un cliente.
		 * @param cliente Il cliente che si è autenticato.
		 */
		void on_loginCliente(Cliente cliente);
		
		/**
		 * Metodo chiamato per comunicare il loogin dell'amministratore.
		 */
		void on_loginAmministratore();
	}
}
