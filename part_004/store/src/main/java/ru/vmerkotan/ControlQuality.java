package ru.vmerkotan;

import ru.vmerkotan.food.Food;
import ru.vmerkotan.stores.Shop;
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
     * private Warehouse to store food.
     */
    private Warehouse warehouse;
    /**
     * private Shop to store food.
     */
    private Shop shop;
    /**
     * private Trash to store food.
     */
    private Trash trash;

    /**
     * Constructs new ControlQuality object.
     *
     * @param warehouse internal Warehouse instance
     * @param shop      internal Shop instance
     * @param trash     internal Trash instance
     */
    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
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
        if (currentTime >= expirationDate) {
            this.trash.addFood(food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.75f) {
            food.setDiscount(10);
            this.shop.addFood(food);
        } else if ((float) (currentTime - createdDate) / (expirationDate - createdDate) >= 0.25f) {
            this.shop.addFood(food);
        } else {
            this.warehouse.addFood(food);
        }
    }
}
