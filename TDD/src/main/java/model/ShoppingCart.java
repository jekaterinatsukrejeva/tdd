package model;

import exception.CheckoutException;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static final double SHIPPING_FEE_AMOUNT = 20.00;
    private List<Product> cart = new ArrayList<>();
    private double totalPrice;

    public List<Product> getCart() {
        return cart;
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public String checkout() {
        calculateTotalPrice();
        if (cart.isEmpty()) {
            throw new CheckoutException("Shopping cart is empty");
        } else if (totalPrice > 1000.00) {
            totalPrice = totalPrice * 0.90;
            return "Checkout successful with 10% discount";
        } else if (totalPrice < 100.00) {
            totalPrice = totalPrice + SHIPPING_FEE_AMOUNT;
            return "Checkout successful and added " + SHIPPING_FEE_AMOUNT + "€ shipping fee";
        }
        return "Checkout successful";
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice = cart.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

}
