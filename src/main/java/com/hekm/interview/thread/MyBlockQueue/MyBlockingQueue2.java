package com.hekm.interview.thread.MyBlockQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: heKangMin
 * @Description: 通过lock锁的多条件（condition）进行阻塞控制，模仿ArrayBlockingQueue实现阻塞队列
 * @Date: Created in 11:52 2019/7/26
 * @Modified By:
 */
public class MyBlockingQueue2<E> {
    private final List<E> list;
    private final int limit;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MyBlockingQueue2(int limit){
        this.limit = limit;
        list = new LinkedList();
    }

    public void put(E e) throws InterruptedException{
        lock.lock();
        try{
            while (list.size() == limit) {
                notFull.await();
            }
            list.add(e);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException{
        lock.lock();
        try{
            while (list.isEmpty()){
                notEmpty.await();
            }
            E remove = list.remove(0);
            notFull.signalAll();
            return remove;
        }finally {
            lock.unlock();
        }
    }
}
