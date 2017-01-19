package ru.vmerkotan.output;

/**
 * ConsoleOutput represents a class
 * to write to console.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class ConsoleOutput implements Output {
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
