package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.exceptions.StoreIsFullException;
import ru.vmerkotan.food.Food;
import ru.vmerkotan.food.Vegetables;
import ru.vmerkotan.stores.FridgeStore;
import ru.vmerkotan.stores.RecycleBin;
import ru.vmerkotan.stores.Shop;
import ru.vmerkotan.stores.Store;
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
        Trash t = new Trash(10);
        Store[] arr = new Store[]{t};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food trashFood = new Food("trashFood", currentTime - 10, currentTime - 50, 100, 0, false);
        cq.addFood(trashFood);

        assertThat(t.getAllFoods().length, is(1));
    }

    /**
     * verify add method when expiration is more then current date
     * and isReproduct = true add to recycleBin store.
     */
    @Test
    public void whenCanReproductThenAddToRecycleStore() {
        RecycleBin r = new RecycleBin(10);
        Store[] arr = new Store[]{r};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food recycleFood = new Food("recycleFood", currentTime - 10, currentTime - 50, 100, 0, true);
        cq.addFood(recycleFood);

        assertThat(r.getAllFoods().length, is(1));
    }

    /**
     * If left only 25% of expiration then
     * add to shop with discount.
     */
    @Test
    public void whenAddShopFoodWithDiscountThenAddToShopStore() {
        Shop s = new Shop(10);
        Store[] arr = new Store[]{s};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food shopFoodWithDiscount = new Food("shopFoodWithDiscount", currentTime + 1000, currentTime - 3500, 100, 0, false);

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
        Shop s = new Shop(10);
        Store[] arr = new Store[]{s};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food shopFoodWithoutDiscount = new Food("shopFoodWithoutDiscount", currentTime + 3000, currentTime - 1500, 100, 0, false);
        cq.addFood(shopFoodWithoutDiscount);

        assertThat(s.getAllFoods().length, is(1));
    }
    /**
     * If left less then 25% of expiration then
     * add to warehouse without discount.
     */
    @Test
    public void whenAddWarehouseFoodThenAddToWarehouseStore() {
        Warehouse w = new Warehouse(10);
        Store[] arr = new Store[]{w};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food warehouseFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        cq.addFood(warehouseFood);

        assertThat(w.getAllFoods().length, is(1));
    }

    /**
     * If left less then 25% of expiration and food is vegetable then
     * add to fridge without discount.
     */
    @Test
    public void whenAddVegetableThenAddToFridge() {
        FridgeStore f = new FridgeStore(10);
        Store[] arr = new Store[]{f};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Vegetables vegetable = new Vegetables("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        cq.addFood(vegetable);

        assertThat(f.getAllFoods().length, is(1));
    }

    /**
     * If no stores were found then throw.
     */
    @Test(expected = RuntimeException.class)
    public void whenNoProperStpresThenThrow() {
        Warehouse w = new Warehouse(1);
        Store[] arr = new Store[]{w};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food firstFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        Food secondFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        cq.addFood(firstFood);
        cq.addFood(secondFood);

    }

    /**
     * If left less then 25% of expiration then
     * add to warehouse without discount.
     * If warehouse is full then add to the second Warehouse.
     */
    @Test
    public void whenWarehouseIsFullThenAddToSecondWarehouse() {
        Warehouse firstWarehouse = new Warehouse(1);
        Warehouse secondWarehouse = new Warehouse(1);
        Store[] arr = new Store[]{firstWarehouse, secondWarehouse};
        ControlQuality cq = new ControlQuality(arr);
        long currentTime = System.currentTimeMillis();

        Food firstFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        Food secondFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);
        cq.addFood(firstFood);
        cq.addFood(secondFood);

        assertThat(firstWarehouse.getAllFoods().length, is(1));
        assertThat(secondWarehouse.getAllFoods().length, is(1));
    }

    /**
     * When add more the capacity then throw.
     */
    @Test(expected = StoreIsFullException.class)
    public void whenAddMoreCapacityThenThrow() {
        Warehouse firstWarehouse = new Warehouse(1);
        long currentTime = System.currentTimeMillis();
        Food firstFood = new Food("warehouseFood", currentTime + 4000, currentTime - 1, 100, 0, false);

        firstWarehouse.addFood(firstFood);
        firstWarehouse.addFood(firstFood);
    }
}