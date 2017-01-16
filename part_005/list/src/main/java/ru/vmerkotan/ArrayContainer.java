package ru.vmerkotan;

import java.util.Arrays;

/**
 * simple Container based on arrays.
 *
 * @param <T> generic type.
 * Created by vmerkotan on 1/16/2017.
 */
public class ArrayContainer<T> implements SimpleContainer<T> {
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
    public ArrayContainer(int size) {
        objects = new Object[size];
    }

    @Override
    public void add(T value) {
        if (this.position >= objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length == 0 ? 1 : this.objects.length * 2);
        }
        this.objects[position++] = value;
    }

    @Override
    public T get(int index) {
        return (T) this.objects[index];
    }
}
