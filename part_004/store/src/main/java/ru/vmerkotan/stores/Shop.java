package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;

/**
 * {@code Shop} represents a simple
 * Shop to store Food.
 *
 * Created by Вадим on 07.01.2017.
 */
public class Shop extends Store {
    /**
     * Creates new Shop instance.
     *
     * @param capacity Internal trash capacity.
     */
    public Shop(int capacity) {
        super(capacity);
    }

    @Override
    public boolean isAppropriate(Food food) {
        long currentTime = System.currentTimeMillis();
        long createdDate = food.getCreatedDate();
        long expirationDate = food.getExpirationDate();
        if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.75f) {
            food.setDiscount(10);
            return true;
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.25f) {
            return true;
        }
        return false;
    }
}
