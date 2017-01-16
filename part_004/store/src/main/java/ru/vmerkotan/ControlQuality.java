package ru.vmerkotan;

import ru.vmerkotan.food.Food;
import ru.vmerkotan.food.Vegetables;
import ru.vmerkotan.stores.Store;
import ru.vmerkotan.stores.Types;

import java.util.Map;

/**
 * {@code ControlQuality} class sorts Food
 * based on it params.
 *
 * Created by Вадим on 07.01.2017.
 */
class ControlQuality {
    /**
     * Internal Store[].
     */
    private Store[] stores;

    /**
     * Creates new ControlQuality object.
     *
     * @param stores Store[]
     */
    ControlQuality(Store[] stores) {
        this.stores = stores;
    }

    /**
     * Adds Food to needed store based on it expirationDate.
     *
     * @param food Food to be added.
     */
    void addFood(Food food) {
        boolean wasAdded = false;
        for (Store s: this.stores) {
            if (s.isAppropriate(food) && !s.isFull()) {
                s.addFood(food);
                wasAdded = true;
                break;
            }
        }
        if (!wasAdded) {
            throw new RuntimeException("All stores are full");
        }
    }

    /**
     * Resorts food.
     */
    void resort() {
        Food[] result = new Food[0];
            for (Store s: this.stores) {
                Food[] storeFood = s.getAllFoods();
                s.clearStore();
                Food[] tmp = new Food[result.length + storeFood.length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                System.arraycopy(storeFood, 0, tmp, result.length, storeFood.length);
                result = tmp;
            }

        for (Food f: result) {
            this.addFood(f);
        }
    }
}
