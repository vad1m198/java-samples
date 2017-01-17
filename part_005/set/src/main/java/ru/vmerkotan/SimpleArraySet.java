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
    private Object[] objects = new Object[10];
    /**
     * Current positionToAdd in objects array.
     */
    private int positionToAdd;

    /**
     * Current next position in objects array.
     */
    private int positionNext;

    @Override
    public void add(T t) {
        int result = binarySearch(this.objects, 0, this.positionToAdd, t.hashCode());

        if (result < 0) {
            int expectedPosition = Math.abs(result) - 1;
            if (this.positionToAdd == this.objects.length) {
                this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
            }
            System.arraycopy(this.objects, expectedPosition, this.objects, expectedPosition + 1, this.objects.length - expectedPosition - 1);
            objects[expectedPosition] = t;
            this.positionToAdd++;
        }
    }

//    /**
//     * Verifies that passed argument not equal
//     * to at least one argument from array.
//     *
//     * @param t Param to verify.
//     *
//     * @return  true is some element from internal array
//     *          is equal to passed param.
//     */
//    private boolean verifyDuplicates(T t) {
//        for (Object o: this.objects) {
//            if (t.equals(o)) {
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * Searches a range of hasCode of
     * the specified array of Objects for the specified value using the
     * binary search algorithm.
     * The range must be sorted.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or <tt>toIndex</tt> if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    private int binarySearch(Object[] a, int fromIndex, int toIndex,
                                    long key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid].hashCode();

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

    @Override
    public boolean hasNext() {
        return this.positionNext < this.positionToAdd;
    }

    @Override
    public T next() {
        return (T) objects[positionNext++];
    }
}
