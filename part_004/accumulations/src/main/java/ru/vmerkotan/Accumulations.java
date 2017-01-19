package ru.vmerkotan;

import java.util.Arrays;

/**
 * Container class for accumulations task.
 *
 * Created by vmerkotan on 1/19/2017.
 */
class Accumulations {
    /**
     * Array to work with.
     */
    private int[][] arr;

    /**
     * holds max accumulation.
     */
    private int max = 0;
    /**
     * Holds counter.
     */
    private int counter = 0;

    /**
     * Creates new Accumulations instance.
     *
     * @param arr   int[][] to work with.
     */
    Accumulations(int[][] arr) {
        this.arr = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
    }

    /**
     * Verified passed int[][] and returns
     * the number of the biggest accumulations fill with 1.
     */
    void findAccumulationsAmount() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = 0;
                    this.counter = 1;
                    verifyNeighbours(i, j);
                }
                if (this.counter > this.max) {
                    this.max = this.counter;
                }
            }
        }
    }

    /**
     * Verifies neighbour cells and update this.count and
     * values.
     *
     * @param i int array position.
     * @param j int position in array.
     */
    private void verifyNeighbours(int i, int j) {
        if (arr[i].length > j + 1 && arr[i][j + 1] == 1) {
            this.counter++;
            arr[i][j + 1] = 0;
            verifyNeighbours(i, j + 1);
        }

        if (arr.length > i + 1 && arr[i + 1][j] == 1) {
            this.counter++;
            arr[i + 1][j] = 0;
            verifyNeighbours(i + 1, j);
        }
    }

    /**
     * Returns max value.
     *
     * @return  int max number of 1 accumulation.
     */
    int getMax() {
        return max;
    }
}
