package ru.vmerkotan.food;

/**
 * {@code Food} class represents
 * abstract food from real world.
 *
 * Created by Вадим on 07.01.2017.
 */
public class Food {
    /**
     * Food name.
     */
    private String name;
    /**
     * Food expiration date.
     */
    private long expirationDate;
    /**
     * Food created date.
     */
    private long createdDate;
    /**
     * Food price.
     */
    private long price;
    /**
     * Food discount.
     */
    private long discount;
    /**
     * Marks that food can be renewed.
     */
    private boolean canReproduct;

    /**
     * Sets discount value.
     *
     * @param discount discount for food.
     */
    public void setDiscount(long discount) {
        this.discount = discount;
    }

    /**
     * Creates new Food instance.
     * @param name              Food name
     * @param expirationDate    Expiration date
     * @param createdDate       Created date
     * @param price             Price
     * @param discount          Discount
     * @param canReproduct      is Food can be recycled
     */
    public Food(String name, long expirationDate, long createdDate, long price, long discount, boolean canReproduct) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.createdDate = createdDate;
        this.price = price;
        this.discount = discount;
        this.canReproduct = canReproduct;
    }

    /**
     * Returns food name.
     *
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns food expirationDate.
     *
     * @return expirationDate.
     */
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * Returns Food createdDate.
     *
     * @return  createdDate
     */
    public long getCreatedDate() {
        return createdDate;
    }

    /**
     * Returns food price.
     *
     * @return price.
     */
    public long getPrice() {
        return price;
    }

    /**
     * Returns food discount.
     *
     * @return  discount.
     */
    public long getDiscount() {
        return discount;
    }

    /**
     * Returns true is food can be reproduct.
     *
     * @return true if canReproduct true.
     */
    public boolean isReproduct() {
        return canReproduct;
    }
}
