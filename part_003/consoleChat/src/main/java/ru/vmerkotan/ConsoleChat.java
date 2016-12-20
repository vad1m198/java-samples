package ru.vmerkotan;

import org.omg.CORBA.portable.*;

import java.io.*;
import java.util.Properties;

/**
 * Created by vmerkotan on 12/20/2016.
 * ConsoleChat class presents a container for
 * console chat to get random answer
 */
public class ConsoleChat {

    private static File answers;
    private static File log;
    private Input input;

    public ConsoleChat(File answers, File log, Input input) throws IOException {
        if(!log.exists()) {
            log.getParentFile().mkdirs();
            log.createNewFile();
        }
        this.answers = answers;
        this.log = log;
        this.input = input;
    }

    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_003\\app.properties"))));
        File in = new File(p.getProperty("pathIn"));
        File out = new File(p.getProperty("pathOut"));
        Input input = new ConsoleInput();
        ConsoleChat chat = new ConsoleChat(in, out, input);
        chat.run();
    }

    /**
     * Runs console chat
     * @throws IOException
     */
    public void run() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(log, "rw");

        boolean stopAnswers = false;
        boolean exit = false;
        System.out.println("Type something and press Enter to get answer");
        System.out.println("Type 'stop' to prevent answers to appear");
        System.out.println("Type 'continue' to restore answers");
        System.out.println("Type 'exit' to stop the run");
        try(RandomAccessFile out = raf) {
            while(!exit) {
                String nextLine = input.read();//scanner.nextLine().toLowerCase();
                out.writeBytes(nextLine + System.getProperty("line.separator"));
                if("stop".equals(nextLine)){
                    stopAnswers = true;
                } else if("continue".equals(nextLine)) {
                    stopAnswers = false;
                }

                if("exit".equals(nextLine)) {
                    exit = true;
                } else {
                    if(!stopAnswers){
                        String randomAnswer = getRandomAnswer(answers);
                        out.writeBytes(randomAnswer + System.getProperty("line.separator"));
                        System.out.println(randomAnswer);
                    }
                }
            }
        }
    }

    /**
     * returns random string from passed file
     * @param f File to get string from
     * @return random string from passed file
     * @throws IOException
     */
    private String getRandomAnswer(File f) throws IOException {
        String result;
        try(RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long r = (long) (Math.random() * raf.length());
            raf.seek(r);
            //pointer is now set to arbitrary position. It can be
            //in the middle of the string. Move to the end of string
            raf.readLine();
            result = raf.readLine();
            if(result == null) {
                raf.seek(0);
                result = raf.readLine();
            }
        }
        return result;
    }
}
