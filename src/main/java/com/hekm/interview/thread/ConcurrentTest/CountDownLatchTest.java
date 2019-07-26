package com.hekm.interview.thread.ConcurrentTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: heKangMin
 * @Description:
 * CountDownLatch:一个线程（或者多个），等待另外N个线程完成某个事情之后才能执行,被等待的那个线程调用CountDownLatch.await()方法
 * @Date: Created in 17:00 2019/7/26
 * @Modified By:
 */
public class CountDownLatchTest {
    private final static int THREAD_NUM = 10;

    public static void main(String[] args) {
        CountDownLatch lock = new CountDownLatch(THREAD_NUM);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.submit(new CountDownLatchTask(lock,"Thread-" + i));
        }
        try {
            lock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("10个工作线程已完成");
        executorService.shutdown();
    }

    static class CountDownLatchTask implements Runnable{
        private final CountDownLatch lock;
        private final String threadName;

        public CountDownLatchTask(CountDownLatch lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }
        @Override
        public void run() {
            System.out.println(threadName + " 执行完成");
            lock.countDown();
        }
    }
}
