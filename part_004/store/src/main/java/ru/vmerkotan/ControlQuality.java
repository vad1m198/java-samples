package ru.vmerkotan;

import ru.vmerkotan.food.Food;
import ru.vmerkotan.food.Vegetables;
import ru.vmerkotan.stores.FridgeStore;
import ru.vmerkotan.stores.RecycleBin;
import ru.vmerkotan.stores.Shop;
import ru.vmerkotan.stores.Store;
import ru.vmerkotan.stores.Trash;
import ru.vmerkotan.stores.Warehouse;

/**
 * {@code ControlQuality} class sorts Food
 * based on it params.
 *
 * Created by Вадим on 07.01.2017.
 */
public class ControlQuality {
    /**
     * Internal Store[].
     */
    private Store[] stores;
    /**
     * Fridge type store.
     */
    private static final String FRIDGE = "FRIDGE";
    /**
     * Trash store type.
     */
    private static final String TRASH = "TRASH";
    /**
     * Warehouse store type.
     */
    private static final String WAREHOUSE = "WAREHOUSE";
    /**
     * Shop store type.
     */
    private static final String SHOP = "SHOP";

    /**
     * RecycleBin store type.
     */
    private static final String RECYCLE = "RECYCLEBIN";

    /**
     * Constructs new ControlQuality object.
     *
     * @param stores Store[] stores
     */
    public ControlQuality(Store[] stores) {
        this.stores = stores;
    }

    /**
     * Adds Food to needed store based on it expirationDate.
     *
     * @param food Food to be added.
     */
    public void addFood(Food food) {
        long currentTime = System.currentTimeMillis();
        long createdDate = food.getCreatedDate();
        long expirationDate = food.getExpirationDate();
        if (currentTime >= expirationDate && !food.isReproduct()) {
            this.addToStore(this.TRASH, food);
        } else if (currentTime >= expirationDate && food.isReproduct()) {
            this.addToStore(this.RECYCLE, food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.75f) {
            food.setDiscount(10);
            this.addToStore(this.SHOP, food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.25f) {
            this.addToStore(this.SHOP, food);
        } else {
            if (food instanceof Vegetables) {
                this.addToStore(this.FRIDGE, food);
            } else {
                this.addToStore(this.WAREHOUSE, food);
            }
        }
    }

    /**
     * Resorts food.
     */
    public void resort() {
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

    /**
     * Adds food to proper store.
     *
     * @param type Store type.
     * @param food  Food to be added.
     */
    private void addToStore(String type, Food food) {
        boolean wasAdded = false;
        if (TRASH.equals(type)) {
            for (Store s: this.stores) {
                if (s instanceof Trash && !s.isFull()) {
                    s.addFood(food);
                    wasAdded = true;
                    break;
                }
            }
        } else if (SHOP.equals(type)) {
            for (Store s: this.stores) {
                if (s instanceof Shop && !s.isFull()) {
                    s.addFood(food);
                    wasAdded = true;
                    break;
                }
            }
        } else if (WAREHOUSE.equals(type)) {
            for (Store s: this.stores) {
                if (s instanceof Warehouse && !s.isFull()) {
                    s.addFood(food);
                    wasAdded = true;
                    break;
                }
            }
        } else if (RECYCLE.equals(type)) {
            for (Store s: this.stores) {
                if (s instanceof RecycleBin && !s.isFull()) {
                    s.addFood(food);
                    wasAdded = true;
                    break;
                }
            }
        } else if (FRIDGE.equals(type)) {
            for (Store s : this.stores) {
                if (s instanceof FridgeStore && !s.isFull()) {
                    s.addFood(food);
                    wasAdded = true;
                    break;
                }
            }
        }

        if (!wasAdded) {
            throw new RuntimeException("No free stores of type " + type + " were found");
        }
    }

}
