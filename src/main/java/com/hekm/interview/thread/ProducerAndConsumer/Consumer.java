package com.hekm.interview.thread.ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: heKangMin
 * @Description: 使用阻塞队列模拟生产者消费者
 * @Date: Created in 15:08 2019/7/26
 * @Modified By:
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Integer> list;

    public Consumer(BlockingQueue list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer take = list.take();
                System.out.println("Consumer has consumed " + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
