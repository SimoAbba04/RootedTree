package it.unisa.model;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	private int id;
	private String nome;
	private String descrizione;
	private double prezzo;
	private int disponibilità;
	public enum Categoria {
	    bonsai,
	    vaso,
	    cura;
	    public static Categoria fromString(String categoriaStr) {
	        if (categoriaStr == null) throw new IllegalArgumentException("Categoria non valida: " + categoriaStr);
	        switch (categoriaStr.toLowerCase()) {
	            case "bonsai": return bonsai;
	            case "vaso": return vaso;
	            case "cura": return cura;
	            default: throw new IllegalArgumentException("Categoria non valida:" + categoriaStr);
	        }
	    }
	}
	Categoria categoria;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getDisponibilità() {
		return disponibilità;
	}
	public void setDisponibilità(int disponibilità) {
		this.disponibilità = disponibilità;
	}
	public void setCategoria(Categoria ct) {
		this.categoria = ct;
	}
	public Categoria getCategoria() {
		return this.categoria;
	}
	private static final long serialVersionUID = 1L;

	
}