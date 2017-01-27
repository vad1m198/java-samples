package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by vmerkotan on 1/27/2017.
 */
public class CounterTest {

    @Test
    public void testIncrement() throws InterruptedException {
        Counter c = new Counter(0);

        Thread t1 = new Thread(() -> c.increment(100));
        Thread t2 = new Thread(() -> c.increment(100));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

       assertThat(c.getCounter(), is(200));
    }



}