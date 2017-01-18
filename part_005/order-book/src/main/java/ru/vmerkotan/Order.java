package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/18/2017.
 */
public class Order {

    private final int orderId;
    private final String operation;
    private final int volume;

    public Order(int orderId, String operation, int volume) {
        this.orderId = orderId;
        this.operation = operation;
        this.volume = volume;
    }


    public int getOrderId() {
        return orderId;
    }

    public String getOperation() {
        return operation;
    }

    public int getVolume() {
        return volume;
    }



}
