package ru.vmerkotan;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class UserStorage {

    private LinkedList<User> storage = new LinkedList<>();

    void add(User u) {
        storage.add(u);
    }

    public void remove(int index) {
        storage.remove(index);
    }

    User get(int index) {
        return storage.get(index);
    }

    void transferMoney(int indexFrom, int indexTo, int sum) {
        User from = storage.get(indexFrom);
        User to = storage.get(indexTo);
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        from.changeAmount(-sum);
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        to.changeAmount(sum);
    }


}
