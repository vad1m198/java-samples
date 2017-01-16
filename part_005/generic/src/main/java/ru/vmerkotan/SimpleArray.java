package ru.vmerkotan;

import java.util.Arrays;

/**
 * {@code SimpleArray} class represents a simple
 * generic container.
 *
 * @param <T> generic type.
 */
class SimpleArray<T> {
    /**
     * Holds all container records.
     */
    private Object[] objects;
    /**
     * Current add position.
     */
    private int position;

    /**
     * Creates new SimpleArray instance.
     *
     * @param size container capacity.
     */
    SimpleArray(int size) {
        objects = new Object[size];
    }

    /**
     * Adds new object to container.
     *
     * @param value object to add.
     */
    void add(T value) {
        if (this.position >= objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length == 0 ? 1 : this.objects.length * 2);
        }
        this.objects[position++] = value;
    }

    /**
     * Returns object placed on passed index.
     *
     * @param index int needed position.
     * @return  T placed on position in container.
     */
    T get(int index) {
        return (T) this.objects[index];
    }

    /**
     * Updates value on index with passed value.
     *
     * @param index int position to update.
     * @param value value to update.
     */
    void update(int index, T value) {
        objects[index] = value;
    }

    /**
     * Removes value on index.
     *
     * @param index position to remove.
     */
    void delete(int index) {
        Object[] objects = new Object[this.objects.length - 1];
        System.arraycopy(this.objects, 0, objects, 0, index);
        System.arraycopy(this.objects, index + 1, objects, index, this.objects.length - index - 1);
        this.objects = objects;
        this.position--;
    }
}
