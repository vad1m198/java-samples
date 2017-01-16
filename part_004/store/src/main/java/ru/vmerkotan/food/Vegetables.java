package ru.vmerkotan.food;

/**
 * {@code Vegetables} class represents food
 * to be stored in fridge.
 *
 * Created by Вадим on 08.01.2017.
 */
public class Vegetables extends Food {
    /**
     * Creates new Food instance.
     *
     * @param name           Food name
     * @param expirationDate Expiration date
     * @param createdDate    Created date
     * @param price          Price
     * @param discount       Discount
     * @param canReproduct   is Food can be recycled
     */
    public Vegetables(String name, long expirationDate, long createdDate, long price, long discount, boolean canReproduct) {
        super(name, expirationDate, createdDate, price, discount, canReproduct);
    }
}
