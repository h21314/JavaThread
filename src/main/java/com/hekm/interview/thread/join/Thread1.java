package com.hekm.interview.thread.join;

/**
 * @Author: heKangMin
 * @Description:
 * @Date: Created in 16:15 2019/7/25
 * @Modified By:
 */
public class Thread1 implements Runnable{


    @Override
    public void run() {
        System.out.println("i am thread1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
