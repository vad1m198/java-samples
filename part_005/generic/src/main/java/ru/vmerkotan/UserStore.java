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
    public User get(String id) {
        int index = getIndex(id);
        if (index != -1) {
            return this.container.get(index);
        }
        return null;
    }

    @Override
    public void put(String id, User value) {
        int index = getIndex(id);
        if (index != -1) {
            this.container.update(index, value);
        }
    }

    @Override
    public void delete(String id) {
        int index = getIndex(id);
        if (index != -1) {
            this.container.delete(index);
        }
    }

    /**
     * Returns object index based on passed id.
     *
     * @param id    String id.
     * @return      int index.
     */
    private int getIndex(String id) {
        for (int i = 0; i < this.container.size(); i++) {
            if (id.equals(this.container.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}
