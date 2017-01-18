package ru.vmerkotan;


import java.util.Arrays;
import java.util.Iterator;

/**
 * Directory class represent a simple key-value data structure.
 *
 * @param <K>   Generic key type.
 * @param <V>   Generic value type.
 */
public class Directory<K, V> implements Iterator<Directory.DirEntry<K, V>> {
    /**
     * Holds all added objects.
     */
    private Entry<K, V>[] arr = new Entry[10];
    /**
     * Current pointer to return.
     */
    private Entry<K, V> current;
    /**
     * Next pointer to return.
     */
    private Entry<K, V> next;
    /**
     * Counts iterators.
     */
    private int counter;
    /**
     * size of the container.
     */
    private int size;
    /**
     * Load factor.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * How many cells are occupied in arr array.
     */
    private int occupiedCells;

    @Override
    public boolean hasNext() {
        if (current == null && size > 0) {
            for (int i = 0; i < this.arr.length; i++) {
                if (this.arr[i] != null) {
                    next = this.arr[i];
                    counter = i;
                    break;
                }
            }
        }
        return next != null;
    }

    @Override
    public Directory.DirEntry<K, V> next() {
        if (hasNext()) {
            Entry<K, V> toReturn = next;
            current = next;
            if (current == null) {
                for (int i = counter; i < this.arr.length; i++) {
                    if (this.arr[i] != null) {
                        next = this.arr[i];
                        counter = i;
                        break;
                    }
                }
            }
            return toReturn;
        } else {
            return null;
        }

    }

    /**
     * Inserts value by Key in container.
     *
     * @param key   Key to add.
     * @param value Value to holds.
     * @return      True if insert was successful.
     */
    boolean insert(K key, V value) {
        boolean result = false;
        boolean wasFound = false;
        resize();
        Entry<K, V> entryToAdd = new Entry<>(key, value, null);
        int cellToAdd = key == null ? 0 : Math.abs(key.hashCode()) % this.arr.length;
        Entry<K, V> entry = this.arr[cellToAdd];
        if (entry == null) {
            this.arr[cellToAdd] = entryToAdd;
            occupiedCells++;
            size++;
            result = true;
        } else {
             do {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    wasFound = true;
                    break;
                }
                if (entry.next != null) {
                    entry = entry.next;
                } else {
                    break;
                }
            } while (true);

            if (!wasFound) {
                entry.next = entryToAdd;
                size++;
                result = true;
            }
        }

        return result;
    }

    /**
     * Removes instance from container by key.
     *
     * @param key   Key to remove.
     * @return      true if key was removed.
     */
    boolean delete(K key) {
        boolean result = false;
        int cellToSearch = key == null ? 0 : Math.abs(key.hashCode()) % this.arr.length;
        Entry<K, V> entry = this.arr[cellToSearch];
        if (entry != null) {
            if (entry.getKey().equals(key)) {
                this.arr[cellToSearch] = null;
                result = true;
                size--;
            } else {
                while (entry.next != null) {
                    Entry<K, V> entryNext = entry.next;
                    if (entryNext.equals(key)) {
                        entry.next = null;
                        result = true;
                        size--;
                    } else {
                        entry = entry.next;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns value by passed key.
     *
     * @param key   Key to insert.
     * @return      value by key.
     */
    V get(K key) {
        int cellToGet = key == null ? 0 : Math.abs(key.hashCode()) % this.arr.length;
        Entry<K, V> entry = this.arr[cellToGet];
        if (entry == null) {
            return null;
        }
        do {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            if (entry.next != null) {
                entry = entry.next;
            } else {
                break;
            }
        } while (true);
        return null;
    }

    /**
     * Resize internal array based on load factor.
     */
    private void resize() {
        if ((float) occupiedCells / this.arr.length >= LOAD_FACTOR) {
            this.arr = Arrays.copyOf(this.arr, this.arr.length * 2);
        }
    }

    /**
     * Class to hold Key value pairs.
     *
     * @param <K>   key generic type.
     * @param <V>   value generic type.
     */
    private class Entry<K, V> implements DirEntry<K, V> {
        /**
         * key.
         */
        private K key;
        /**
         * Value.
         */
        private V value;
        /**
         * Link to next instance.
         */
        private Entry<K, V> next;

        /**
         * Creates new Entry instance.
         *
         * @param key   Key to add.
         * @param value Value to add.
         * @param next  Link to next instance.
         */
        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        /**
         * Sets passed value.
         *
         * @param value value to set.
         */
        void setValue(V value) {
            this.value = value;
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
