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
        if (orderedProducts == null || orderedProducts.isEmpty()) {
            System.out.println("No products ordered.");
            return null;
        }

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Product product : orderedProducts) {
            Product existingProduct = products.getProduct(product.modelNumber());

            if (existingProduct == null) {
                System.out.println("Ordered product " + product.modelNumber() + " doesn't exist!");
                return null;
            }

            if (existingProduct.stockQuantity() < 1) {
                System.out.println("Product " + product.modelNumber() + " is out of stock!");
                return null;
            }

            totalPrice = totalPrice.add(existingProduct.price());
        }

        for (Product product : orderedProducts) {
            Product existingProduct = products.getProduct(product.modelNumber());
            products.updateProduct(existingProduct.withStockQuantity(existingProduct.stockQuantity() - 1));
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
