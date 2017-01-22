package ru.vmerkotan;

/**
 * Simple Store interface.
 *
 * @param <T> Generic type
 */
public interface Store<T extends Base> {
    /**
     * Adds new object to the Store.
     *
     * @param value T object to add.
     */
    void add(T value);

    /**
     * Returns value by id.
     *
     * @param id Object idintificagtor.
     * @return T value with position index.
     */
    T get(String id);

    /**
     * Updates value on the index position with passed value.
     *
     * @param id String id to update.
     * @param value T instance to put.
     */
    void put(String id, T value);

    /**
     * Removes value by id.
     *
     * @param id String object id to delete.
     */
    void delete(String id);

}
