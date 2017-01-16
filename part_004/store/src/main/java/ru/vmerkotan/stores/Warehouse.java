package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;
import ru.vmerkotan.food.Vegetables;

/**
 * {@code Warehouse} represents a simple
 * Warehouse to store Food.
 *
 * Created by Вадим on 07.01.2017.
 */
public class Warehouse extends Store {
    /**
     * Creates new Warehouse object.
     *
     * @param capacity Internal trash capacity.
     */
    public Warehouse(int capacity) {
        super(capacity);
    }

    @Override
    public boolean isAppropriate(Food food) {
        long currentTime = System.currentTimeMillis();
        long createdDate = food.getCreatedDate();
        long expirationDate = food.getExpirationDate();
        return (float) (currentTime - createdDate) / (expirationDate - createdDate) < 0.25f && !(food instanceof Vegetables);
    }
}
