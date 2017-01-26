package ru.vmerkotan;

/**
 * Simple Tree data structure.
 *
 * Created by vmerkotan on 1/26/2017.
 */
public class Tree<K extends Comparable> {

    private Entry<K> root = null;

    private int size = 0;

    void put(K value) {
        if(value == null) {
            return;
        }
        Entry<K> e = new Entry<>(value, null, null);
        if(root == null) {
            root = e;
            size++;
        } else {
            Entry<K> entry = root;
            do {
                int tmp = entry.getValue().compareTo(value);
                if(tmp == 0) {
                    break;
                } else if(tmp > 0) {
                    if(entry.right == null) {
                        entry.right = e;
                        size++;
                        break;
                    }
                    entry = entry.right;
                } else if(tmp < 0) {
                    if(entry.left == null) {
                        entry.left = e;
                        size++;
                        break;
                    }
                    entry = entry.left;
                }
            } while(true);
        }
    }

    boolean contains(K value) {
        return findEntry(value) != null;
    }

    int size() {
        return size;
    }

    private Entry<K> findEntry(K value) {
        if(value == null || root == null) {
            return null;
        }
        Entry<K> entry = root;
        do {
            int tmp = entry.getValue().compareTo(value);
            if(tmp == 0) {
                break;
            } else if(tmp > 0) {
                entry = entry.right;
            } else if(tmp < 0) {
                entry = entry.left;
            }
        } while(entry != null);
        return entry != null ? entry : null;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root.toString() +
                '}';
    }

    static final class Entry<V> {
        V value;
        Entry<V> left;
        Entry<V> right;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        Entry(V value, Entry<V> left, Entry<V> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Returns the value associated with the key.
         *
         * @return the value associated with the key
         */
        V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
