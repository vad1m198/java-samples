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
    private Node[] objects = new Node[10];
    /**
     * Current counts occupied cells number in internal objects array.
     */
    private int occupiedCellsAmount;

    /**
     * Counts size.
     */
    private int size;
    /**
     * Default load factor.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Node to hold value for nex() method.
     */
    private Node nodeToReturn;

    /**
     * increase private array size if
     * its load factor is greater then 0.75.
     */
    private void resize() {
        if ((float) occupiedCellsAmount / this.objects.length >= LOAD_FACTOR) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
        }
    }


    @Override
    public void add(T t) {
        resize();

        int cellIndexToAdd = Math.abs(t.hashCode()) % this.objects.length;

        Node node = this.objects[cellIndexToAdd];
        if (node == null) {
            this.objects[cellIndexToAdd] = new Node<>(t, null, null);
            occupiedCellsAmount++;
            size++;
        } else {
            if (node.key.equals(t)) {
                return;
            }
            while (node.next != null) {
                if (!node.key.equals(t)) {
                    node = node.next;
                } else {
                    return;
                }

            }
            node.next = new Node<>(t, null, null);
            size++;
        }
    }

    @Override
    public boolean hasNext() {
        if (nodeToReturn == null && size > 0) {
            return true;
        } else if (nodeToReturn == null && size == 0) {
            return  false;
        }

        if (nodeToReturn.next != null) {
            return true;
        } else {
            for (int i = nodeToReturn.key.hashCode(); i < this.objects.length; i++) {
                if (objects[i] != null) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public T next() {

        if (nodeToReturn == null && size > 0) {
            for (int i = 0; i < this.objects.length; i++) {
                if (this.objects[i] != null) {
                    this.nodeToReturn = this.objects[i];
                    return (T) nodeToReturn.key;
                }
            }
        } else if (nodeToReturn == null && size == 0) {
            return null;
        }

        if (nodeToReturn.next != null) {
            this.nodeToReturn = this.nodeToReturn.next;
            return (T) this.nodeToReturn.key;
        } else {
            for (int i = (Math.abs(this.nodeToReturn.key.hashCode()) % this.objects.length) + 1; i < this.objects.length; i++) {
                if (this.objects[i] != null) {
                    this.nodeToReturn = this.objects[i];
                    return (T) nodeToReturn.key;
                }
            }
        }
        return null;
    }

    /**
     * Internal class to hold links.
     *
     * @param <M>   generic type for key.
     * @param <E>   generic type for value.
     */
    private class Node<M, E> {
        /**
         * Current item.
         */
        private E item;
        /**
         * Current item key.
         */
        private M key;
        /**
         * Link to next item.
         */
        private Node<M, E> next;

        /**
         * Creates new Node instance.
         *
         * @param key       key value
         * @param element   element value
         * @param next      link to next Node
         */
        Node(M key, E element, Node<M, E> next) {
            this.item = element;
            this.key = key;
            this.next = next;
        }
    }
}
