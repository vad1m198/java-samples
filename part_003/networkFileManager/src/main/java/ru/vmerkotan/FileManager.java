package ru.vmerkotan;

import java.io.*;

/**
 * Created by vmerkotan on 12/26/2016.
 */
public class FileManager {

    private final int BYTE_ARRAY_SIZE = 4096;

    private void readFromFileToStream(File file, DataOutputStream outputStr) throws IOException {
        byte[] arr = new byte[BYTE_ARRAY_SIZE];
        try (ByteArrayOutputStream ous = new ByteArrayOutputStream();
             FileInputStream fInput = new FileInputStream(file)) {

            long counter = 0;
            long fileSize =  file.length();
            while ( counter <= fileSize) {
                fInput.read(arr);
                ous.write(arr, 0, (int) (fileSize - counter) > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : (int) (fileSize - counter) );
                counter += BYTE_ARRAY_SIZE;
            }
            outputStr.write(ous.toByteArray());
        }
    }

    private void readFromStreamToFile(DataInputStream inStream, File f) throws IOException {
        byte[] arr = new byte[BYTE_ARRAY_SIZE];
        long fileSize = f.length();
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
            long counter = 0;
            while(counter <= fileSize) {
                inStream.read(arr);
                dos.write(arr, 0, (int) (fileSize - counter) > BYTE_ARRAY_SIZE ? BYTE_ARRAY_SIZE : (int) (fileSize - counter));
                counter += BYTE_ARRAY_SIZE;
            }
        }
    }

    public void writePathToOutputStream(String path, DataOutputStream out) throws IOException {
        File fileToUpload = new File(path);
        if(fileToUpload.exists() && fileToUpload.isFile()) {
            out.writeUTF("200");
            out.writeUTF(fileToUpload.getName());
            out.writeLong(fileToUpload.length());
            this.readFromFileToStream(fileToUpload, out);
        } else {
            out.writeUTF("404");
        }
    }

    public void readInputStreamToFile(DataInputStream inStream, String currentDir) throws IOException {
        if("200".equalsIgnoreCase(inStream.readUTF())) {
            String fileName = inStream.readUTF();
            long fileSize = inStream.readLong();
            File f = new File( currentDir + File.separator + fileName);
            f.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.setLength(fileSize);
            raf.close();
            this.readFromStreamToFile(inStream, f);
        }
    }

}
