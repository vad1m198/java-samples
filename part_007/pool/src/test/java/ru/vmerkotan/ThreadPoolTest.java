package ru.vmerkotan;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 29.01.2017.
 */
public class ThreadPoolTest {

    @Test
    public void testThreadPoolWork() {
        ThreadPool pool = new ThreadPool();

        for(int i = 0; i < 50; i++) {
            pool.add(() -> {
                //dummy work
                int [] arr = new int[1];
                for(int j =0; j < 1_000_000; j++) {
                    arr[0] = new Random().nextInt();
                }
                System.out.println(Thread.currentThread().getName() + " says Hello!!! " + arr[0]);
            });

            pool.add(() -> System.out.println(Thread.currentThread().getName() + " says Hi!!!"));
        }
    }

}