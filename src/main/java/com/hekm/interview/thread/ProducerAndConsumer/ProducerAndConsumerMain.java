package com.hekm.interview.thread.ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: heKangMin
 * @Description:
 * 生产者消费者有很多的实现方法：
 * 1：使用wait()，notifyAll()方法，
 * 2:Lock中的Condition对象，await()和SignalAll(),原理与wait().notifyAll()类似，
 * 3：使用类库中已经实现好的工具类，本例使用BlockingQueue(阻塞队列)来实现
 * @Date: Created in 15:12 2019/7/26
 * @Modified By:
 */
public class ProducerAndConsumerMain {



    public static void main(String[] args) {
        final BlockingQueue<Integer> list = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(list);
        Consumer consumer = new Consumer(list);
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
