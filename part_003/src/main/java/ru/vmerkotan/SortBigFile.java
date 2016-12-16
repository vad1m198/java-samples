package ru.vmerkotan;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vmerkotan on 12/15/2016.
 * SortBigFile class presents a container of
 * methods to work with big files
 */
public class SortBigFile {

    /**
     * Updates dest file with strings form source
     * ordered in ascending by string length
     * @param source    File to read from
     * @param dest      File to write to from
     * @throws IOException
     */
    public void sort(File source, File dest) throws IOException {

        int LINES_CAPACITY = 10;

        dest.getParentFile().mkdirs();
        dest.createNewFile();

        RandomAccessFile raf = new RandomAccessFile(source, "r");
        RandomAccessFile rafOut = new RandomAccessFile(dest, "rw");
        List<String> lines = new ArrayList<>();

        String str;
        int counter = LINES_CAPACITY;
        while((str = raf.readLine()) != null) {
            lines.add(str);

            if(--counter == 0 || raf.getFilePointer() == raf.length()) {
                lines.sort(new CompareStringsByLength());
                for(String s: lines) {
                    rafOut.writeBytes(s + System.getProperty("line.separator"));
                }
                lines.clear();
                counter = LINES_CAPACITY;
            }
        }
/*
        lines.sort(new CompareStringsByLength());

        for(String s: lines) {
            rafOut.writeBytes(s + System.getProperty("line.separator"));
        }*/
        raf.close();
        rafOut.close();
    }

    private class CompareStringsByLength implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() >  s2.length() ? 1 : -1;
        }
    }

}
