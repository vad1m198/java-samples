package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ItemsManager class.
 *
 * Created by Вадим on 08.01.2017.
 */
public class ItemsManagerTest {
    /**
     * Class to test output.
     */
    private class TestOutput implements Output {
        /**
         * StringBuilder to write to.
         */
        private StringBuilder sb = new StringBuilder();
        @Override
        public void write(String s) {
            sb.append(s);
        }

        /**
         * Returns result.
         * @return  result
         */
        public String getResult() {
            return this.sb.toString();
        }
    }

    /**
     * Verify printItems method.
     */
    @Test
    public void whenPassItemsThenPrintAll() {
        Item first = new Item("1", new Item[]{new Item("1.1", null), new Item("1.2", null)});
        Item second = new Item("2", new Item[]{new Item("2.1", null)});
        TestOutput output = new TestOutput();
        ItemsManager manager = new ItemsManager(new Item[]{first, second}, output);
        manager.printItems();
        assertThat(output.getResult(), is("1-1.1-1.22-2.1"));
    }

}