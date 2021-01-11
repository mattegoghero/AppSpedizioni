package dao;


import data.Cliente;

/**
 * Sottoclasse di {@link Dao} che si occupa di gestire oggetti di tipo @Cliente.
 * La classe è costruita con un pattern del tipo Singleton.
 * 
 * @author Matteo Federico Goghero - 143143
 */
public class DaoCliente extends Dao<Cliente>{
	
	static DaoCliente instance = null;
	
	/**
	 * @return L'unica istanza della classe.
	 */
	static public DaoCliente getInstance() {
		if (instance == null) {
			instance = new DaoCliente();
		}
		return instance;
	}
		
	private DaoCliente(){
		super("Clienti.dat");
	}
	
	/**
	 * Metodo per inserire un nuovo cliente.
	 * @param cliente Il Cliente da inserire.
	 * @return <div> <code>true</code>: se l'inserimento è andato a buon fine <br/> <code>false</code>: se è gia presente un altro cliente con lo stesso username e l'inserimento è fallito </div>
	 */
	public boolean insertCliente(Cliente cliente) {
		for (Cliente clienteEx : mData) {
			if (clienteEx.getUsername().contentEquals(cliente.getUsername())) {
				return false;
			}
		}
		mData.add(cliente);
		return true;
	}
	/**
	 * Metodo per ottenere un cliente a partire dal suo username.
	 * @param username L'username del cliente da ottenere.
	 * @return <div> {@link Cliente} se l'username passato è valido </div> <code>null</code> altrimenti </div>
	 */
	public Cliente getCliente(String username) {
		for (Cliente c : mData) {
			if (c.getUsername().contentEquals(username)) {
				return c;
			}
		}
		return null;
	}
	
	public void printAll() {
		for (Cliente cliente : mData) {
			System.out.println(cliente);
		}
	}
	
	
}
