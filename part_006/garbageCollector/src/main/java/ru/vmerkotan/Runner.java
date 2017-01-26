package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/26/2017.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;;i++) {
            new User("test user_" + i, i);
        }
    }
}
