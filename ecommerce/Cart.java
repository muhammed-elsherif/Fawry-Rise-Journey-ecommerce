package ecommerce;

import java.util.ArrayList;
import java.util.List;

import ecommerce.dto.CartDTO;

public class Cart {
    private List<CartDTO> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        if (product.isExpired()) {
            throw new IllegalArgumentException("Product " + product.getName() + " is expired");
        }
        items.add(new CartDTO(product, quantity));
    }

    public List<CartDTO> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}

