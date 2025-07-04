package ecommerce;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Test normal flow
        System.out.println("=== Normal Checkout Flow ===");
        testNormalCheckout();

        System.out.println("\n=== Testing Corner Cases ===");
        testCornerCases();
    }

    private static void testNormalCheckout() {
        try {
            Product cheese = new ShippableProduct("Cheese", 10, 5, 0.2, LocalDate.now().plusDays(2));
            Product tv = new ShippableProduct("TV", 300, 2, 10, null);
            Product scratchCard = new Product("Mobile Scratch Card", 2, 100, null);

            Customer customer = new Customer("Mohamed", 5000);

            customer.getCart().addItem(cheese, 2);
            customer.getCart().addItem(tv, 1);
            customer.getCart().addItem(scratchCard, 5);

            new CheckoutService().checkout(customer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // TODO use JUnit
    private static void testCornerCases() {
        // Test 1: Null customer
        try {
            new CheckoutService().checkout(null);
        } catch (Exception e) {
            System.out.println("Test 1 - Null customer: " + e.getMessage());
        }

        // Test 2: Empty cart
        try {
            Customer customer = new Customer("Test", 100);
            new CheckoutService().checkout(customer);
        } catch (Exception e) {
            System.out.println("Test 2 - Empty cart: " + e.getMessage());
        }

        // Test 3: Out of stock product
        try {
            Product milk = new ShippableProduct("Milk", 7, 0, 1, LocalDate.now().minusDays(1));
            Customer customer = new Customer("Test", 100);
            customer.getCart().addItem(milk, 1);
        } catch (Exception e) {
            System.out.println("Test 3 - Out of stock: " + e.getMessage());
        }

        // Test 4: Expired product
        try {
            Product biscuits = new ShippableProduct("Biscuits", 5, 10, 0.7, LocalDate.now().minusDays(1));
            Customer customer = new Customer("Test", 100);
            customer.getCart().addItem(biscuits, 2);
        } catch (Exception e) {
            System.out.println("Test 4 - Expired product: " + e.getMessage());
        }

        // Test 5: Insufficient balance
        try {
            Product tv = new ShippableProduct("TV", 300, 2, 10, null);
            Customer customer = new Customer("Test", 100);
            customer.getCart().addItem(tv, 1);
            new CheckoutService().checkout(customer);
        } catch (Exception e) {
            System.out.println("Test 5 - Insufficient balance: " + e.getMessage());
        }

        // Test 6: Duplicate product in cart
        try {
            Product cheese = new ShippableProduct("Cheese", 10, 5, 0.2, LocalDate.now().plusDays(2));
            Customer customer = new Customer("Test", 1000);
            customer.getCart().addItem(cheese, 1);
            customer.getCart().addItem(cheese, 1); // Should fail
        } catch (Exception e) {
            System.out.println("Test 6 - Duplicate product: " + e.getMessage());
        }

        // Test 7: Negative weight
        try {
            Product invalidProduct = new ShippableProduct("Invalid", 10, 5, -0.5, LocalDate.now().plusDays(2));
        } catch (Exception e) {
            System.out.println("Test 7 - Negative weight: " + e.getMessage());
        }

        // Test 8: Empty product name
        try {
            Product invalidProduct = new Product("", 10, 5, LocalDate.now().plusDays(2));
        } catch (Exception e) {
            System.out.println("Test 8 - Empty product name: " + e.getMessage());
        }

        // Test 9: Negative price
        try {
            Product invalidProduct = new Product("Invalid", -10, 5, LocalDate.now().plusDays(2));
        } catch (Exception e) {
            System.out.println("Test 9 - Negative price: " + e.getMessage());
        }

        // Test 10: Negative quantity
        try {
            Product invalidProduct = new Product("Invalid", 10, -5, LocalDate.now().plusDays(2));
        } catch (Exception e) {
            System.out.println("Test 10 - Negative quantity: " + e.getMessage());
        }

    }
}
