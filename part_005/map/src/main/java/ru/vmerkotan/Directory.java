package ru.vmerkotan;

import java.util.Arrays;
import java.util.Iterator;

/**
 * SimpleArraySet class is a simple set implementation
 * based on arrays.
 *
 * @param <K> generic type.
 * @param <V> generic type.
 */
public class Directory<K, V> implements Iterator<Directory.DirEntry<K, V>> {
    /**
     * Array to hold container items.
     */
    private Node<K, V>[] objects = new Node[16];

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
    private Node<K, V> nodeToReturn;

    /**
     * increase private array size if
     * its load factor is greater then 0.75.
     */
    private void resize() {
        if ((float) this.objects.length / size <= LOAD_FACTOR) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
        }
    }

    /**
     * Inserts new value by key.
     *
     * @param key       key value.
     * @param value     value.
     * @return          true if value was added. If value was overridden return false.
     */
    public boolean insert(K key, V value) {
        resize();
        int cellIndexToAdd = Math.abs(key.hashCode()) % this.objects.length;
        Node<K, V> node = this.objects[cellIndexToAdd];

        if (node == null) {
            this.objects[cellIndexToAdd] = new Node<>(key, value, null);
            size++;
            return true;
        } else {
            Node<K, V> prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return false;
                }
                prev = node;
                node = node.next;
            }
            prev.next = new Node<>(key, value, null);
            size++;
            return true;
        }
    }

    /**
     * Returns value by passed key.
     *
     * @param key   Key to look for.
     * @return      value.
     */
    V get(K key) {
        int cellToSearch = Math.abs(key.hashCode()) % this.objects.length;
        Node<K, V> node = this.objects[cellToSearch];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node  = node.next;
            }
        return null;
    }

    /**
     * Removes value from the map.
     *
     * @param key   Key to search value for.
     * @return      true if value was founded else false.
     */
    boolean delete(K key) {
        int cellToSearch = Math.abs(key.hashCode()) % this.objects.length;

        Node<K, V> node = this.objects[cellToSearch];
        Node<K, V> prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    if (prev == null) {
                        this.objects[cellToSearch] = null;
                    } else {
                        prev.next = node.next;
                    }
                    size--;
                    return true;
                }
                prev = node;
                node = node.next;
            }
        return false;
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
    public Directory.DirEntry<K, V> next() {

        if (nodeToReturn == null && size > 0) {
            for (int i = 0; i < this.objects.length; i++) {
                if (this.objects[i] != null) {
                    this.nodeToReturn = this.objects[i];
                    return nodeToReturn;
                }
            }
        } else if (nodeToReturn == null && size == 0) {
            return null;
        }

        if (nodeToReturn.next != null) {
            this.nodeToReturn = this.nodeToReturn.next;
            return this.nodeToReturn;
        } else {
            for (int i = (Math.abs(this.nodeToReturn.key.hashCode()) % this.objects.length) + 1; i < this.objects.length; i++) {
                if (this.objects[i] != null) {
                    this.nodeToReturn = this.objects[i];
                    return nodeToReturn;
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
    private class Node<M, E> implements DirEntry<M, E> {
        /**
         * Current item.
         */
        private E value;
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
            this.value = element;
            this.key = key;
            this.next = next;
        }

        @Override
        public M getKey() {
            return this.key;
        }

        @Override
        public E getValue() {
            return this.value;
        }
    }


    /**
     * Interface to represents entry in key value structures.
     *
     * @param <K>   key generic type.
     * @param <V>   value generic type.
     */
    interface DirEntry<K, V> {
        /**
         * Returns key.
         *
         * @return  key value.
         */
        K getKey();

        /**
         * Returns value.
         *
         * @return  value cost.
         */
        V getValue();
    }


}
