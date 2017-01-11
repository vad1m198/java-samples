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
    private Map<Types, Store[]> mapTypeToStores;

    /**
     * Constructs new ControlQuality object.
     *
     * @param mapTypeToStores Map<String, Store[]> stores. Map Store type to Stores[].
     */
    ControlQuality(Map<Types, Store[]> mapTypeToStores) {
        this.mapTypeToStores = mapTypeToStores;
    }

    /**
     * Adds Food to needed store based on it expirationDate.
     *
     * @param food Food to be added.
     */
    void addFood(Food food) {
        long currentTime = System.currentTimeMillis();
        long createdDate = food.getCreatedDate();
        long expirationDate = food.getExpirationDate();
        if (currentTime >= expirationDate && !food.isReproduct()) {
            this.addToStore(Types.TRASH, food);
        } else if (currentTime >= expirationDate && food.isReproduct()) {
            this.addToStore(Types.RECYCLE, food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.75f) {
            food.setDiscount(10);
            this.addToStore(Types.SHOP, food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.25f) {
            this.addToStore(Types.SHOP, food);
        } else {
            if (food instanceof Vegetables) {
                this.addToStore(Types.FRIDGE, food);
            } else {
                this.addToStore(Types.WAREHOUSE, food);
            }
        }
    }

    /**
     * Resorts food.
     */
    void resort() {
        Food[] result = new Food[0];
        for (Map.Entry<Types, Store[]> entry: this.mapTypeToStores.entrySet()) {
            for (Store s: entry.getValue()) {
                Food[] storeFood = s.getAllFoods();
                s.clearStore();
                Food[] tmp = new Food[result.length + storeFood.length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                System.arraycopy(storeFood, 0, tmp, result.length, storeFood.length);
                result = tmp;
            }
        }

        for (Food f: result) {
            this.addFood(f);
        }
    }

    /**
     * Adds food to proper store.
     *
     * @param type Store type.
     * @param food  Food to be added.
     */
    private void addToStore(Types type, Food food) {
        Store[] stores = this.mapTypeToStores.get(type);
        boolean wasAdded = false;
        if (stores == null) {
            throw new RuntimeException("No stores of type " + type + " were found");
        }

        for (Store s: stores) {
            if (!s.isFull()) {
                s.addFood(food);
                wasAdded = true;
                break;
            }
        }

        if (!wasAdded) {
            throw new RuntimeException("All stores of type " + type + " are full");
        }
    }

}
