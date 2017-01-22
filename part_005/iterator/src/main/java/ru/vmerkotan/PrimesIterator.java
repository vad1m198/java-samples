package ru.vmerkotan;

import java.util.Iterator;

/**
 * PrimesIterator class represents iterator of prime number indexes.
 * Created by Вадим on 01.01.2017.
 */
public class PrimesIterator implements Iterator<Integer> {
    /**
     * holds elements to iterate.
     */
    private final Integer[] array;
    /**
     * current position.
     */
    private int index = -1;

    /**
     * Creates new PrimesIterator object.
     *
     * @param array Integer[] elements to iterate.
     */
    public PrimesIterator(final Integer[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        for (int i = this.index + 1; i < this.array.length; i++) {
            if (isPrimes(this.array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        for (int i = this.index + 1; i < this.array.length; i++) {
            if (isPrimes(this.array[i])) {
                this.index = i;
                return this.array[i];
            }
        }
        return null;
    }

    /**
     * Verifies passed number is primes.
     *
     * @param i input number.
     * @return  true if passed number is primes,
     *          else false
     */
    private boolean isPrimes(int i) {
        if (i == 1 || i == 2) {
            return true;
        }
        if (i % 2 == 0) {
            return false;
        }

        for (int j = 3; j < i; j += 2) {
            if (i % j == 0) {
                return false;
            }
        }

        return true;
    }

}
