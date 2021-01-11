package panels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import customcomponents.CustomFont;
import customcomponents.CustomPanel;
import dao.DaoSpedizione;
import data.Spedizione;
import data.SpedizioneAssicurata;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

/**
 * Pannello che si occupa di creare una nuova spedizione per un cliente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */
public class NuovaSpedizione extends CustomPanel {
	private JFormattedTextField txtf_data;
	private JFormattedTextField txtf_peso;
	private JCheckBox chk_spedizioneAssicurata;
	private JFormattedTextField txtf_valoreAssicurato;
	private JTextField txt_destinazione;
	
	private DateFormat dateFormat;
	private NumberFormat valueFormat;
	private NumberFormat weightFormat;
	
	private String username;
	private String indirizzo;
	
	private NuovaSpedizioneListener listener;
	
	public NuovaSpedizione() {
		setBounds(0, 0, 1080, 720);
		
		addComponentListener (new ComponentAdapter () {
	        @Override
			public void componentShown ( ComponentEvent e ){
	            clearFields();
	        }

	        @Override
			public void componentHidden ( ComponentEvent e ){}
	    });
		setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_nuovaSpedizione = new JLabel("Nuova spedizione");
		lbl_nuovaSpedizione.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		lbl_nuovaSpedizione.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nuovaSpedizione.setFont(new CustomFont(30));
		add(lbl_nuovaSpedizione, BorderLayout.NORTH);
		
		JPanel pnl_input = new JPanel();
		add(pnl_input);
		
		GridBagLayout gbl_pnl_input = new GridBagLayout();
		gbl_pnl_input.columnWidths = new int[] {150, 250, 50, 0};
		gbl_pnl_input.rowHeights = new int[] {30, 30, 30, 30};
		pnl_input.setLayout(gbl_pnl_input);
		
		Insets lbl_Insets = new Insets(16, 8, 16, 8);
		Insets txt_insets = new Insets(8, 8, 8, 8);
		
		weightFormat = new DecimalFormat("0.000; 0.000"); 
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		valueFormat = new DecimalFormat("0.00; 0.00");
		
		
		JLabel lbl_destinazione = new JLabel("Destinazione");
		GridBagConstraints gbc_lbl_destinazione = new GridBagConstraints();
		gbc_lbl_destinazione.anchor = GridBagConstraints.EAST;
		gbc_lbl_destinazione.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_destinazione.insets = lbl_Insets;
		gbc_lbl_destinazione.gridx = 0;
		gbc_lbl_destinazione.gridy = 0;
		
		lbl_destinazione.setFont(new CustomFont(15));
		pnl_input.add(lbl_destinazione, gbc_lbl_destinazione);
		
		txt_destinazione = new JTextField();
		txt_destinazione.setColumns(10);
		GridBagConstraints gbc_txt_destinazione = new GridBagConstraints();
		gbc_txt_destinazione.insets = txt_insets;
		gbc_txt_destinazione.fill = GridBagConstraints.BOTH;
		gbc_txt_destinazione.gridx = 1;
		gbc_txt_destinazione.gridy = 0;
		
		pnl_input.add(txt_destinazione, gbc_txt_destinazione);
		
		JLabel lbl_peso = new JLabel("Peso");
		GridBagConstraints gbc_lbl_peso = new GridBagConstraints();
		gbc_lbl_peso.anchor = GridBagConstraints.EAST;
		gbc_lbl_peso.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_peso.insets = lbl_Insets;
		gbc_lbl_peso.gridx = 0;
		gbc_lbl_peso.gridy = 1;
		
		lbl_peso.setFont(new CustomFont(15));
		pnl_input.add(lbl_peso, gbc_lbl_peso);
		
		txtf_peso = new JFormattedTextField(weightFormat);
		GridBagConstraints gbc_txtf_peso = new GridBagConstraints();
		gbc_txtf_peso.fill = GridBagConstraints.BOTH;
		gbc_txtf_peso.insets = txt_insets;
		gbc_txtf_peso.gridx = 1;
		gbc_txtf_peso.gridy = 1;
		
		txtf_peso.setHorizontalAlignment(SwingConstants.TRAILING);
		pnl_input.add(txtf_peso, gbc_txtf_peso);
		
		JLabel lbl_pesoKg = new JLabel("Kg");
		GridBagConstraints gbc_lbl_pesoKg = new GridBagConstraints();
		gbc_lbl_pesoKg.anchor = GridBagConstraints.WEST;
		gbc_lbl_pesoKg.insets = lbl_Insets;
		gbc_lbl_pesoKg.gridx = 2;
		gbc_lbl_pesoKg.gridy = 1;
		
		lbl_pesoKg.setFont(new CustomFont(15));
		pnl_input.add(lbl_pesoKg, gbc_lbl_pesoKg);
		
		JLabel lbl_data = new JLabel("Data");
		GridBagConstraints gbc_lbl_data = new GridBagConstraints();
		gbc_lbl_data.anchor = GridBagConstraints.EAST;
		gbc_lbl_data.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_data.insets = lbl_Insets;
		gbc_lbl_data.gridx = 0;
		gbc_lbl_data.gridy = 2;
		
		lbl_data.setFont(new CustomFont(15));
		pnl_input.add(lbl_data, gbc_lbl_data);
		
		
		txtf_data = new JFormattedTextField(dateFormat);
		GridBagConstraints gbc_txtf_data = new GridBagConstraints();
		gbc_txtf_data.fill = GridBagConstraints.BOTH;
		gbc_txtf_data.insets = txt_insets;
		gbc_txtf_data.gridx = 1;
		gbc_txtf_data.gridy = 2;
		
		pnl_input.add(txtf_data, gbc_txtf_data);
		
		JLabel lbl_valoreAssicurato = new JLabel("Valore assicurato");
		GridBagConstraints gbc_lbl_valoreAssicurato = new GridBagConstraints();
		gbc_lbl_valoreAssicurato.anchor = GridBagConstraints.EAST;
		gbc_lbl_valoreAssicurato.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_valoreAssicurato.insets = lbl_Insets;
		gbc_lbl_valoreAssicurato.gridx = 0;
		gbc_lbl_valoreAssicurato.gridy = 3;
		
		lbl_valoreAssicurato.setFont(new CustomFont(15));
		pnl_input.add(lbl_valoreAssicurato, gbc_lbl_valoreAssicurato);
		
		
		txtf_valoreAssicurato = new JFormattedTextField(valueFormat);
		GridBagConstraints gbc_txtf_valoreAssicurato = new GridBagConstraints();
		gbc_txtf_valoreAssicurato.fill = GridBagConstraints.BOTH;
		gbc_txtf_valoreAssicurato.insets = txt_insets;
		gbc_txtf_valoreAssicurato.gridx = 1;
		gbc_txtf_valoreAssicurato.gridy = 3;
		
		txtf_valoreAssicurato.setHorizontalAlignment(SwingConstants.TRAILING);
		txtf_valoreAssicurato.setEnabled(false);
		pnl_input.add(txtf_valoreAssicurato, gbc_txtf_valoreAssicurato);
		
		JLabel lbl_euro = new JLabel("\u20AC");
		GridBagConstraints gbc_lbl_euro = new GridBagConstraints();
		gbc_lbl_euro.anchor = GridBagConstraints.WEST;
		gbc_lbl_euro.insets = lbl_Insets;
		gbc_lbl_euro.gridx = 2;
		gbc_lbl_euro.gridy = 3;
		
		lbl_euro.setFont(new CustomFont(15));
		pnl_input.add(lbl_euro, gbc_lbl_euro);
		
		chk_spedizioneAssicurata = new JCheckBox("Spedizione assicurata");
		GridBagConstraints gbc_chk_spedizioneAssicurata = new GridBagConstraints();
		gbc_chk_spedizioneAssicurata.gridx = 3;
		gbc_chk_spedizioneAssicurata.gridy = 3;
		
		chk_spedizioneAssicurata.setFont(new CustomFont(12));
		pnl_input.add(chk_spedizioneAssicurata, gbc_chk_spedizioneAssicurata);
		
		JPanel pnl_bottoni = new JPanel();
		pnl_bottoni.setBorder(new EmptyBorder(0, 0, 30, 0));
		add(pnl_bottoni, BorderLayout.SOUTH);
		pnl_bottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		Dimension buttonDimension = new Dimension(150, 40);
		
		JButton btn_conferma = new JButton("Conferma");
		btn_conferma.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_conferma);
		
