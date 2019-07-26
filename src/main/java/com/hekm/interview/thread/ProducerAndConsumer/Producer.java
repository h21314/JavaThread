package com.hekm.interview.thread.ProducerAndConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: heKangMin
 * @Description:
 * @Date: Created in 15:05 2019/7/26
 * @Modified By:
 */
public class Producer implements Runnable{
    private final BlockingQueue<Integer> list;
    public Producer(BlockingQueue list){
        this.list = list;
    }
    @Override
    public void run() {
        while (true) {
            int i = new Random().nextInt(100);
            try {
                list.put(i);
                System.out.println("producer has produced: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
