package ru.vmerkotan;

import java.util.Arrays;

/**
 * SimpleArraySet class is a simple set implementation
 * based on arrays.
 *
 * @param <T> generic type.
 */
public class SimpleArraySet<T> implements SimpleSet<T> {
    /**
     * Array to hold container items.
     */
    private Object[] objects = new Object[10];
    /**
     * Current positionToAdd in objects array.
     */
    private int positionToAdd;

    /**
     * Current next position in objects array.
     */
    private int positionNext;

    @Override
    public void add(T t) {
        if (!verifyDuplicates(t)) {
            if (this.positionToAdd == this.objects.length) {
                objects = Arrays.copyOf(objects, objects.length * 2);
            }
            objects[this.positionToAdd++] = t;
        }
    }

    /**
     * Verifies that passed argument not equal
     * to at least one argument from array.
     *
     * @param t Param to verify.
     *
     * @return  true is some element from internal array
     *          is equal to passed param.
     */
    private boolean verifyDuplicates(T t) {
        for (Object o: this.objects) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        return this.positionNext < this.positionToAdd;
    }

    @Override
    public T next() {
        return (T) objects[positionNext++];
    }
}
