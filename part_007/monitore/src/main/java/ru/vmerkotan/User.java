package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/27/2017.
 */
class User {
    private int amount;

    public User(int amount) {
        this.amount = amount;
    }

    public synchronized void changeAmount(int amount) {
        int tmp = this.amount;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount = tmp + amount;
    }

    public int getAmount() {
        return amount;
    }
}
