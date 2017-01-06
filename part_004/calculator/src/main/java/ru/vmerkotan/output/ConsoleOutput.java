package ru.vmerkotan.output;

/**
 * Created by vmerkotan on 1/6/2017.
 */
public class ConsoleOutput implements Output {
    /**
     * writes output data.
     * @param output    String to write.
     */
    @Override
    public void write(String output) {
        System.out.println(output);
    }
}
