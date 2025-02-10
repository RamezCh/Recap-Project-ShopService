package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Arrays;

class ShopServiceTest {

    private ShopService shopService;
    private ProductRepo productRepo;
    private OrderListRepo orderListRepo;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
        orderListRepo = new OrderListRepo();
        shopService = new ShopService(productRepo, orderListRepo);
    }

    @Test
    void testPlaceOrder_Success() {
        Product product = new Product(
                "12345", "Product A", "Description", "Category", 10, 20, 30, "Red",
                "Plastic", new BigDecimal("100.00"), "USD", 1.0, "SKU123", 10);

        productRepo.add(product);

        Order order = shopService.placeOrder(
                Arrays.asList(product), "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNotNull(order);
        assertEquals("userId123", order.userId());
        assertEquals(new BigDecimal("100.00"), order.totalPrice());
        assertEquals("Processing", order.status());
        assertEquals(1, orderListRepo.getOrders().size());
    }

    @Test
    void testPlaceOrder_ProductNotFound() {
        Product product = new Product(
                "12345", "Product A", "Description", "Category", 10, 20, 30, "Red",
                "Plastic", new BigDecimal("100.00"), "USD", 1.0, "SKU123", 10);

        Order order = shopService.placeOrder(
                Arrays.asList(product), "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNull(order);
    }

    @Test
    void testPlaceOrder_MultipleProducts() {
        Product product1 = new Product(
                "12345", "Product A", "Description", "Category", 10, 20, 30, "Red",
                "Plastic", new BigDecimal("50.00"), "USD", 1.0, "SKU123", 10);
        Product product2 = new Product(
                "67890", "Product B", "Description", "Category", 10, 20, 30, "Blue",
                "Metal", new BigDecimal("75.00"), "USD", 1.0, "SKU456", 5);

        productRepo.add(product1);
        productRepo.add(product2);

        Order order = shopService.placeOrder(
                Arrays.asList(product1, product2), "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNotNull(order);
        assertEquals(new BigDecimal("125.00"), order.totalPrice());
    }

    @Test
    void testPlaceOrder_EmptyProductList() {
        Order order = shopService.placeOrder(
                Arrays.asList(), "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNull(order);
    }

    @Test
    void testPlaceOrder_NullProductList() {
        Order order = shopService.placeOrder(
                null, "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNull(order);
    }

    @Test
    void testPlaceOrder_InvalidProductData() {
        Product invalidProduct = new Product(
                "", "Product A", "", "Category", 0, 0, 0, "", "Plastic", new BigDecimal("100.00"), "USD", 0.0, "SKU123", 0);

        Order order = shopService.placeOrder(
                Arrays.asList(invalidProduct), "userId123", "123 Street", "456 Avenue", "Credit Card");

        assertNull(order);
    }

}
