package panels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import customcomponents.ColoredRowRenderer;
import customcomponents.CustomFont;
import customcomponents.CustomPanel;
import dao.DaoSpedizione;
import tablemodels.SpedizioniTableModel;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * Pannello astratto che si occupa di visualizzare una lista di spedizioni.
 * Il pannello mette a disposizione anche un bottone per efettuare il logout dell'utente corrente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */
abstract public class Spedizioni extends JPanel {
	
	private SpedizioniListener listener;
	protected JTable table_spedizioni;
	protected SpedizioniTableModel model;
	protected DaoSpedizione daoSpedizione = DaoSpedizione.getInstance();
	protected JPanel pnl_bottoni;
	
	/**
	 * @param model Il modello utilizzato per visualizzare le spedizioni all'interno della tabella.
	 */
	public Spedizioni(SpedizioniTableModel model) {
		this.model = model;
		this.setBounds(0, 0, 1080, 720);
		setLayout(new BorderLayout(20, 20));
		
		Dimension buttonDimension = new Dimension(150, 40);
		
		JLabel lbl_listaSpedizioni = new JLabel("Lista spedizioni");
		lbl_listaSpedizioni.setBorder(new EmptyBorder(30, 0, 0, 0));
		lbl_listaSpedizioni.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_listaSpedizioni.setFont(new CustomFont(30));
		add(lbl_listaSpedizioni, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new CompoundBorder(new EmptyBorder(0, 20, 0, 20), new LineBorder(new Color(0, 0, 0))));
		add(scrollPane);
		
		table_spedizioni = new JTable();
		table_spedizioni.setRowHeight(30);
		scrollPane.setViewportView(table_spedizioni);
		
		
		table_spedizioni.setModel(model);
		
		pnl_bottoni = new JPanel();
		pnl_bottoni.setBorder(new EmptyBorder(0, 0, 30, 0));
		add(pnl_bottoni, BorderLayout.SOUTH);
		pnl_bottoni.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_logout.setPreferredSize(buttonDimension);
		pnl_bottoni.add(btn_logout);
		table_spedizioni.setDefaultRenderer(Object.class, new ColoredRowRenderer());
		
		table_spedizioni.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int row = table_spedizioni.rowAtPoint(evt.getPoint());
		        int col = table_spedizioni.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	onTableClick(row, col, evt);
		        }
		    }
		});
		
		btn_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.on_logout();
				}
			}
			
		});
	}
	
	/**
	 * Metodo che viene chiamato quando viene effettuato un click del mouse sulla tabella delle spedizioni.
	 * @param row La riga della tabella cliccata
	 * @param column La colonna della tabella cliccata
	 * @param evt L'evento generato dal click del mouse
	 */
	abstract protected void onTableClick(int row, int column, MouseEvent evt);
	
	public void setListener(SpedizioniListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Interfaccia per comunicare con il {@JFrame} che ospita il pannello.
	 * @author Matteo Federico Goghero - 143143
	 */
	interface SpedizioniListener {
		/**
		 * Metodo che viene chiamato quando è necessario effettuare il logout dell'utente corrente.
		 */
		void on_logout();
	}
}
