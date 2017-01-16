package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;

/**
 * {@code RecycleBin} class represents
 * store to hold Food that can be restored.
 *
 * Created by Вадим on 08.01.2017.
 */
public class RecycleBin extends Store {
    /**
     * Creates new Store object.
     *
     * @param capacity Store capacity.
      */
    public RecycleBin(int capacity) {
        super(capacity);
    }

    @Override
    public boolean isAppropriate(Food food) {
        long currentTime = System.currentTimeMillis();
        long expirationDate = food.getExpirationDate();
        return currentTime >= expirationDate && food.isReproduct();
    }
}
