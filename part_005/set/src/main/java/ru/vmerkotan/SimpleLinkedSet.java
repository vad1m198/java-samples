package ru.vmerkotan;

/**
 * SimpleLinkedSet class represents a set
 * based on Linked Lists.
 *
 * @param <T> generic type.
 */
public class SimpleLinkedSet<T> implements SimpleSet<T> {
    /**
     * Link to first element.
     */
    private Node<T> first;
    /**
     * Link to the last element.
     */
    private Node<T> last;

    /**
     * Link to the current element.
     */
    private Node<T> current;

    @Override
    public void add(T t) {
        if (!isDuplicate(t)) {
            if (first == null && last == null) {
                Node node = new Node<>(null, t, null);
                last = node;
                first = node;
                current = new Node<>(null, null, first);
            } else {
                Node<T> l = last;
                last = new Node<>(l, t, null);
                l.next = last;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.current != null && this.current.next != null;
    }

    @Override
    public T next() {
        if (hasNext()) {
            this.current = this.current.next;
            return this.current.item;
        } else {
            return null;
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
    private boolean isDuplicate(T t) {
        Node tmp = first;
        while (tmp != null) {
            if (t.equals(first.item)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * Class represents Link in linkedList.
     *
     * @param <E> generic type.
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
