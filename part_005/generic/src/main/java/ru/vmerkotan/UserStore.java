package ru.vmerkotan;

/**
 * UserStore class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class UserStore implements Store<User> {
    /**
     * Container to hold values.
     */
    private SimpleArray<User> container;

    /**
     * Creates new UserStore.
     *
     * @param capacity internal Store default capacity.
     */
    public UserStore(int capacity) {
        container = new SimpleArray<>(capacity);
    }

    @Override
    public void add(User value) {
        this.container.add(value);
    }

    @Override
    public User get(int index) {
        return this.container.get(index);
    }

    @Override
    public void put(int index, User value) {
        this.container.update(index, value);
    }

    @Override
    public void delete(int index) {
        this.container.delete(index);
    }
}
