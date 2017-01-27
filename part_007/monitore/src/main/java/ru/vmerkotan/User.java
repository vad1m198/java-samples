package ru.vmerkotan;

import java.util.Random;

/**
 * Created by vmerkotan on 1/27/2017.
 */
class User {
    private int amount;

    User(int amount) {
        this.amount = amount;
    }

    synchronized void changeAmount(int amount) {
        int tmp = this.amount;
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount = tmp + amount;
    }

    int getAmount() {
        return amount;
    }
}
