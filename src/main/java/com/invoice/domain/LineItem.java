package com.invoice.domain;

import java.math.BigDecimal;

public class LineItem {
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public LineItem() {}

    public LineItem(String description, int quantity, BigDecimal unitPrice) {
        setDescription(description);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
    }

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description; 
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { 
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity; 
        updateTotalPrice();
    }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { 
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative or null");
        }
        this.unitPrice = unitPrice; 
        updateTotalPrice();
    }

    public BigDecimal getTotalPrice() { return totalPrice; }

    private void updateTotalPrice() {
        if (this.unitPrice != null && this.quantity > 0) {
            this.totalPrice = this.unitPrice.multiply(new BigDecimal(this.quantity));
        }
    }
}
