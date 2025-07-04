package ecommerce;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // TODO (add weight to product?)
        Product cheese = new ShippableProduct("Cheese", 10, 5, 0.2, LocalDate.now().plusDays(2));
        Product biscuits = new ShippableProduct("Biscuits", 5, 10, 0.7, LocalDate.now().minusDays(1)); // expired
        Product tv = new ShippableProduct("TV", 300, 2, 10, null);
        Product scratchCard = new Product("Mobile Scratch Card", 2, 100, null);

        Customer customer = new Customer("Mohamed", 5000);

        /*
            - if quantity exceed of the availability will raise an error
            - insufficient balance will raise an error
            - expired products will raise an error
        */
        customer.getCart().addItem(cheese, 2);
        customer.getCart().addItem(tv, 2);
        customer.getCart().addItem(scratchCard, 5);

        new CheckoutService().checkout(customer);
    }
}
