package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;
import ru.vmerkotan.food.Vegetables;

/**
 * {@code FridgeStore} class represents
 * store with low temperature.
 *
 * Created by Вадим on 08.01.2017.
 */
public class FridgeStore extends Store {

    /**
     * Creates new FridgeStore object.
     *
     * @param capacity Store capacty.
     */
    public FridgeStore(int capacity) {
        super(capacity);
    }

    @Override
    public boolean isAppropriate(Food food) {
        long currentTime = System.currentTimeMillis();
        long createdDate = food.getCreatedDate();
        long expirationDate = food.getExpirationDate();
        return (float) (currentTime - createdDate) / (expirationDate - createdDate) < 0.25f && (food instanceof Vegetables);
    }
}
