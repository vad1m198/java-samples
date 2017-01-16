package ru.vmerkotan;

/**
 * {@code Item} class represents a menu item.
 *
 * Created by Вадим on 08.01.2017.
 */
public class Item implements Action {
    /**
     * Item name.
     */
    private String name;
    /**
     * Children items.
     */
    private Item[] children;
    /**
     * Console output to write to.
     */
    private ConsoleOutput output = new ConsoleOutput();

    /**
     * Creates new Item instance.
     *
     * @param name      Item name.
     * @param children  Item[] children
     */
    public Item(String name, Item[] children) {
        this.name = name;
        this.children = children;
    }

    /**
     * Returns children of current Item.
     *
     * @return Item[]
     */
    public Item[] getChildren() {
        return children;
    }

    /**
     * Returns Item name.
     *
     * @return  name.
     */
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        output.write("executing... " + this.name);
    }
}
