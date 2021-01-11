package data;

import java.awt.Color;

/**
 * Interfaccia per descrivere i vari stati di una spedizione.
 * @author Matteo Federico Goghero - 143143
 */
public interface StatoSpedizioneInterface {
	/**
	 * @return {@link Color} che rappresenta lo stato.
	 */
	public Color getColor();
	
	/**
	 * @return Il nome formattato dello stato.
	 */
	public String getName();
	
	/**
	 * Metodo per stabilire se lo stato è adatto a una spedizione normale (non assicurata).
	 * @return <div> <code>true</code> se è uno stato adatto a una spedizione normale <br/> <code>false</code> altrimenti. </div>
	 */
	public boolean isForNormal();
}
