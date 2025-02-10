package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderListRepo {
    private List<Order> orders = new ArrayList<>();

    public OrderListRepo() {}

    public List<Order> getOrders() {
        return orders;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public void remove(Order order) {
        orders.remove(order);
    }

    public void remove(String orderId) {
        Order toRemove = null;
        for (Order order : orders) {
            if (order.orderId().equals(orderId)) {
                toRemove = order;
                break;
            }
        }
        if (toRemove != null) {
            orders.remove(toRemove);
        }
    }

    public Order getOrder(String orderId) {
        for (Order order : orders) {
            if (order.orderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }

    @Override
    public String toString() {
        return "OrderListRepo {" +
                "orders = " + orders +
                '}';
    }
}
