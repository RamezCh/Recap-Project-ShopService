package org.example;

import java.time.LocalDateTime;
import java.util.List;

public record Order(
        String orderId,
        String userId,
        List<Product> products,
        double totalPrice,
        String status,
        String shippingAddress,
        String billingAddress,
        String paymentMethod,
        LocalDateTime orderDate
) {
}
