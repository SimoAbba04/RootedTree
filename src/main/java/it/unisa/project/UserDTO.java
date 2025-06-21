package it.unisa.project;

import java.io.Serializable;
import java.util.Date;


public class UserDTO implements Serializable {
	String nome;
	String cognome;
	String email;
	Date dataNascita;
	//add card numbers 
	//Add addresses
	
	
	public UserDTO(String n, String c, String e, Date d) {
		nome = n;
		cognome = c;
		email = e;
		dataNascita = d;
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



	private static final long serialVersionUID = 1L;

}
