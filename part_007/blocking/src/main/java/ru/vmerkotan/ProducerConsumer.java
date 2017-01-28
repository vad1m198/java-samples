package ru.vmerkotan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 28.01.2017.
 */
public class ProducerConsumer {

    private Integer value;
    private List<Integer> values = new ArrayList<>();


//    public synchronized void put(Integer num) throws InterruptedException {
//        while(value != null) {
//            this.wait();
//        }
//        this.value = num;
//        System.out.println(num + " was added");
//        this.notifyAll();
//    }
//
//    public synchronized Integer get() throws InterruptedException {
//        while(value == null) {
//            this.wait();
//        }
//        int result = this.value;
//        this.value = null;
//        System.out.println(result + " was returned");
//        this.notifyAll();
//        return result;
//    }
    public synchronized void put(Integer num) throws InterruptedException {
        while(values.size() >= 10) {
            this.wait();
        }

        this.values.add(num);
        System.out.println(num + " was added");
        this.notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while(values.size() == 0) {
            this.wait();
        }
        int result = this.values.get(0);
//        this.value = null;
        this.values.remove(0);
        System.out.println(result + " was returned");
        this.notifyAll();
        return result;
    }

}
