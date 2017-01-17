package ru.vmerkotan;

import java.util.Iterator;

/**
 * SimpleSet interface represents a simple set.
 *
 * @param <T> generic type.
 */
public interface SimpleSet<T> extends Iterator<T> {
    /**
     * adds new object to collection.
     *
     * @param t T obejct to add.
     */
    void add(T t);

}
