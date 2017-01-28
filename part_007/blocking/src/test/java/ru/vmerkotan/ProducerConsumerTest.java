package ru.vmerkotan;

import org.junit.Test;

import java.util.Random;

/**
 * Tests for ProducerConsumer class.
 *
 * Created by Вадим on 28.01.2017.
 */
public class ProducerConsumerTest {

    private Random random = new Random();

    @Test
    public void testProducerConsumerWork() throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer = new ProducerThread(pc);
        Thread consumer = new ConsumerThread(pc);
        Thread producer1 = new ProducerThread(pc);
        Thread consumer1 = new ConsumerThread(pc);

        producer.start();
        consumer.start();
        producer1.start();
        consumer1.start();

        producer.join();
        consumer.join();
        producer1.join();
        consumer1.join();

    }

    private class ConsumerThread extends Thread {
        private final ProducerConsumer pc;

        private ConsumerThread(ProducerConsumer pc) {
            this.pc = pc;
        }

        public void run() {
                for(int i = 0; i < 100; i++) {
                try {
//                    Thread.sleep(50);
                    pc.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ProducerThread extends Thread {
        private final ProducerConsumer pc;

        private ProducerThread(ProducerConsumer pc) {
            this.pc = pc;
        }

        public void run() {
            for(int i = 0; i < 100; i++) {
                int tmp = random.nextInt();
                try {
                    Thread.sleep(random.nextInt(500));
                    pc.put(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}