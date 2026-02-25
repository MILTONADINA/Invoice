package com.invoice.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String invoiceNumber;
    private LocalDate dateIssued;
    private LocalDate dueDate;
    private Client client;
    private List<LineItem> lineItems = new ArrayList<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal taxAmount = BigDecimal.ZERO;
    private BigDecimal discountAmount = BigDecimal.ZERO;
    private String paymentStatus; // "Paid", "Unpaid", "Overdue"

    // Getters and Setters
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.client = client;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem item) {
        if (item != null) {
            this.lineItems.add(item);
            recalculateTotals();
        }
    }

    public void setLineItems(List<LineItem> lineItems) {
        if (lineItems != null) {
            this.lineItems = new ArrayList<>(lineItems);
            recalculateTotals();
        }
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = (taxAmount != null && taxAmount.compareTo(BigDecimal.ZERO) >= 0) ? taxAmount : BigDecimal.ZERO;
        recalculateTotals();
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = (discountAmount != null && discountAmount.compareTo(BigDecimal.ZERO) >= 0)
                ? discountAmount
                : BigDecimal.ZERO;
        recalculateTotals();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    private void recalculateTotals() {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (LineItem item : lineItems) {
            if (item.getTotalPrice() != null) {
                subTotal = subTotal.add(item.getTotalPrice());
            }
        }

        // Final Total = (Subtotal + Tax) - Discount
        BigDecimal calculatedTotal = subTotal.add(this.taxAmount).subtract(this.discountAmount);

        // Prevent negative totals
        if (calculatedTotal.compareTo(BigDecimal.ZERO) < 0) {
            this.totalAmount = BigDecimal.ZERO;
        } else {
            this.totalAmount = calculatedTotal;
        }
    }
}
