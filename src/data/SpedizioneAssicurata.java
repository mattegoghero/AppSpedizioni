package data;

import java.util.Date;

/**
 * Classe che rappresenta una spedizione assicurata.
 * @author Matteo Federico Goghero - 143143
 *
 */
public class SpedizioneAssicurata extends Spedizione {


	private double valoreAssicurato;
	

	public SpedizioneAssicurata(String username, String destinazione, double peso, Date data, double valoreAssicurato) {
		super(username, destinazione, peso, data);
		this.valoreAssicurato = valoreAssicurato;
	}
	
	@Override
	public void setStato (StatoSpedizione stato) {
		this.stato = stato;
	}

	public double getValoreAssicurato() {
		return valoreAssicurato;
	}

	@Override
	public String toString() {
		return "SpedizioneAssicurata [codice=" + getCodice() + ", destinazione=" + getDestinazione() + ", peso=" + getPeso() + ", data=" + getData()
				+ ", stato=" + stato + ", valoreAssicurato=" + valoreAssicurato + "]";
	}

}
