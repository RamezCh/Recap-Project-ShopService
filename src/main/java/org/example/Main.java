package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product laptop = new Product(
                "LAP123", "Gaming Laptop", "High-performance gaming laptop with RGB keyboard",
                "Electronics", 35, 25, 3, "Black", "Aluminum",
                new BigDecimal("1299.99"), "USD", 2.5, "SKU-001", 10
        );

        Product smartphone = new Product(
                "PHN456", "Smartphone X", "Latest smartphone with OLED display",
                "Electronics", 15, 7, 0, "Silver", "Glass & Metal",
                new BigDecimal("899.99"), "USD", 0.4, "SKU-002", 50
        );

        Product chair = new Product(
                "CHR789", "Ergonomic Office Chair", "Adjustable office chair with lumbar support",
                "Furniture", 60, 120, 60, "Blue", "Mesh & Metal",
                new BigDecimal("199.99"), "USD", 15.0, "SKU-003", 20
        );

        Product book = new Product(
                "BK321", "Java Programming Guide", "Comprehensive guide to Java programming",
                "Books", 15, 23, 2, "White", "Paper",
                new BigDecimal("49.99"), "USD", 1.2, "SKU-004", 100
        );
    }
}