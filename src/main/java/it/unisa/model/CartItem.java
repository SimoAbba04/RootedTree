package it.unisa.model;

import java.io.Serializable;

/**
 * Rappresenta un singolo articolo all'interno del carrello.
 * Contiene il prodotto e la quantità desiderata.
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ProductDTO product;
    private int quantity;

    public CartItem(ProductDTO product) {
        this.product = product;
        this.quantity = 1; // La quantità iniziale è sempre 1
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void incrementQuantity() {
        this.quantity++;
    }
    
    public double getSubtotal() {
        return this.product.getPrezzo() * this.quantity;
    }
}