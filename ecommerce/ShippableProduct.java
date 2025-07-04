package ecommerce;

import java.time.LocalDate;

public class ShippableProduct extends Product implements Shippable {
    protected Double weightKg; // weight in KG

    public ShippableProduct(String name, double price, int quantity, double weightKg, LocalDate expirationDate) {
        super(name, price, quantity, expirationDate);
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Product weight must be positive");
        }
        this.weightKg = weightKg;
    }

    @Override
    public double getWeight() {
        if (weightKg == null) {
            throw new UnsupportedOperationException("Product not shippable");
        }
        return weightKg;
    }
}
