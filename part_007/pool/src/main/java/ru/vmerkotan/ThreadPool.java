package ru.vmerkotan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 29.01.2017.
 */
class ThreadPool {

    private Workable work;

    ThreadPool() {
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(() -> {
                while(true) {
                    if(Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    this.getWork();
                }
            }).start();
        }
    }

    private synchronized void getWork() {
        while(work == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.work.execute();
        this.work = null;
        this.notifyAll();
    }

    synchronized void add(Workable work) {
        while(this.work != null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.work = work;
        this.notifyAll();
    }

}
