package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.food.Food;
import ru.vmerkotan.stores.Shop;
import ru.vmerkotan.stores.Trash;
import ru.vmerkotan.stores.Warehouse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * {@code ControlQualityTest} class contains
 * tests for ControlQuality class.
 *
 * Created by Вадим on 07.01.2017.
 */
public class ControlQualityTest {
    /**
     * verify add expiration is more then current
     * date add to trash store.
     */
    @Test
    public void whenAddTrashFoodThenAddToTrashStore() {
        Trash t = new Trash();
        ControlQuality cq = new ControlQuality(null, null, t);
        long currentTime = System.currentTimeMillis();

        Food trashFood = new Food("trashFood", currentTime - 10, currentTime - 50, 100, 0);
        cq.addFood(trashFood);

        assertThat(t.getAllFoods().length, is(1));
    }

    /**
     * If left only 25% of expiration then
     * add to shop with discount.
     */
    @Test
    public void whenAddShopFoodWithDiscountThenAddToShopStore() {
        Shop s = new Shop();
        ControlQuality cq = new ControlQuality(null, s, null);
        long currentTime = System.currentTimeMillis();

        Food shopFoodWithDiscount = new Food("shopFoodWithDiscount", currentTime + 1000, currentTime - 3500, 100, 0);

        cq.addFood(shopFoodWithDiscount);
        assertThat(s.getAllFoods().length, is(1));
        assertThat(s.getAllFoods()[0].getDiscount(), is(10L));
    }

    /**
     * If left more then 25% and less then 75% of expiration then
     * add to shop without discount.
     */
    @Test
    public void whenAddShopFoodWithoutDiscountThenAddToShopStore() {
        Shop s = new Shop();
        ControlQuality cq = new ControlQuality(null, s, null);
        long currentTime = System.currentTimeMillis();

        Food shopFoodWithoutDiscount = new Food("shopFoodWithoutDiscount", currentTime + 3000, currentTime - 1500, 100, 0);
        cq.addFood(shopFoodWithoutDiscount);

        assertThat(s.getAllFoods().length, is(1));
    }
    /**
     * If left less then 25% of expiration then
     * add to warehouse without discount.
     */
    @Test
    public void whenAddWarehouseFoodThenAddToWarehouseStore() {
        Warehouse w = new Warehouse();
        ControlQuality cq = new ControlQuality(w, null, null);
        long currentTime = System.currentTimeMillis();

        Food warehouseFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0);
        cq.addFood(warehouseFood);

        assertThat(w.getAllFoods().length, is(1));
    }
}