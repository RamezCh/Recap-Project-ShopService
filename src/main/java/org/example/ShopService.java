package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo products;
    private OrderListRepo orders;

    public ShopService(ProductRepo products, OrderListRepo orders) {
        this.products = products;
        this.orders = orders;
    }

    public Order placeOrder(List<Product> orderedProducts, String userId, String shippingAddress, String billingAddress, String paymentMethod) {
        if(orderedProducts == null) {
            return null;
        }

        for (Product product : orderedProducts) {
            if (products.getProduct(product.modelNumber()) == null) {
                System.out.println("Ordered product " + product.modelNumber() + " doesn't exist!");
                return null;
            }
        }

        BigDecimal totalPrice = new BigDecimal("0.00");
        for(Product product : orderedProducts) {
           totalPrice = totalPrice.add(product.price());
        }

        Order order = new Order(
                UUID.randomUUID().toString(),
                userId,
                orderedProducts,
                totalPrice,
                "Processing",
                shippingAddress,
                billingAddress,
                paymentMethod,
                LocalDateTime.now()
        );

        orders.add(order);

        return order;
    }
}
