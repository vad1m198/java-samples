package ru.vmerkotan;

import java.util.LinkedList;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class UserStorage {

    private LinkedList<User> storage = new LinkedList<>();

    public void add(User u) {
        storage.add(u);
    }

    public void remove(int index) {
        storage.remove(index);
    }

    public User get(int index) {
        return storage.get(index);
    }

    public /*synchronized */void transferMoney(int indexFrom, int indexTo, int sum) {
        User from = storage.get(indexFrom);
        User to = storage.get(indexTo);

        from.changeAmount(-sum);
        to.changeAmount(sum);
    }


}
