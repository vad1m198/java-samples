package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/27/2017.
 */
class Counter {

    private int counter;


    Counter(int counter) {
        this.counter = counter;
    }

    void increment(int i) {
        synchronized (this) {
            int tmp = this.counter;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.counter = tmp + i;
        }
    }

    int getCounter() {
        return counter;
    }
}
