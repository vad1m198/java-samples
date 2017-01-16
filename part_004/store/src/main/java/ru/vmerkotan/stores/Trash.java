package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;

/**
 * {@code Trash} represents a simple
 * Trash to store Food.
 *
 * Created by Вадим on 07.01.2017.
 */
public class Trash extends Store {
    /**
     * Creates new Trash instance.
     *
     * @param capacity Internal Trash capacity.
     */
    public Trash(int capacity) {
        super(capacity);
    }

    @Override
    public boolean isAppropriate(Food food) {
        long currentTime = System.currentTimeMillis();
        long expirationDate = food.getExpirationDate();
        if (currentTime >= expirationDate && !food.isReproduct()) {
            return true;
        }
        return false;
    }
}
