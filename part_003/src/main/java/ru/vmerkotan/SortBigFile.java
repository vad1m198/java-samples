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

        final int LINES_CAPACITY = 10;

        dest.getParentFile().mkdirs();
        dest.createNewFile();

        File tmp1 = new File(dest.getParentFile().getAbsolutePath() + File.separator + "tmp1.txt");
        File tmp2 = new File(dest.getParentFile().getAbsolutePath() + File.separator + "tmp2.txt");
        File tmp3 = new File(dest.getParentFile().getAbsolutePath() + File.separator + "tmp3.txt");
        File tmp4 = new File(dest.getParentFile().getAbsolutePath() + File.separator + "tmp4.txt");
        tmp1.createNewFile();
        tmp2.createNewFile();
        tmp3.createNewFile();
        tmp4.createNewFile();

        RandomAccessFile raf = new RandomAccessFile(source, "r");
        RandomAccessFile rafOut = new RandomAccessFile(dest, "rw");
        RandomAccessFile rafTmp1 = new RandomAccessFile(tmp1, "rw");
        RandomAccessFile rafTmp2 = new RandomAccessFile(tmp2, "rw");
        RandomAccessFile rafTmp3 = new RandomAccessFile(tmp3, "rw");
        RandomAccessFile rafTmp4 = new RandomAccessFile(tmp4, "rw");
        List<String> lines = new ArrayList<>();

        String str;
        int counter = LINES_CAPACITY;
        boolean writeFirst = false;
        while((str = raf.readLine()) != null) {
            lines.add(str);
            if(--counter == 0 || raf.getFilePointer() == raf.length()) {
                lines.sort(new CompareStringsByLength());
                for(String s: lines) {
                    if(writeFirst) {
                        rafTmp1.writeBytes(s + System.getProperty("line.separator"));
                    } else {
                        rafTmp2.writeBytes(s + System.getProperty("line.separator"));
                    }
                }
                writeFirst = !writeFirst;
                lines.clear();
                counter = LINES_CAPACITY;
            }
        }

        rafTmp1.seek(0);
        rafTmp2.seek(0);

        System.out.println(rafTmp1.length() + ":" + rafTmp2.length());
        while((str = rafTmp1.readLine()) != null) {
            rafOut.writeBytes( str + System.getProperty("line.separator"));
        }

        while((str = rafTmp2.readLine()) != null) {
            rafOut.writeBytes( str + System.getProperty("line.separator"));
        }

        rafTmp1.close();
        rafTmp2.close();
        rafTmp3.close();
        rafTmp4.close();
        raf.close();
        rafOut.close();
//        tmp1.delete();
//        tmp2.delete();
//        tmp3.delete();
//        tmp4.delete();
    }

    private class CompareStringsByLength implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() >  s2.length() ? 1 : -1;
        }
    }

}
