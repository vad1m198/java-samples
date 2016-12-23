package ru.vmerkotan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Represents class to work with Input nad OutPut Streams.
 * Created by vmerkotan on 12/12/2016.
 */
public class DropAbuse {
    /**
     * Removes all abused words from input stream and writes to output stream.
     * @param inStream input stream to read from.
     * @param outStream outputStream to write to.
     * @param abuse strings to be dropped
     * @throws IOException when error present
     */
    public void dropAbuses(InputStream inStream, OutputStream outStream, String[] abuse) throws IOException {

        try (InputStream in = inStream; OutputStream out = outStream) {
            Scanner sc  = new Scanner(in);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                for (String str : abuse) {
                    s = s.replaceAll(str, "");
                }
                out.write(s.getBytes());
            }
        }
    }
}
