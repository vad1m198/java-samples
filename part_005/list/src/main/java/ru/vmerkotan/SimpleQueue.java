package ru.vmerkotan;

/**
 * {@code SimpleQueue} class represents a simple
 * generic queue container.
 *
 * @param <T> generic type.
 */
public interface SimpleQueue<T> {
    /**
     * Adds new object to container.
     *
     * @param t object to add.
     */
    void push(T t);

    /**
     * Returns object placed on passed index position.
     *
     * @return  T instance.
     */
    T pop();
}
