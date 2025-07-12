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
	private double totale;
	
	private static final long serialVersionUID = 1L;
}