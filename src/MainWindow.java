import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import dao.DaoCliente;
import dao.DaoSpedizione;
import data.Cliente;
import panels.Login;
import panels.NuovaSpedizione;
import panels.Registrazione;
import panels.SpedizioniAmministratore;
import panels.SpedizioniCliente;

/**
 * Classe principale d'avvio del programma per la gestione delle spedizioni.
 * Nel programma è presente una sola finestra (o {@link JFrame}) rappresentata dalla {@link MainWindow} che si occupa si sostituire dinamicamente
 * le varie schermate (o {@link CustomPanel}) presenti nel programma.
 * 
 * @author Matteo Fedrico Goghero - 143143
 */

public class MainWindow extends JFrame implements WindowListener {

	private final String LOGIN_PANEL = "LOGIN_PANEL";
	private final String REGISTRAZIONE_PANEL = "REGISTRAZIONE_PANEL";
	private final String SPEDIZIONI_CLIENTE_PANEL = "SPEDIZIONI_CLIENTE_PANEL";
	private final String SPEDIZIONI_AMMINISTRATORE_PANEL = "SPEDIZIONI_AMMINISTRATORE_PANEL";
	private final String NUOVA_SPEDIZIONE = "NUOVA_SPEDIZIONE";
	
	private Cliente cliente;
	private SpedizioniCliente spedizioniCliente;
	private NuovaSpedizione nuovaSpedizione;
	
	private Container contentPanel;

	public static void main(String[] args) {
		MainWindow frame = new MainWindow();
		frame.setVisible(true);
	}

	
	public MainWindow() {
		super("Gestore spedizioni");
		contentPanel = getContentPane();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800,600));
		setSize(1080,720);
		setLocationRelativeTo(null);
		
		contentPanel.setLayout(new CardLayout(0, 0));
		
		Login loginSelect = new Login();
		contentPanel.add(loginSelect, LOGIN_PANEL);
		loginSelect.setListener(loginListener);
		
		Registrazione registrazione = new Registrazione();
		contentPanel.add(registrazione, REGISTRAZIONE_PANEL);
		registrazione.setListener(registrazioneListener);
		
		spedizioniCliente = new SpedizioniCliente();
		spedizioniCliente.setListener(spedizioniListener);
		contentPanel.add(spedizioniCliente, SPEDIZIONI_CLIENTE_PANEL);
	
		SpedizioniAmministratore spedizioniAmministratore = new SpedizioniAmministratore();
		spedizioniAmministratore.setListener(spedizioniListener);
		contentPanel.add(spedizioniAmministratore, SPEDIZIONI_AMMINISTRATORE_PANEL);

		nuovaSpedizione = new NuovaSpedizione();
		nuovaSpedizione.setListener(nuovaSpedizioneListener);
		contentPanel.add(nuovaSpedizione, NUOVA_SPEDIZIONE);
		
		addWindowListener(this);
	}
	
	/**
	 * Permette di sostituire il pannello correntemente visualizzato nel {@link CardLayout} del {@link JFrame}.
	 * 
	 * @param panelName Nome del pannello da visualizzare (impostato quando il pannello è stato aggiunto).
	 */
	private void switchToPanel(String panelName) {
		((CardLayout)contentPanel.getLayout()).show(contentPanel, panelName);
	}
	
	/**
	 * Listener che si occupa di visualizzare:
	 * 	<ul>
	 * 		<li> {@link Registrazione}: quando viene richiesta la registrazione di un nuovo @Cliente </li>
	 * 		<li> {@link SpedizioniCliente}: quando viene effettuato il login di un @Cliente </li>
	 * 		<li> {@link SpedizioniAmministratore}: quando viene effettuato il login dell'amministratore </li>
	 * 	</ul>
	 */
	private Login.LoginListener loginListener = new Login.LoginListener() {

		@Override
		public void on_registrazioneRichiesta() {
			switchToPanel(REGISTRAZIONE_PANEL);
		}

		@Override
		public void on_loginCliente(Cliente c) {
			cliente = c;
			spedizioniCliente.setUsername(c.getUsername());
			switchToPanel(SPEDIZIONI_CLIENTE_PANEL);
		}

		@Override
		public void on_loginAmministratore() {
			switchToPanel(SPEDIZIONI_AMMINISTRATORE_PANEL);
		}
	};
	
	/**
	 * Listener che si occupa di visualizzare {@link Login} una volta usciti dalla schermata di Registrazione.
	 */
	private Registrazione.RegistrazioneListener registrazioneListener = new Registrazione.RegistrazioneListener() {
		
		@Override
		public void on_exit() {
			switchToPanel(LOGIN_PANEL);
		}

	};
	
	/**
	 * Listener che si occupa di visualizzare:
	 * <ul>
	 * 	<li> {@link Login}: una volta usciti dalla schermata delle spedizioni </li>
	 * 	<li> {@link NuovaSpedizione}: quando viene richiesto l'inserimento di una nuova spedizione </li>
	 * </ul>
	 */
		
	private SpedizioniCliente.SpedizioniClienteListener spedizioniListener = new SpedizioniCliente.SpedizioniClienteListener() {
		
		@Override
		public void on_nuovaSpedizione() {
			nuovaSpedizione.setUsername(cliente.getUsername());
			nuovaSpedizione.setIndirizzo(cliente.getIndirizzo());
			switchToPanel(NUOVA_SPEDIZIONE);
		}
		
		@Override
		public void on_logout() {
			switchToPanel(LOGIN_PANEL);
		}
	};
	
	/**
	 * Listener che si occupa di visualizzare {@link SpedizioniCliente} una volta usciti da {@link NuovaSpedizione}
	 */
	private NuovaSpedizione.NuovaSpedizioneListener nuovaSpedizioneListener = new NuovaSpedizione.NuovaSpedizioneListener() {
		
		@Override
		public void on_exit() {
			switchToPanel(SPEDIZIONI_CLIENTE_PANEL);
		}
		
	};
	
	/**
	 * Override dell'evento {@link #windowClosing(WindowEvent) windowClosing} per salvare le informazioni su clienti e spedizioni su file.
	 */

	@Override
	public void windowClosing(WindowEvent e) {
		DaoCliente.getInstance().saveChanges();
		DaoSpedizione.getInstance().saveChanges();
	}

	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
