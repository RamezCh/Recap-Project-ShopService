package org.example;

import java.util.List;

public interface OrderRepoInterface {
    public Order getOrder(String orderId);
    public void add(Order order);
    public void remove(String orderId);
    public void remove(Order order);
}
