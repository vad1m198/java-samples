package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleRunnable implements Runnable {

    private String text;
    private char c;

    private int counter;

    SimpleRunnable(String text, char c) {
        this.text = text;
        this.c = c;
    }

    @Override
    public void run() {
        int counter = 0;
        for (char c : this.text.toCharArray()) {
            if (c == this.c) {
                counter++;
            }
        }
        this.counter = counter;
        System.out.println("Test string: " + text + ". Contains " + counter + " '" + c + "'");
    }
}
