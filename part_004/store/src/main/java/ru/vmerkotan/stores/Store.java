package ru.vmerkotan.stores;

import ru.vmerkotan.food.Food;

import java.util.Arrays;

/**
 * {@code Store} class represents
 * basic class for different kinds of stores.
 */
public class Store {
    /**
     * Holds all food added to store.
     */
    private Food[] foods = new Food[0];
    /**
     * holds current position from internal
     * Food[].
     */
    private int position = 0;

    /**
     * Adds food to the store.
     *
     * @param food Food to add.
     */
    public void addFood(Food food) {
        if (food != null) {
            if (this.position == this.foods.length) {
                this.foods = Arrays.copyOf(this.foods, this.position == 0 ? 1 : this.foods.length * 2);
            }
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

}