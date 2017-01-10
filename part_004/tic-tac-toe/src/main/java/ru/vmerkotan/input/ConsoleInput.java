package ru.vmerkotan.input;

import java.util.Scanner;

/**
 * {@code ConsoleInput} class represents an
 * input to work with console.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class ConsoleInput implements Input {
    /**
     * inner instance of Scanner.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Returns line read from console.
     * @return  String read from console.
     */
    @Override
    public String read() {
        return sc.nextLine().trim();
    }
}
