package ecommerce;

public class Customer {
    private String name;
    private double balance;
    private Cart cart = new Cart();

    public Customer(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.name = name;
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void incrementBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to increment cannot be negative");
        }
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deduct cannot be negative");
        }
        if (this.balance < amount) {
            throw new IllegalStateException("Insufficient balance for deduction");
        }
        this.balance -= amount;
    }
}
