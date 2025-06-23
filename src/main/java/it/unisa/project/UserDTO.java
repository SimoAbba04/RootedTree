package it.unisa.project;

import java.io.Serializable;
import java.sql.Date;


public class UserDTO implements Serializable {
	int id;
	String nome;
	String cognome;
	String email;
	String pw;
	Date dataNascita;
	//add card numbers 
	//Add addresses
	
	
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


	private static final long serialVersionUID = 1L;

}
