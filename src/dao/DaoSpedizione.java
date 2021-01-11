package dao;


import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Collectors;

import data.Spedizione;

/**
 * Sottoclasse di {@link Dao} che si occupa di gestire oggetti di tipo {@link Spedizione}.
 * La classe è costruita con un pattern del tipo Singleton.
 * 
 * @author Matteo Federico Goghero - 143143
 */
public class DaoSpedizione extends Dao<Spedizione>{

	static DaoSpedizione instance = null;
	
	/**
	 * @return L'unica istanza della classe.
	 */
	static public DaoSpedizione getInstance() {
		if (instance == null) {
			instance = new DaoSpedizione();
		}
		return instance;
	}
	
	private DaoSpedizione(){
		super("Spedizioni.dat");
	}
	
	/**
	 * Metodo per inserire una nuova spedizione.
	 * @param spedizione La spedizione da inserire.
	 */
	public void insertSpedizione(Spedizione spedizione) {
		mData.add(spedizione);
	}
	
	/**
	 * Metodo per ottenere una spedizione a partire dal suo codice univoco.
	 * @param codice Il codice della spedizione da ottenere.
	 * @return <div> {@link Spedizione} se il codice passato è valido <br/> <code>null</code>: se il codice passato non è valido </div>
	 */
	public Spedizione getSpedizione(String codice) {
		for (Spedizione spedizione : mData) {
			if (spedizione.getCodice().contentEquals(codice)) {
				return spedizione;
			}
		}
		return null;
	}
	
	/**
	 * Metodo per rimuovere una spedizione a partire dal suo codice univoco.
	 * @param codice Il codice della spedizione da rimuovere.
	 * @return <div> <code>true</code>: se il codice passato è valido <br/> <code>false</code>: se il codice passato non è valido </div>
	 */
	public boolean deleteSpedizione(String codice) {
		Spedizione s = getSpedizione(codice);
		if (s != null) {
			mData.remove(s);
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per ottenere tutte le spedizioni che appartengono a un {@link Cliente}.
	 * @param codice Il codice del cliente.
	 * @return Un {@link Vector} (può anche essere vuoto) contente le spedizioni del cliente indicato.
	 */
	public Vector<Spedizione> getSpedizioniForCliente(String username){
		return mData.stream()
				.filter(s -> s.getCodiceCliente().contentEquals(username))
				.collect(Collectors.toCollection(Vector::new));
	}
	
	/**
	 * Metodo per ottenere tutte le spedizioni presenti.
	 * Le spedizioni sono ordinate per nome utente dei clienti.
	 * @return  Un {@link Vector} (può anche essere vuoto) contente tutte le spedizioni.
	 */
	public Vector<Spedizione> getAllSpedizioni(){
		return mData.stream()
				.sorted(new Comparator<Spedizione>() {
					@Override
					public int compare(Spedizione s1, Spedizione s2) {
						return s1.getCodiceCliente().compareTo(s2.getCodiceCliente());
					}
					
				}).collect(Collectors.toCollection(Vector::new));
	}
	
	public void printAll() {
		for (Spedizione sped : mData) {
			System.out.println(sped);
		}
	}
	
}
