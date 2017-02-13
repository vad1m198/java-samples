package ru.vmerkotan;

/**
 * Simple Linked container.
 *
 * @param <T> generic type.
 * Created by vmerkotan on 1/16/2017.
 */
public class LinkedContainer<T> implements SimpleContainer<T> {
    /**
     * First link.
     */
    private Node<T> first;
    /**
     * Last Link.
     */
    private Node<T> last;
    /**
     * holds container size.
     */
    private int size;

    @Override
    public void add(T t) {
        if (first == null && last == null) {
            Node node = new Node<>(null, t, null);
            last = node;
            first = node;
        } else {
            Node<T> l = last;
            last = new Node<>(l, t, null);
            l.next = last;
        }
        size++;
    }

    @Override
    public T get(int index) {
        T result = null;
        Node<T> current = first;
        int counter = 0;
        while (current != null) {
            if (counter == index) {
                result = current.item;
                break;
            }
            current = current.next;
            counter++;
        }
        return result;
    }

    /**
     * Removes element by index from the container.
     *
     * @param index int index to remove.
     */
    public void remove(int index) {
        Node<T> current = first;
        int counter = 0;
        while (current != null) {
            if (counter == index) {
                Node prev = current.prev;
                Node next = current.next;
                if (prev == null && next == null) {
                    this.first = null;
                    this.last = null;
                } else if (next == null) {
                    prev.next = null;
                    this.last = prev;
                } else if (prev == null) {
                    next.prev = null;
                    this.first = next;
                } else {
                    prev.next = next;
                    next.prev = prev;
                }

                break;
            }
            current = current.next;
            counter++;
        }
        size--;
    }

    /**
     * Returns size of the container.
     *
     * @return int size of container.
     */
    public int size() {
        return this.size;
    }

    /**
     * Reverts container order
     */
    public void revert() {
        Node<T> tmpFirst = first;
        Node<T> tmpLast = last;

        Node<T> current = first;
        while(current != null) {
            Node<T> tmp = current.next;
            current.next = current.prev;
            current.prev = tmp;
            current = tmp;
        }

        first = tmpLast;
        last = tmpFirst;

    }

    /**
     * Internal class to holds links.
     *
     * @param <E> generic type
     */
    private class Node<E> {
        /**
         * Current item.
         */
        private E item;
        /**
         * Link to next item.
         */
        private Node<E> next;
        /**
         * Link to previous item.
         */
        private Node<E> prev;

        /**
         * Creates new Node instance.
         *
         * @param prev      Node previous item.
         * @param element   Node current item.
         * @param next      Node next item.
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
