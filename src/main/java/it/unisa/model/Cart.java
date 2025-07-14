package it.unisa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

  
    public synchronized void addItem(ProductDTO product) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) { //Se il prodotto è già nel carrello ne aumenta la quantità
                item.incrementQuantity(); 
                return;
            }
        }
        // Il prodotto non è nel carrello, aggiungilo come nuovo item
        items.add(new CartItem(product));
    }

  
    public synchronized void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }
    
    /**
     * Aggiorna la quantità di un prodotto nel carrello.
     * @param productId L'ID del prodotto da aggiornare.
     * @param quantity La nuova quantità. Se <= 0, l'articolo viene rimosso.
     */
    public synchronized void updateQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }
    
    public double getGrandTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
