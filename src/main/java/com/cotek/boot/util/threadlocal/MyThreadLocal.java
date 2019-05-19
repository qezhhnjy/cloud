package com.cotek.boot.util.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuzy
 * create time 19-3-6-下午9:40
 */
@Slf4j
public class MyThreadLocal {

    private static final int THREAD_LOOP_SIZE = 500;
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 100000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        // 初始化值的threadLocal
        ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "123");

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            exec.execute(() -> {
                threadLocal.set(new MyThreadLocal().add());
                Thread thread = Thread.currentThread();
                log.info(thread.getName());
                // 不remove就会出现OOM
                threadLocal.remove();
            });
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exec.shutdownNow();
    }

    private List<User> add() {
        List<User> users = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            users.add(new User("qezhhnjy", "passwd" + i, "男", i));
        }
        return users;
    }

    @AllArgsConstructor
    @Data
    class User {
        private String username;
        private String password;
        private String gender;
        private int age;
    }
}

class Volatile {
    private static volatile int count = 0;
    private static volatile int mark = 10;

    public static void add() {
        count++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
                mark--;
            }).start();
        }
        while (Thread.activeCount() > 1) Thread.yield();
        System.out.println(count);
    }
}

class Test {
    public volatile int count = 0;

    public void add() {
        count++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println(test.count);
    }
}

class Test2{
    public int count = 0;
    public synchronized void add() {
        count ++;
    }

    public static void main(String[] args) {
        final Test2 test2 = new Test2();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    test2.add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2)Thread.yield();
        System.out.println(test2.count);
    }
}

class Test3{
    public int count = 0;
    private Lock lock = new ReentrantLock();

    public void add() {
        lock.lock();
        try{
            count ++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Test3 test = new Test3();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    test.add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2)Thread.yield();
        System.out.println(test.count);
    }
}

class Test4{
    public AtomicInteger count = new AtomicInteger();
    public void add() {
        count.getAndIncrement();
    }
    public static void main(String[] args) {
        final Test4 test = new Test4();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    test.add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2)Thread.yield();
        System.out.println(test.count);
    }
}
