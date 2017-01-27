package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class MultiThreadsProblems {

    private static String txt = "test";

    public static void main(String[] args) throws InterruptedException {

        (new Thread(MultiThreadsProblems::modify)).start();
        (new Thread(MultiThreadsProblems::modify)).start();

        Thread.sleep(500);
        System.out.println(txt);

    }

    private static void modify() {
        String tmp = txt + ". modified";
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        txt = tmp;
    }

}
