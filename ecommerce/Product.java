package ecommerce;

import java.time.LocalDate;

public class Product {
    private final LocalDate expirationDate;

    private String name;
    private double price;
    private int quantity;
    private Double weight;

    protected Product(String name, double price, int quantity, LocalDate expirationDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public boolean isExpired() {
        return expirationDate != null && LocalDate.now().isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void reduceQuantity(int qty) {
        if (qty <= quantity) this.quantity -= qty;
    }
}
