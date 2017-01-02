package ru.vmerkotan;

import java.util.Iterator;

/**
 * An iterator for lists of Iterators.
 * Created by Вадим on 02.01.2017.
 *
 * @param <T> generic type to work with.
 */
public interface IConvertIterator<T> {
    /**
     * Converts {@code Iterator<Iterator<T>>} to {@code Iterator<T>}.
     * @param it {@code Iterator<Iterator<T>>} to convert
     * @return converted {@code Iterator<T>}
     */
    Iterator<T> convert(Iterator<Iterator<T>> it);
}
