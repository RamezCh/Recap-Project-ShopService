package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    private ProductRepo products;
    private Product laptop;
    private Product smartphone;

    @BeforeEach
    public void setUp() {
        products = new ProductRepo();
        laptop = new Product(
                "LAP123", "Gaming Laptop", "High-performance gaming laptop with RGB keyboard",
                "Electronics", 35, 25, 3, "Black", "Aluminum",
                new BigDecimal("1299.99"), "USD", 2.5, "SKU-001", 10
        );

        smartphone = new Product(
                "PHN456", "Smartphone X", "Latest smartphone with OLED display",
                "Electronics", 15, 7, 0, "Silver", "Glass & Metal",
                new BigDecimal("899.99"), "USD", 0.4, "SKU-002", 50
        );
        products.add(laptop);
        products.add(smartphone);
    }

    @Test
    void shouldReturnAllProducts() {
        assertEquals(2, products.getProducts().size());
    }

    @Test
    void shouldAddProductSuccessfully() {
        Product tablet = new Product(
                "TAB789", "Tablet Pro", "High-performance tablet with pen support",
                "Electronics", 20, 10, 5, "Gray", "Metal",
                new BigDecimal("699.99"), "USD", 1.0, "SKU-003", 15
        );
        products.add(tablet);
        assertEquals(3, products.getProducts().size());
        assertTrue(products.getProducts().contains(tablet));
    }

    @Test
    void shouldRemoveProductByInstance() {
        products.remove(smartphone);
        assertEquals(1, products.getProducts().size());
        assertNull(products.getProduct("PHN456"));
    }

    @Test
    void shouldRemoveProductByModelNumber() {
        products.remove("PHN456");
        assertEquals(1, products.getProducts().size());
        assertNull(products.getProduct("PHN456"));
    }

    @Test
    void shouldReturnProductByModelNumber() {
        Product expected = smartphone;
        Product actual = products.getProduct("PHN456");
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateProductSuccessfully() {
        Product updatedSmartphone = new Product(
                "PHN456", "Smartphone X Pro", "Upgraded smartphone with better OLED display",
                "Electronics", 20, 10, 0, "Silver", "Glass & Metal",
                new BigDecimal("999.99"), "USD", 0.4, "SKU-002", 45
        );

        products.updateProduct(updatedSmartphone);

        Product retrievedProduct = products.getProduct("PHN456");
        assertNotNull(retrievedProduct);
        assertEquals("Smartphone X Pro", retrievedProduct.name());
        assertEquals(new BigDecimal("999.99"), retrievedProduct.price());
        assertEquals(45, retrievedProduct.stockQuantity());
    }
}
