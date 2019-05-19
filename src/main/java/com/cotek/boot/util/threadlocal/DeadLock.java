package com.cotek.boot.util.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fuzy
 * create time 19-3-6-下午11:51
 * 模拟死锁的场景， 三个人 三根筷子，每个人需要拿到身边的两根筷子才能开始吃饭
 * 出现死锁的场景是，三个人都拿到了右边的筷子，但是由于筷子都被抢占，均无法获得左边的筷子
 */
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size]));
        }
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}

@Data
class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
    }
}

@AllArgsConstructor
@Slf4j
@Data
class Philosopher implements Runnable {

    private Chopstick left;
    private Chopstick right;

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    private void pauseToHoldChopstick() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                log.info(this + " thinking");
                pause();
                right.take();
                log.info(this + " grabbing right");
                pauseToHoldChopstick();
                left.take();
                log.info(this + " grabbing left");
                log.info(this + " eating");
                pause();
                right.drop();
                log.info(this + " drop right");
                left.drop();
                log.info(this + " drop left");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
