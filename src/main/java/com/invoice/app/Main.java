package com.invoice.app;

import com.invoice.domain.Client;
import com.invoice.domain.Invoice;
import com.invoice.domain.LineItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a client
        Client client = new Client();
        client.setClientId("C001");
        client.setName("Tech Solutions Inc.");
        client.setBillingAddress("123 Business Park, Tech City");
        client.setContactEmail("billing@techsolutions.com");
        client.setContactPhone("+123456789");

        // Create line items
        LineItem item1 = new LineItem();
        item1.setDescription("Software License - Enterprise Plan");
        item1.setQuantity(2);
        item1.setUnitPrice(new BigDecimal("500.00"));

        LineItem item2 = new LineItem();
        item2.setDescription("Cloud Storage - 1TB");
        item2.setQuantity(5);
        item2.setUnitPrice(new BigDecimal("100.00"));

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(item1);
        lineItems.add(item2);

        // Create the invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV001");
        invoice.setDateIssued(LocalDate.now());
        invoice.setDueDate(LocalDate.now().plusDays(7)); // Due in 7 days
        invoice.setClient(client);
        invoice.setLineItems(lineItems);
        invoice.setTaxAmount(new BigDecimal("150.00"));
        invoice.setDiscountAmount(new BigDecimal("100.00"));
        invoice.setPaymentStatus("Unpaid");

        // Display invoice details
        System.out.println("Invoice Details:");
        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
        System.out.println("Date Issued: " + invoice.getDateIssued());
        System.out.println("Due Date: " + invoice.getDueDate());
        System.out.println("Client: " + invoice.getClient().getName());
        System.out.println("Billing Address: " + invoice.getClient().getBillingAddress());
        System.out.println("Contact Email: " + invoice.getClient().getContactEmail());
        System.out.println("Contact Phone: " + invoice.getClient().getContactPhone());

        System.out.println("\nLine Items:");
        for (LineItem item : invoice.getLineItems()) {
            System.out.println("Description: " + item.getDescription());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Unit Price: $" + item.getUnitPrice());
            System.out.println("Total Price: $" + item.getTotalPrice());
            System.out.println("---------------------------");
        }

        System.out.println("Tax Amount: $" + invoice.getTaxAmount());
        System.out.println("Discount Amount: $" + invoice.getDiscountAmount());
        System.out.println("Total Amount: $" + invoice.getTotalAmount());
        System.out.println("Payment Status: " + invoice.getPaymentStatus());
    }
}
