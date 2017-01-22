package ru.vmerkotan;

import java.util.Iterator;

/**
 * EvenIterator class represents iterator of event indexes.
 * Created by Вадим on 01.01.2017.
 */
public class EvenIterator implements Iterator<Integer> {
    /**
     * holds elements to iterate.
     */
    private final Integer[] array;
    /**
     * Holds position of next element to return.
     */
    private int position = -1;

    /**
     * Creates new EvenIterator object.
     *
     * @param array Integer[] elements to iterate.
     */
    public EvenIterator(final Integer[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        for (int i = position + 1; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        for (int i = position + 1; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                this.position = i;
                return this.array[i];
            }
        }
        return null;
    }
}
