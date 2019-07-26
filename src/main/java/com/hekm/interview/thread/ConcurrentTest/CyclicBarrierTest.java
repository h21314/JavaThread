package com.hekm.interview.thread.ConcurrentTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: heKangMin
 * @Description: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待
 * CyclicBarrier可重用，CountDownLatch不可重用，计数值为0该CountDownLatch就不可再用了
 * @Date: Created in 17:30 2019/7/26
 * @Modified By:
 */
public class CyclicBarrierTest {
    private final static int THREAD_NUM = 10;

    public static void main(String[] args) {
        CyclicBarrier lock = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("大家都准备好了");
            }
        });
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            service.submit(new CyclicBarrierTask(lock,"Thread-" + i));
        }
        service.shutdown();

    }
    static class CyclicBarrierTask implements Runnable{
        private final CyclicBarrier lock;
        private final String threadName;

        public CyclicBarrierTask(CyclicBarrier lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }
        @Override
        public void run() {
            System.out.println(threadName + " is ready..");
            try {
                lock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " has finished..");
        }
    }
}
