package ecommerce.dto;

import ecommerce.Product;

public class CartDTO {
    public Product product;
    public int quantity;

    public CartDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
