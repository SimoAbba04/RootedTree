package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;

import it.unisa.model.ProductDTO.Categoria;


public class OrderDTO implements Serializable {
	private int id;
	private int idAccount;
	private Date dataOrdine;
	public enum Stato {
	    lavorazione,
	    spedito,
	    consegnato;
	    public static Stato fromString(String statoStr) {
	        if (statoStr == null) throw new IllegalArgumentException("Stato non valido: " + statoStr);
	        switch (statoStr.toLowerCase()) {
	            case "lavorazione": return lavorazione;
	            case "spedito": return spedito;
	            case "consegnato": return consegnato;
	            default: throw new IllegalArgumentException("Categoria non valida:" + statoStr);
	        }
	    }
	}
	Stato st;
	private double totale;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setStato(Stato st) {
		this.st = st;
	}
	public Stato getStato() {
		return this.st;
	}

	private static final long serialVersionUID = 1L;
}