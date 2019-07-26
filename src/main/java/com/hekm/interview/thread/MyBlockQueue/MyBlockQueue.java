package com.hekm.interview.thread.MyBlockQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: heKangMin
 * @Description: 使用LinkedList数据结构实现阻塞队列
 * @Date: Created in 9:33 2019/7/26
 * @Modified By:
 */
public class MyBlockQueue<E> {
    final List<E> list;
    private final int limit;

    public MyBlockQueue(int limit){
        list = new LinkedList();
        this.limit = limit;
    }

    /**
     * 用list进行加锁，在队列为空或满时效率会比较低，因为会一直空轮询
     * @param e
     */
    public void put(E e){
        while(true){
            synchronized (list){
                if (list.size() < limit){
                    list.add(e);
                }
            }
        }

    }

    public E take(){
        while(true){
            synchronized (list){
                if (list.isEmpty()) {
                    return null;
                }
                return list.remove(0);
            }
        }


    }

    /**
     * 使用wait()、notifyAll()方法实现
     * @param e
     */
    public synchronized void putByWait(E e) {
        while (list.size() == limit){
            try {
                //如果队列已满，进入阻塞状态，并释放锁，当前是对该实例进行加锁的
                wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
        list.add(e);
        System.out.println("put value: " + e);
        notifyAll();
    }

    public synchronized E takeByWait(){
        while (list.isEmpty()) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E remove = list.remove(0);
        System.out.println("take value: " + remove);
        notifyAll();
        return remove;
    }


    public static void main(String[] args) {
        final MyBlockQueue<Integer> myBlockQueue = new MyBlockQueue<>(10);
        final int NTHREADS = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < NTHREADS; i++) {
            executorService.execute(new BlockQueueRunnable(myBlockQueue));
        }
    }

    static class BlockQueueRunnable implements Runnable{
        private final MyBlockQueue<Integer> myBlockQueue;
        public BlockQueueRunnable(MyBlockQueue<Integer> myBlockQueue){
            this.myBlockQueue = myBlockQueue;
        }
        @Override
        public void run() {
            Random random = new Random();
            int r = random.nextInt(100);
            if (r < 50) {
                myBlockQueue.putByWait(r);
            }else{
                myBlockQueue.takeByWait();
            }
        }
    }
}

