package ru.vmerkotan;

/**
 * SimpleCounter class
 *
 * Created by vmerkotan on 1/27/2017.
 */
public class SimpleCounter {

    private static String text = "Some text string to work with";

    public static void main(String[] args) {
        Thread countWhiteSpacesThread = new Thread(() -> {
            int spaceCount = 0;
            for (char c : text.toCharArray()) {
                if (c == ' ') {
                    spaceCount++;
                }
            }
            System.out.println("String contains: " + spaceCount + " whitespaces.");
        });

        Thread countWordsThread = new Thread(() -> {
            int wordsCount = text.split(" ").length;
            System.out.println("String contains: " + wordsCount + " words.");
        });

        countWhiteSpacesThread.start();
        countWordsThread.start();
    }
}
