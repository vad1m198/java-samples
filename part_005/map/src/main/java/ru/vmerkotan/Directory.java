package ru.vmerkotan;


import java.util.Iterator;

/**
 * Directory class represent a simple data structure
 *
 * Created by vmerkotan on 1/17/2017.
 */

/**
 * Directory class represent a simple key-value data structure.
 *
 * @param <K>   Generic key type.
 * @param <V>   Generic value type.
 */
public class Directory<K, V> implements Iterator<Directory.DirEntry<K, V>> {

    private Entry<K, V>[] arr = new Entry[10];

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Directory.DirEntry<K, V> next() {
        return null;
    }

    boolean insert(K key, V value) {
        return false;
    }

    boolean delete(K key) {
        return false;
    }

    V get(K key) {
        return null;
    }


    private class Entry<K, V> implements DirEntry<K, V> {

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }



    interface DirEntry<K, V> {

        K getKey();

        V getValue();

    }
}
