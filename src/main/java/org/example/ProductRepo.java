package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<Product> products = new ArrayList<>();

    public ProductRepo(){}

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void remove(String modelNumber) {
        Product toRemove = null;
        for (Product product : products) {
            if (product.modelNumber().equals(modelNumber)) {
                toRemove = product;
                break;
            }
        }
        if (toRemove != null) {
            products.remove(toRemove);
        }
    }

    public Product getProduct(String modelNumber) {
        for (Product product : products) {
            if (product.modelNumber().equals(modelNumber)) {
                return product;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "ProductRepo {" +
                "products = " + products +
                '}';
    }
}
