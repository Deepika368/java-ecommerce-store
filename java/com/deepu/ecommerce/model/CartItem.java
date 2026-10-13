package com.deepu.ecommerce.model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void increase() { quantity++; }
    public void decrease() { if (quantity > 1) quantity--; }
    public double getSubtotal() { return product.getPrice() * quantity; }
}
