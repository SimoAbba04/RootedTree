package it.unisa.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date; 

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Stato {
        lavorazione,
        spedito,
        consegnato;

        public static Stato fromString(String statoStr) {
            if (statoStr == null) {
                return lavorazione; // Valore di default
            }
            switch (statoStr.toLowerCase()) {
                case "in lavorazione": 
                case "lavorazione": return lavorazione;
                case "spedito": return spedito;
                case "consegnato": return consegnato;
                default: throw new IllegalArgumentException("Stato non valido: " + statoStr);
            }
        }
    }

    private int id;
    private int idAccount;
    private Date dataOrdine;
    private Stato stato;
    private double totale;
    private Collection<OrderDetailDTO> details;

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
    public Stato getStato() {
        return stato;
    }
    public void setStato(Stato stato) {
        this.stato = stato;
    }
    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }
    
    public Collection<OrderDetailDTO> getDetails() {
        return details;
    }
    public void setDetails(Collection<OrderDetailDTO> details) {
        this.details = details;
    }
}