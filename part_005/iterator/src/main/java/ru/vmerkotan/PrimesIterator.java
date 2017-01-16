package ru.vmerkotan;

import java.util.Iterator;

/**
 * PrimesIterator class represents iterator of prime number indexes.
 * Created by Вадим on 01.01.2017.
 *
 * @param <T> Type Generic.
 */
public class PrimesIterator<T> implements Iterator<T> {
    /**
     * holds elements to iterate.
     */
    private final T[] array;
    /**
     * current position.
     */
    private int index = 1;
    /**
     *  holds primes indexes.
     */
    private int[] primes = new int[1];
    /**
     * Index in primes array.
     */
    private int primesIndex = 0;

    /**
     * Creates new PrimesIterator object.
     *
     * @param array T[] elements to iterate.
     */
    public PrimesIterator(final T[] array) {
        this.array = array;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.index > this.array.length - 1) {
            result = false;
        } else {
            for (int i = this.index; i < this.array.length; i++) {
                if (isPrimes(i)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public T next() {
        T result = this.array[this.index];
        boolean foundNext = false;
        for (int i = index + 1; i < this.array.length; i++) {
            if (isPrimes(i)) {
                addPrimes(i);
                index = i;
                foundNext = true;
                break;
            }
        }
        if (!foundNext) {
            index = this.array.length;
        }
        return result;
    }

    /**
     * Verifies passed number is primes.
     *
     * @param i input number.
     * @return  true if passed number is primes,
     *          else false
     */
    private boolean isPrimes(int i) {
        boolean result = true;
        for (int p: this.primes) {
            if (p != 0 && i % p == 0 && p != i) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Adds passed int to premise array.
     *
     * @param i int to add
     */
    private void addPrimes(int i) {
        if (this.primesIndex == this.primes.length) {
            int[] newPrimes = new int[this.primes.length * 2];
            System.arraycopy(this.primes, 0, newPrimes, 0, this.primes.length);
            this.primes = newPrimes;
        }
        this.primes[this.primesIndex++] = i;
    }
}
