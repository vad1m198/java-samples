package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleInterruptRunnable implements Runnable {

    private String text;
    private char c;
    private int counter;

    SimpleInterruptRunnable(String text, char c) {
        this.text = text;
        this.c = c;
    }

    @Override
    public void run() {
        int counter = 0;
        for (char c : this.text.toCharArray()) {

            if (Thread.interrupted()) {
                return;
            }

            if (c == this.c) {
                counter++;
            }
        }
        this.counter = counter;
    }

    int getCounter() {
        return this.counter;
    }
}
