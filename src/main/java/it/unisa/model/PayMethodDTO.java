package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;


public class PayMethodDTO implements Serializable {
	private int id;
	private String numeroCarta;
	private Date dataScadenza;
	private String codiceSicurezza;
	private String titolare;
	private int idAccount;
	public PayMethodDTO() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCodiceSicurezza() {
		return codiceSicurezza;
	}
	public void setCodiceSicurezza(String codiceSicurezza) {
		this.codiceSicurezza = codiceSicurezza;
	}
	public String getTitolare() {
		return titolare;
	}
	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	private static final long serialVersionUID = 1L;
	
	
}