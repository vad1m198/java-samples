package ru.vmerkotan;

/**
 * Order class represents Order in the system.
 *
 * Created by vmerkotan on 1/18/2017.
 */
class Order {
    /**
     * Order id.
     */
    private final int orderId;
    /**
     * Order operation type.
     */
    private final String operation;
    /**
     * Order volume.
     */
    private final int volume;
    /**
     * Order price.
     */
    private final double price;

    /**
     * Creates new Order instance.
     *
     * @param orderId   int order id.
     * @param operation String operation.
     * @param volume    int volume.
     * @param price     double order price.
     */
    Order(int orderId, String operation, int volume, double price) {
        this.orderId = orderId;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
    }

    /**
     * Returns order id.
     * @return  orderId value.
     */
    int getOrderId() {
        return orderId;
    }

    /**
     * Returns order operation type.
     *
     * @return  order operation type.
     */
    String getOperation() {
        return operation;
    }

    /**
     * Returns operation volume.
     *
     * @return  order operation volume.
     */
    int getVolume() {
        return volume;
    }

    /**
     * Returns order price.
     *
     * @return  order price value.
     */
    double getPrice() {
        return price;
    }
}
