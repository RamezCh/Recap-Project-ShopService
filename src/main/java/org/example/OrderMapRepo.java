package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderMapRepo implements OrderRepoInterface {
    private Map<String,Order> orders = new HashMap<>();

    public Map<String, Order> getOrders() {
        return orders;
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public void add(Order order){
        orders.put(order.orderId(), order);
    }

    @Override
    public void remove(String orderId) {
        orders.remove(orderId);
    }

    @Override
    public void remove(Order order) {
        orders.remove(order.orderId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapRepo that = (OrderMapRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }

    @Override
    public String toString() {
        return "OrderMapRepo {" +
                "orders = " + orders +
                '}';
    }
}
