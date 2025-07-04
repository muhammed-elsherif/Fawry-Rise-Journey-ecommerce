package ecommerce;

import java.time.LocalDate;

public class Product {
    private final LocalDate expirationDate;

    private String name;
    private double price;
    private int quantity;
    private Double weight;

    protected Product(String name, double price, int quantity, LocalDate expirationDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isExpired() {
        return expirationDate != null && LocalDate.now().isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void reduceQuantity(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to reduce cannot be negative");
        }
        if (qty > quantity) {
            throw new IllegalStateException("Cannot reduce quantity by more than available stock");
        }
        this.quantity -= qty;
    }
}
