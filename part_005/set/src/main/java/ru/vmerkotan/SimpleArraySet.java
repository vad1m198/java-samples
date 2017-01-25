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
        boolean duplicateFound = false;
        for(int i = 0; i < positionToAdd; i++) {
            if(objects[i].equals(t)) {
                duplicateFound = true;
            }
        }

        if(!duplicateFound) {
            if (this.positionToAdd == this.objects.length) {
                this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
            }
            objects[this.positionToAdd++] = t;
        }
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
