package com.invoice.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @Test
    void testValidLineItemCreation() {
        LineItem item = new LineItem("Test Item", 2, new BigDecimal("10.50"));
        assertEquals("Test Item", item.getDescription());
        assertEquals(2, item.getQuantity());
        assertEquals(new BigDecimal("10.50"), item.getUnitPrice());
        assertEquals(new BigDecimal("21.00"), item.getTotalPrice());
    }

    @Test
    void testNegativeQuantityThrowsException() {
        LineItem item = new LineItem();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            item.setQuantity(-1);
        });
        assertEquals("Quantity must be greater than zero", exception.getMessage());
    }

    @Test
    void testZeroQuantityThrowsException() {
        LineItem item = new LineItem();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            item.setQuantity(0);
        });
        assertEquals("Quantity must be greater than zero", exception.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        LineItem item = new LineItem();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            item.setUnitPrice(new BigDecimal("-5.00"));
        });
        assertEquals("Unit price cannot be negative or null", exception.getMessage());
    }

    @Test
    void testNullDescriptionThrowsException() {
        LineItem item = new LineItem();
        assertThrows(IllegalArgumentException.class, () -> {
            item.setDescription(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            item.setDescription("   ");
        });
    }

    @Test
    void testTotalPriceUpdatesOnQuantityChange() {
        LineItem item = new LineItem("Test", 1, new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), item.getTotalPrice());

        item.setQuantity(3);
        assertEquals(new BigDecimal("300.00"), item.getTotalPrice());
    }
}
