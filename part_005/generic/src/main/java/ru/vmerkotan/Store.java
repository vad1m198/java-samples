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
     * Returns value from index position.
     *
     * @param index position to get value from.
     * @return T value with position index.
     */
    T get(int index);

    /**
     * Updates value on the index position with passed value.
     *
     * @param index int position to update value.
     * @param value T instance to put.
     */
    void put(int index, T value);

    /**
     * Removes value from index position.
     *
     * @param index int position to remove value from.
     */
    void delete(int index);

}
