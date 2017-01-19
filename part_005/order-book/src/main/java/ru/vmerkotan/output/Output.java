package ru.vmerkotan.output;

/**
 * Output represents an interface.
 * To write to.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public interface Output {
    /**
     * Writes message.
     *
     * @param message   String message to write.
     */
    void write(String message);
}
