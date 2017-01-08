package ru.vmerkotan;

/**
 * {@code ItemsManager} class represents a
 * simple manager for menu items.
 */
public class ItemsManager {
    /**
     * Item[] to manage.
     */
    private Item[] items;

    /**
     * Output to write to.
     */
    private Output output;

    /**
     * Creates new ItemsManager instance.
     *
     * @param items Item[] to manage
     * @param output Output to write to.
     */
    public ItemsManager(Item[] items, Output output) {
        this.items = items;
        this.output = output;
    }

    /**
     * prints all items to output.
     */
    public void printItems() {
        printItems(this.items, 0, "-");
    }

    /**
     * Prints Items to output.
     *
     * @param items Item[].
     * @param repeat times to repeat prefix.
     * @param prefix String prefix.
     *
     */
    private void printItems(Item[] items, int repeat, String prefix) {
        for (Item i: items) {
            StringBuilder sb = new StringBuilder();
            for (int k = 1; k <= repeat; k++) {
                sb.append(prefix);
            }
            output.write(sb.toString() + i.getName());
            if (i.getChildren() != null) {
                printItems(i.getChildren(), repeat + 1, prefix);
            }
        }
    }
}