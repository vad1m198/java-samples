package ru.vmerkotan;

/**
 * Created by Вадим on 28.01.2017.
 */
public class ProducerConsumer {

    private Integer value;

    public synchronized void put(Integer num) throws InterruptedException {
        while(value != null) {
            this.wait();
        }
        this.value = num;
        System.out.println(num + " was added");
        this.notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while(value == null) {
            this.wait();
        }
        int result = this.value;
        this.value = null;
        System.out.println(result + " was returned");
        this.notifyAll();
        return result;
    }

}
