package ru.vmerkotan;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vmerkotan on 12/12/2016.
 * задание 1. Проверить байтовый поток
 */
public class VerifyByteStream {

    /**
     * verifies that input contains only even number.
     * @param input InputStream to read from
     * @return true if input contains even number else false
     * @throws IOException when error present
     */
    public boolean isNumber(InputStream input) throws IOException {
        StringBuffer buff = new StringBuffer();
        int i;
        try (InputStream in = input) {
            while ((i = in.read()) != -1) {
                buff.append((char) i);
            }
            int result = Integer.valueOf(String.valueOf(buff));
            return result % 2 == 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
