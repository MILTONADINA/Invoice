package com.invoice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    private Invoice invoice;
    private Client mockClient;

    @BeforeEach
    void setUp() {
        invoice = new Invoice();
        mockClient = new Client();
        mockClient.setName("Test Client");
        invoice.setClient(mockClient);
    }

    @Test
    void testInvoiceTotalCalculationWithoutTaxOrDiscount() {
        LineItem item1 = new LineItem("Item 1", 2, new BigDecimal("50.00")); // 100
        LineItem item2 = new LineItem("Item 2", 1, new BigDecimal("75.00")); // 75

        invoice.addLineItem(item1);
        invoice.addLineItem(item2);

        assertEquals(new BigDecimal("175.00"), invoice.getTotalAmount());
    }

    @Test
    void testInvoiceTotalWithTaxAndDiscount() {
        LineItem item1 = new LineItem("Item 1", 2, new BigDecimal("50.00")); // 100
        invoice.addLineItem(item1);

        invoice.setTaxAmount(new BigDecimal("10.00"));
        invoice.setDiscountAmount(new BigDecimal("5.00"));

        // (100 + 10) - 5 = 105
        assertEquals(new BigDecimal("105.00"), invoice.getTotalAmount());
    }

    @Test
    void testNullClientThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoice.setClient(null);
        });
        assertEquals("Client cannot be null", exception.getMessage());
    }

    @Test
    void testNegativeTotalIsZeroedOut() {
        LineItem item1 = new LineItem("Item 1", 1, new BigDecimal("50.00"));
        invoice.addLineItem(item1);

        // Huge discount
        invoice.setDiscountAmount(new BigDecimal("100.00"));

        // Should not be -50, should floor to 0
        assertEquals(BigDecimal.ZERO, invoice.getTotalAmount());
    }
}
