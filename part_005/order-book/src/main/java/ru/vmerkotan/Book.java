package ru.vmerkotan;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by vmerkotan on 1/18/2017.
 */
public class Book {

    private final String name;
    private final double price;
    private Map<Integer, Order> map;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.map = new TreeMap<>();
    }

    public void addOrder(Order order) {
        this.map.put(order.getOrderId(), order);
    }

    public void deleteOrder(int orderId) {
        this.map.remove(orderId);
    }

    public int getSize() {
        return this.map.size();
    }

    public String getName() {
        return name;
    }
}
