package ru.vmerkotan;

/**
 * {@code ConsoleOutput} class represents
 * a simple class to write to console.
 *
 * Created by Вадим on 08.01.2017.
 */
public class ConsoleOutput implements Output {
    @Override
    public void write(String s) {
        System.out.println(s);
    }
}
