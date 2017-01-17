package ru.vmerkotan;

/**
 * simple StackContainer.
 *
 * @param <T> generic type.
 * Created by vmerkotan on 1/17/2017.
 */
public class StackContainer<T> implements SimpleQueue<T> {
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
        T result = container.get(container.size() - 1);
        container.remove(container.size() - 1);
        return result;
    }
}
