package ecommerce;

import java.util.List;

import ecommerce.dto.CartDTO;

public class ShippingService {
    private double totalWeight;

    public void ship(List<Shippable> items, Cart cart) {
        System.out.println("** Shipment notice **");
        for (Shippable item : items) {
            int quantity = getQuantityFromCart(cart, item.getName());
            double totalItemWeight = quantity * item.getWeight();
            System.out.printf("%dx %-15s %8.2fkg%n", quantity, item.getName(), totalItemWeight);
            totalWeight += totalItemWeight;
        }
        System.out.printf("Total package weight: %.2fkg%n%n", totalWeight);
    }

    private int getQuantityFromCart(Cart cart, String itemName) {
        for (CartDTO cartItem : cart.getItems()) {
            if (cartItem.product.getName().equals(itemName)) {
                return cartItem.quantity;
            }
        }
        return 0;
    }
}
