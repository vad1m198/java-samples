package ru.vmerkotan;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;

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

        dest.getParentFile().mkdirs();
        String absolutePath = dest.getParentFile().getAbsolutePath();

        RandomAccessFile rafIn = new RandomAccessFile(source, "r");
        String fileNameMask = "sortFilesTmp";
        String str;
        int linesCounter = 0;
        //create temporary files with length of one line
        while((str = rafIn.readLine()) != null) {
            File f = new File(absolutePath + File.separator + fileNameMask + linesCounter++ + ".txt");
            f.createNewFile();
            RandomAccessFile rafTmp = new RandomAccessFile(f, "rw");
            rafTmp.writeBytes(str);
            rafTmp.close();
        }
        rafIn.close();

        File parent = new File(absolutePath);
        File[] files = parent.listFiles(new FilesFilter(fileNameMask));

        while(files.length > 1) {
            for(int i = 0; i < files.length; i+=2) {
                RandomAccessFile rafTmp1 = new RandomAccessFile(files[i], "r");
                RandomAccessFile rafTmp2 = null;
                if(i+1 < files.length) {
                    rafTmp2 = new RandomAccessFile(files[i+1], "r");
                }

                File fOut = new File(absolutePath + File.separator + fileNameMask + files.length + i + ".txt");
                fOut.createNewFile();
                RandomAccessFile rafTmp = new RandomAccessFile(fOut, "rw");

                String str1 = rafTmp1.readLine();
                String str2 = null;
                if(rafTmp2!=null) {
                    str2 = rafTmp2.readLine();
                }
                boolean breakLoop = false;
                while(!breakLoop) {

                    if(str2 != null && str1 != null && str1.length() > str2.length()) {
                        rafTmp.writeBytes(str2 + System.getProperty("line.separator"));
                        str2 = rafTmp2.readLine();
                    } else if(str2 != null && str1 != null){
                        rafTmp.writeBytes(str1 + System.getProperty("line.separator"));
                        str1 = rafTmp1.readLine();
                    } else if(str2 != null) {
                        rafTmp.writeBytes(str2 + System.getProperty("line.separator"));
                        str2 = rafTmp2.readLine();
                    } else if(str1 != null) {
                        rafTmp.writeBytes(str1 + System.getProperty("line.separator"));
                        str1 = rafTmp1.readLine();
                    } else {
                        breakLoop = true;
                    }
                }
                rafTmp1.close();
                if(rafTmp2!=null)rafTmp2.close();
                rafTmp.close();
                files[i].delete();
                if(i+1 < files.length) {
                    files[i+1].delete();
                }

            }
            files = parent.listFiles(new FilesFilter(fileNameMask));
        }

        dest.createNewFile();
        RandomAccessFile rafDest = new RandomAccessFile(dest, "rw");
        RandomAccessFile rafRead = new RandomAccessFile(files[0], "r");
        String strout;
        while((strout=rafRead.readLine())!=null) {
            rafDest.writeBytes(strout + System.getProperty("line.separator") );
        }
        rafRead.close();
        rafDest.close();
        files[0].delete();

    }

    private class FilesFilter implements FilenameFilter {
        private final String fileMask;

        public FilesFilter(String mask) {
            this.fileMask = mask;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.contains(fileMask);
        }
    }

}
