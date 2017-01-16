package ru.vmerkotan.stores;

import ru.vmerkotan.exceptions.StoreIsFullException;
import ru.vmerkotan.food.Food;

import java.util.Arrays;

/**
 * {@code Store} class represents
 * basic class for different kinds of stores.
 */
public abstract class Store {
    /**
     * Holds all food added to store.
     */
    private Food[] foods;
    /**
     * holds current position from internal
     * Food[].
     */
    private int position = 0;
    /**
     * store capacity. Amount of Food
     * objects that can be stored.
     */
    private int capacity;

    /**
     * Creates new Store instance.
     *
     * @param capacity store capacity.
     */
    public Store(int capacity) {
        this.capacity = capacity;
        this.foods = new Food[capacity];
    }

    /**
     * Adds food to the store.
     *
     * @param food Food to add.
     */
    public void addFood(Food food) {
        if (this.position >= this.capacity) {
            throw new StoreIsFullException("Store is full.");
        }
        this.foods[position++] = food;
    }

    /**
     * Returns all Food present in store.
     *
     * @return Food[]
     */
    public Food[] getAllFoods() {
        Food[] result = new Food[this.position];
        int counter = 0;
        for (int i = 0; i < this.foods.length; i++) {
            if (this.foods[i] != null) {
                result[counter++] = this.foods[i];
            }
        }
        return Arrays.copyOf(result, counter);
    }

    /**
     * Verifies that current store is full.
     *
     * @return true if store is full, false otherwise
     */
    public boolean isFull() {
        return this.position >= this.capacity;
    }

    /**
     * Initiates foods array with new Food[].
     */
    public void clearStore() {
        this.foods = new Food[capacity];
    }

    /**
     * Verifies if current type of food can be added.
     *
     * @param food  Food to add.
     * @return      true if food can be added, else false.
     */
    public abstract boolean isAppropriate(Food food);
}