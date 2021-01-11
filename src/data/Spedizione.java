package data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Classe che rappresenta una spedizione normale.
 * @author Matteo Federico Goghero - 143143
 *
 */
public class Spedizione implements Serializable {
	
	private String codice;
	private String codiceCliente;
	private String destinazione;
	private double peso;
	private Date data;
	protected StatoSpedizione stato;

	public Spedizione(String codiceCliente, String destinazione, double peso, Date data){
		codice = UUID.randomUUID().toString();
		stato = StatoSpedizione.IN_PREPARAZIONE;
		this.destinazione = destinazione;
		this.peso = peso;
		this.data = data;
		this.codiceCliente = codiceCliente;
	}
	
	public String getCodice() {
		return codice;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		if (peso >= 0)
			this.peso = peso;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatoSpedizione getStato() {
		return stato;
	}

	/**
	 * E' possibile impostare uno {@link StatoSpedizione} idoneo a una spedizione normale.
	 * @param stato Lo stato da impostare.
	 * 
	 */
	public void setStato(StatoSpedizione stato) {
		if (stato.isForNormal())
			this.stato = stato;
	}

	@Override
	public String toString() {
		return "Spedizione [codice=" + codice + ", destinazione=" + destinazione + ", peso=" + peso + ", data=" + data
				+ ", stato=" + stato + "]";
	}

	public String getCodiceCliente() {
		return codiceCliente;
	}

	
	
}
