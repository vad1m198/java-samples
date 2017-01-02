package ru.vmerkotan;

import java.util.Iterator;

/**
 * EvenIterator class represents iterator of event indexes.
 * Created by Вадим on 01.01.2017.
 *
 * @param <T> Type Generic.
 */
public class EvenIterator<T> implements Iterator<T> {
    /**
     * holds elements to iterate.
     */
    private final T[] array;
    /**
     * current position.
     */
    private int index = 0;

    /**
     * Creates new EvenIterator object.
     *
     * @param array T[] elements to iterate.
     */
    public EvenIterator(final T[] array) {
        this.array = array;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return index < this.array.length;
    }
    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public T next() {
        T result = this.array[index];
        index += 2;
        return result;
    }
}
