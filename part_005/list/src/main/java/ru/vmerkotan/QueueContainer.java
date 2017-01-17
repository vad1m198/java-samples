package ru.vmerkotan;

/**
 * simple QueueContainer.
 *
 * @param <T> generic type.
 * Created by vmerkotan on 1/17/2017.
 */
public class QueueContainer<T> implements SimpleQueue<T> {
    /**
     * Internal container implementation.
     */
    private LinkedContainer<T> container = new LinkedContainer<>();

    @Override
    public void push(T t) {
        container.add(t);
    }

    @Override
    public T pop() {
        T result = container.get(0);
        container.remove(0);
        return result;
    }
}