		btn_conferma.setFont(new CustomFont(12));
		
		JButton btn_annulla = new JButton("Annulla");
		btn_annulla.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_annulla);
		
		
		btn_annulla.setFont(new CustomFont(12));
		
		btn_annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.on_exit();
				}
			}
		});
				
		btn_conferma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inserisciNuovaSpedizione();
			}
		});
		
		chk_spedizioneAssicurata.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        txtf_valoreAssicurato.setEnabled(chk_spedizioneAssicurata.isSelected());
		    }
		});
	}
	
	/**
	 * Metodo che si occupa di inserire una nuova spedizione a partire dai valori indicati nei campi di testo corrispondenti.
	 */
	private void inserisciNuovaSpedizione() {
		try {
			if (checkFields()) {
				Spedizione sped;
				double weight = weightFormat.parse(txtf_peso.getText()).doubleValue();
				Date data = dateFormat.parse(txtf_data.getText());
				if (chk_spedizioneAssicurata.isSelected()) {
					sped = new SpedizioneAssicurata(
								username,
								txt_destinazione.getText(),
								weight,
								data,
								valueFormat.parse(txtf_valoreAssicurato.getText()).doubleValue()
							);
				} else {							
					sped = new Spedizione(
								username,
								txt_destinazione.getText(),
								weight,
								data
							);
				}
				DaoSpedizione.getInstance().insertSpedizione(sped);
				if (listener != null) {
					listener.on_exit();
				}
			}
		}catch(Exception ex) {
			showWarningDialog("Errore durante l'inserimento della spedizione");
		}
	}
	
	/**
	 * Metodo che si occupa di controllare la correttezza dei valori inseriti nei vari campi presenti nel pannello.
	 * @return div><code>true</code> se i dati inseriti sono corretti <br/><code>false</code> altrimenti </div>
	 */
	private boolean checkFields() {
		try {
			Date today = dateFormat.parse(dateFormat.format(new Date())); //In questo modo ho solo la parte di data odierna e non l'orario che crea problemi con il controllo successivo
			if (txt_destinazione.getText().isBlank()) {
				showWarningDialog("La destinazione non può essere vuota");
				return false;
			} else if (txtf_peso.getText().isBlank() || weightFormat.parse(txtf_peso.getText()).doubleValue() < 0.001) {
				showWarningDialog("Il peso non può essere vuoto o di valore inferiore o uguale a 0");
				return false;
			} else if (txtf_data.getText().isBlank() || dateFormat.parse(txtf_data.getText()).before(today)) {
				showWarningDialog("La data non può essere vuota o antecedente a quella odierna");
				return false;
			} else if (chk_spedizioneAssicurata.isSelected() && (txtf_valoreAssicurato.getText().isEmpty() || valueFormat.parse(txtf_valoreAssicurato.getText()).doubleValue() < 0.01)) {
				showWarningDialog("Una spedizione assicurata non può avere un valore assicurato vuoto di valore inferiore o uguale a 0");
				return false;
			} 
		}catch(Exception e) {
			showWarningDialog("Errore durante il controllo dei dati");
			return false;
		}
				
		return true;
	}
	
	/**
	 * Metodo che resetta i campi da compilare con valori di default.
	 */
	private void clearFields() {
		txt_destinazione.setText(indirizzo);
		txtf_peso.setValue(null);;
		txtf_data.setText(dateFormat.format(new Date()));
		txtf_valoreAssicurato.setValue(null);
		chk_spedizioneAssicurata.setSelected(false);
		txtf_valoreAssicurato.setEnabled(false);
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setListener(NuovaSpedizioneListener listener) {
		this.listener = listener;
	}
	/**
	 * Interfaccia per comunicare con il {@JFrame} che ospita il pannello.
	 * @author Matteo Federico Goghero - 143143
	 */
	public interface NuovaSpedizioneListener {
		/**
		 * Metodo che viene chiamato per comunicare il termine dell'operazione di inserimento di una nuova spedizione (sia che abbia avuto successo o sia stata annullata).
		 */
		void on_exit();
	}
}
