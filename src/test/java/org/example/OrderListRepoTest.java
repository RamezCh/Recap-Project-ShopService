package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    private OrderListRepo orders;
    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        orders = new OrderListRepo();

        order1 = new Order(
                "ORD001", "USER123", List.of(
                new Product("LAP123", "Gaming Laptop", "High-performance gaming laptop", "Electronics", 35, 25, 3, "Black", "Aluminum", new BigDecimal("1299.99"), "USD", 2.5, "SKU-001", 10)
        ), new BigDecimal("1299.99"), "Pending", "123 Main St", "456 Billing St", "Credit Card", LocalDateTime.now());

        order2 = new Order(
                "ORD002", "USER456", List.of(
                new Product("PHN456", "Smartphone X", "Latest smartphone with OLED display", "Electronics", 15, 7, 0, "Silver", "Glass & Metal", new BigDecimal("899.99"), "USD", 0.4, "SKU-002", 50)
        ), new BigDecimal("899.99"), "Shipped", "789 Shipping Ln", "101 Billing Ave", "PayPal", LocalDateTime.now());

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void shouldReturnAllOrders() {
        assertEquals(2, orders.getOrders().size());
    }

    @Test
    void shouldAddOrderSuccessfully() {
        Order order3 = new Order(
                "ORD003", "USER789", List.of(
                new Product("TAB789", "Tablet Pro", "High-performance tablet", "Electronics", 20, 10, 5, "Gray", "Metal", new BigDecimal("699.99"), "USD", 1.0, "SKU-003", 15)
        ), new BigDecimal("699.99"), "Processing", "567 New St", "890 Billing Rd", "Debit Card", LocalDateTime.now());
        orders.add(order3);
        assertEquals(3, orders.getOrders().size());
        assertTrue(orders.getOrders().contains(order3));
    }

    @Test
    void shouldRemoveOrderByInstance() {
        orders.remove(order1);
        assertEquals(1, orders.getOrders().size());
        assertNull(orders.getOrder("ORD001"));
    }

    @Test
    void shouldRemoveOrderByOrderNumber() {
        orders.remove("ORD002");
        assertEquals(1, orders.getOrders().size());
        assertNull(orders.getOrder("ORD002"));
    }

    @Test
    void shouldReturnOrderByOrderNumber() {
        Order expected = order2;
        Order actual = orders.getOrder("ORD002");
        assertEquals(expected, actual);
    }
}
