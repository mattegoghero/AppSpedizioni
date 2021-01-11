package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * Data Access Object (Dao) che permette di salvare e caricare da file un {@link Vector} di oggetti {@link Serializable}.
 * Mantiene al suo interno un {@link Vector} delle istanze degli oggetti caricati da file o aggiunti dinamicamente.
 * La classe sfrutta i Generics accettando classi che estendono il tipo Serializable in modo che possano essere scritte e lette su file.
 * @author Matteo Federico Goghero - 143143
 * @param <T extends Serializable> Generic del tipo di oggetto gestito dal Dao.
 */

public class Dao<T extends Serializable> {
	/**
	 * {@link Vector} che contiene gli oggetti caricati da file o aggiunti dinamicamente.
	 * Le modifiche apportate non vengono salvate finch� non si chiama il metodo {@link #saveChanges() saveChanges}
	 */
	protected Vector<T> mData;
	
	/**
	 * Nome del file su cui salvare e caricare gli oggetti di {@link Dao#mData mData}.
	 */
	private String filename;
	
	/**
	 * Il costruttore richiama automaticamente il metodo {@link #readFromFile() readFromFile}.
	 * Se la lettura dei dati da file fallisce {@link #mData mData} viene inizializzato con un {@link Vector} vuoto.
	 * @param filename Nome del file su cui leggere e scrivere i dati.
	 */
	protected Dao(String filename) {
		this.filename = filename;
		if (!readFromFile()) {
			mData = new Vector<T>();
		}
	}
	
	/**
	 * Metodo che legge dal file indicato da {@link #filename filename} i dati da caricare all'interno di {@link Dao#mData mData}.
	 * In caso di fallimento dell'operazione viene scritto sull'output di sistema un messaggio d'avviso.
	 * @return <div> <code>true</code>: se la lettura è andata a buon fine </br> <code>false</code>: altrimenti </div>
	 */
	@SuppressWarnings("unchecked")
	private boolean readFromFile() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream input = new ObjectInputStream(fis);	
			mData = (Vector<T>)input.readObject();
			fis.close();
			input.close();
			return true;
		}catch(Exception e) {
			System.out.println("Cannot read " + filename);
			return false;
		}
	}
	
	/**
	 * Metodo che scrive sul file indicato da {@link #filename filename} i dati salvati all'interno di {@link Dao#mData mData}.
	 * In caso di fallimento dell'operazione viene scritto sull'output di sistema un messaggio d'avviso.
	 * @return <div> <code>true</code>: se la scrittura è andata a buon fine </br> <code>false</code>: altrimenti </div>
	 */
	public boolean saveChanges() {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(fos);
            output.writeObject(mData);
            output.close();
            fos.close();
            return true;
		}catch(Exception e) {
			System.out.println("Cannot save changes to " + filename);
			return false;
		}
	}
}
