package ecommerce;

import java.util.ArrayList;
import java.util.List;

import ecommerce.dto.CartDTO;

public class CheckoutService {
    public void checkout(Customer customer) {
        Cart cart = customer.getCart();

        if (cart.isEmpty()) throw new IllegalStateException("Error: Cart is empty");

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (CartDTO item : cart.getItems()) {
            if (item.product.isExpired()) {
                throw new IllegalStateException("Product " + item.product.getName() + " is expired.");
            }
            if (item.quantity > item.product.getQuantity()) {
                throw new IllegalStateException("Product " + item.product.getName() + " is out of stock.");
            }

            subtotal += item.product.getPrice() * item.quantity;

            if (item.product instanceof Shippable) {
                toShip.add((Shippable) item.product);
            }
        }

        double shippingFee = calculateShippingFees(toShip);
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance.");
        }

        for (CartDTO item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }

        customer.deductBalance(total);

        if (!toShip.isEmpty()) {
            new ShippingService().ship(toShip, customer.getCart());
        }

        // console output
        System.out.println("** Checkout receipt **");
        System.out.println("Customer Name: \t" + customer.getName());
        for (CartDTO item :  cart.getItems()) {
            System.out.println(item.quantity + "x " + item.product.getName() + "\t" + item.product.getPrice() * item.quantity);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: \t" + subtotal);
        System.out.println("Shipping: \t" + shippingFee);
        System.out.println("Total Paid: \t" + total);
        System.out.println("Remaining Balance: \t" + customer.getBalance());

        cart.clear();
    }

    private double calculateShippingFees(List<Shippable> items) {
        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        return totalWeight * 2.0;
    }
}
