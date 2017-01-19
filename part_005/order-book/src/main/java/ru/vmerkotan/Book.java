package ru.vmerkotan;

import ru.vmerkotan.output.Output;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Book class represent a Book in program.
 *
 * Created by vmerkotan on 1/18/2017.
 */
class Book {
    /**
     * Name field.
     */
    private final String name;
    /**
     * Map for orders with operation "SELL".
     */
    private Map<Integer, Order> sellOrders;
    /**
     * Map for orders with operation "BUY".
     */
    private Map<Integer, Order> buyOrders;

    /**
     * Creates new book instance.
     *
     * @param name  String name.
     */
    Book(String name) {
        this.name = name;
        this.sellOrders = new HashMap<>();
        this.buyOrders = new HashMap<>();
    }

    /**
     * Adds order to appropriate map
     * based on operation type.
     *
     * @param order Order to add.
     */
    void addOrder(Order order) {
        if ("SELL".equals(order.getOperation())) {
            this.sellOrders.put(order.getOrderId(), order);
        } else if ("BUY".equals(order.getOperation())) {
            this.buyOrders.put(order.getOrderId(), order);
        }
    }

    /**
     * Removes order from map.
     *
     * @param orderId   order id to remove.
     */
    void deleteOrder(int orderId) {
        this.sellOrders.remove(orderId);
        this.buyOrders.remove(orderId);
    }

    /**
     * calculates book orders and write result to Output.
     *
     * @param out   Output to write result to.
     */
    void calculateAndWrite(Output out) {
        out.write("Order book: " + this.name);

        Map<Double, Integer> buyOrdersSumOrdered = new TreeMap<>(Collections.reverseOrder());
        buyOrdersSumOrdered.putAll(
            this.buyOrders.values()
                .stream()
                .collect(
                    Collectors.groupingBy(
                        Order::getPrice,
                        Collectors.reducing(
                            0,
                            Order::getVolume,
                            Integer::sum))));

        Iterator buyIterator = buyOrdersSumOrdered.entrySet().iterator();

        Map<Double, Integer> sellOrdersSumOrdered = new TreeMap<>(this.sellOrders.values()
            .stream()
            .collect(
                Collectors.groupingBy(
                    Order::getPrice,
                    Collectors.reducing(
                        0,
                        Order::getVolume,
                        Integer::sum))));

        Iterator sellIterator = sellOrdersSumOrdered.entrySet().iterator();

        Map.Entry<Double, Integer> buyEntry = buyIterator.hasNext() ? (Map.Entry<Double, Integer>) buyIterator.next() : null;
        Map.Entry<Double, Integer> sellEntry = sellIterator.hasNext() ? (Map.Entry<Double, Integer>) sellIterator.next() : null;

        while (buyEntry != null && sellEntry != null) {

            if (buyEntry.getKey() >= sellEntry.getKey()) {
                int buyEntryVolume = buyEntry.getValue();
                int sellEntryVolume = sellEntry.getValue();
                if (buyEntryVolume > sellEntryVolume) {
                    buyEntry.setValue(buyEntryVolume - sellEntryVolume);
                    sellIterator.remove();
                    sellEntry = sellIterator.hasNext() ? (Map.Entry<Double, Integer>) sellIterator.next() : null;
                } else if (buyEntryVolume < sellEntryVolume) {
                    sellEntry.setValue(sellEntryVolume - buyEntryVolume);
                    buyIterator.remove();
                    buyEntry = buyIterator.hasNext() ? (Map.Entry<Double, Integer>) buyIterator.next() : null;
                } else {
                    buyIterator.remove();
                    sellIterator.remove();
                    buyEntry = buyIterator.hasNext() ? (Map.Entry<Double, Integer>) buyIterator.next() : null;
                    sellEntry = sellIterator.hasNext() ? (Map.Entry<Double, Integer>) sellIterator.next() : null;
                }
            } else {
                out.write(buyEntry.getValue() + "@" + buyEntry.getKey() + " --- " + sellEntry.getValue() + "@" + sellEntry.getKey());
                buyEntry = buyIterator.hasNext() ? (Map.Entry<Double, Integer>) buyIterator.next() : null;
                sellEntry = sellIterator.hasNext() ? (Map.Entry<Double, Integer>) sellIterator.next() : null;
            }
        }

        while (buyEntry != null) {
            out.write(buyEntry.getValue() + "@" + buyEntry.getKey() + " ---  ---@---");
            buyEntry = buyIterator.hasNext() ? (Map.Entry<Double, Integer>) buyIterator.next() : null;

        }

        while (sellEntry != null) {
            out.write("---@---  --- " + sellEntry.getValue() + "@" + sellEntry.getKey());
            sellEntry = sellIterator.hasNext() ? (Map.Entry<Double, Integer>) sellIterator.next() : null;

        }
    }
}
