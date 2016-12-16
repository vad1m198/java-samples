package ru.vmerkotan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by vmerkotan on 12/12/2016.
 */
public class DropAbuse {

    public void dropAbuses(InputStream inStream, OutputStream outStream, String[] abuse) throws IOException {

        try (InputStream in = inStream; OutputStream out = outStream) {
            Scanner sc  = new Scanner(in);
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                for (String str : abuse) {
                    s = s.replaceAll(str, "");
                }
                out.write(s.getBytes());
                out.flush();
            }
        }
    }


}
