package ru.vmerkotan;

import java.util.Iterator;

/**
 *
 * {@code ConvertIterator} class presents Iterator to work
 * with {@code Iterator<Iterator<T>>}
 *
 * Created by Вадим on 02.01.2017.
 * @param <T> generic type
 */
public class ConvertIterator<T> implements IConvertIterator<T> {
    /**
     * Returns new instance of inner Iter class.
     *
     * @param it {@code Iterator<Iterator<T>>} to convert
     * @return instance of Iter class.
     */
    @Override
    public Iterator<T> convert(Iterator<Iterator<T>> it) {
        return new Iter<T>(it);
    }

    /**
     * {@code Iter} class represents a class to work
     * with {@code Iterator<Iterator<T>>}.
     *
     * @param <T> generic code
     */
    private class Iter<T> implements Iterator<T> {
        /**
         * Holds inner {@code Iterator<Iterator<T>>}.
         */
        private final Iterator<Iterator<T>> it;
        /**
         * Holds current {@code Iterator<T>} to work with.
         */
        private Iterator<T> current;

        /**
         * Constructs new Iter instance.
         *
         * @param it {@code Iterator<Iterator<T>>} to work with
         */
        Iter(Iterator<Iterator<T>> it) {
            this.it = it;
            if (this.it.hasNext()) {
                current = this.it.next();
            }
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
            boolean result = false;
            if (this.current.hasNext()) {
                result = true;
            } else {
                while (it.hasNext()) {
                    this.current = it.next();
                    if (this.current != null && this.current.hasNext()) {
                        result = true;
                        break;
                    }
                }
            }
            return result;
        }
        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            T result = null;
            if (hasNext()) {
                result = this.current.next();
            }
            return result;
        }
    }
}
