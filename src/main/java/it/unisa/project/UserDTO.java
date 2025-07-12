package it.unisa.project;

import java.io.Serializable;
import java.sql.Date;


public class UserDTO implements Serializable {
	public enum Ruolo {
	    utente,
	    admin;
	    public static Ruolo fromString(String ruoloStr) {
	        if (ruoloStr == null) return utente; // default
	        switch (ruoloStr.toLowerCase()) {
	            case "admin": return admin;
	            case "utente": return utente;
	            default: throw new IllegalArgumentException("Ruolo non valido: " + ruoloStr);
	        }
	    }
	}
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String pw;
	private Date dataNascita;
	Ruolo ruolo;
	
	
	public UserDTO() {}
	
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDataNascita() {
		return dataNascita;
	}



	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}


	public String getPassword() {
		return pw;
	}



	public void setPassword(String pw) {
		this.pw = pw;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	private static final long serialVersionUID = 1L;

}
