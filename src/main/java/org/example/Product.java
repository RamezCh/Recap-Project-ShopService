package org.example;

import java.math.BigDecimal;

public record Product(
        String modelNumber,
        String name,
        String description,
        String category,
        int width,
        int height,
        int depth,
        String color,
        String material,
        BigDecimal price,
        String currencyCode,
        double weight,
        String sku,
        int stockQuantity
) {
}
