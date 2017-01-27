package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by vmerkotan on 1/27/2017.
 */
public class UserStorageTest {

    @Test
    public void whenTransferMoneyThenMoneyShouldTransferedProperly() throws InterruptedException {
        User u1 = new User(1000);
        User u2 = new User(1000);
        UserStorage storage = new UserStorage();
        storage.add(u1);
        storage.add(u2);

        Thread t1 = new Thread(() -> storage.transferMoney(0, 1, 100));
        Thread t2 = new Thread(() -> storage.transferMoney(0, 1, 100));
        Thread t3 = new Thread(() -> storage.transferMoney(0, 1, 100));
        Thread t4 = new Thread(() -> storage.transferMoney(0, 1, 100));
        Thread t5 = new Thread(() -> storage.transferMoney(0, 1, 100));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        assertThat(storage.get(0).getAmount(), is(500));
        assertThat(storage.get(1).getAmount(), is(1500));
    }



}