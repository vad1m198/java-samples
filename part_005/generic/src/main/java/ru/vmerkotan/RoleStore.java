package ru.vmerkotan;

/**
 * Role store class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class RoleStore implements Store<Role> {
    /**
     * Container to hold values.
     */
    private SimpleArray<Role> container;

    /**
     * Creates new RoleStore.
     *
     * @param capacity internal Store default capacity.
     */
    public RoleStore(int capacity) {
        container = new SimpleArray<>(capacity);
    }

    @Override
    public void add(Role value) {
        this.container.add(value);
    }

    @Override
    public Role get(int index) {
        return this.container.get(index);
    }

    @Override
    public void put(int index, Role value) {
        this.container.update(index, value);
    }

    @Override
    public void delete(int index) {
        this.container.delete(index);
    }
}
