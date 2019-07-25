package com.hekm.interview.thread.ReadAndWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: heKangMin
 * @Description: 实现一个高效缓存，允许多个用户读，只允许一个用户写
 * @Date: Created in 16:51 2019/7/25
 * @Modified By:
 */
public class ReadAndWrite<K,V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Map<K,V> map;

    public ReadAndWrite(Map<K,V> map){
        this.map = map;
    }

    public V put(K key, V value) {
        while (true) {
            if (writeLock.tryLock()) {
                try{
                    System.out.println("put " + key + " = " + value);
                    return map.put(key,value);
                }finally {
                    writeLock.unlock();
                }
            }
        }
    }

    public V get(K key) {
        while (true) {
            if (readLock.tryLock()) {
                try{
                    V v = map.get(key);
                    System.out.println("get " + key + " = " + v);
                    return v;
                }finally {
                    readLock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        final ReadAndWrite<String, Integer> rw = new ReadAndWrite<>(new HashMap<>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new TestRunnable(rw));
        }
        executorService.shutdown();
    }

    static class TestRunnable implements Runnable{
        private final ReadAndWrite<String,Integer> rw;
        private final String KEY = "x";
        public TestRunnable(ReadAndWrite<String,Integer> rw){
            this.rw = rw;
        }
        @Override
        public void run() {
            Random random = new Random();
            int r = random.nextInt(100);
            if (r < 30) {
                rw.put(KEY,r);
            }else{
                rw.get(KEY);
            }
        }
    }
}
