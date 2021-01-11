package data;

import java.io.Serializable;

/**
 * Classe che rappresenta un cliente.
 * 
 * @author Matteo Federico Goghero - 143143
 *
 */
public class Cliente implements Serializable{
	
	private String username;
	private String password;
	private String indirizzo;
	
	public Cliente(String username, String password, String indirizzo){
		this.username = username;
		this.password = password;
		this.indirizzo = indirizzo;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@Override
	public String toString() {
		return "Cliente [username=" + username + ", password=" + password + ", indirizzo=" + indirizzo + "]";
	}
}
