package ru.vmerkotan;

/**
 * {@code SimpleContainer} class represents a simple
 * generic container.
 *
 * @param <T> generic type.
 */
interface SimpleContainer<T> {
    /**
     * Adds new object to container.
     *
     * @param t object to add.
     */
    void add(T t);

    /**
     * Returns object placed on passed index position.
     *
     * @param index int needed position.
     * @return  T placed on position in container.
     */
    T get(int index);
}
