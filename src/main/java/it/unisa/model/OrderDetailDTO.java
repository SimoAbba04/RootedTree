package it.unisa.model;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int idOrdine;
    private int idProdotto;
    private int qta;
    private double prezzoUnitario;

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdOrdine() {
        return idOrdine;
    }
    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }
    public int getIdProdotto() {
        return idProdotto;
    }
    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }
    public int getQta() {
        return qta;
    }
    public void setQta(int qta) {
        this.qta = qta;
    }
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }
    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }
}