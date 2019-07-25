package com.hekm.interview.thread.join;

/**
 * @Author: heKangMin
 * @Description:
 * @Date: Created in 16:16 2019/7/25
 * @Modified By:
 */
public class Thread3 implements Runnable {

    private Thread2 t2;

    public Thread3(){
        t2 = new Thread2();
    }

    @Override
    public void run() {

        Thread thread2 = new Thread(t2);
        thread2.start();
        try {
            thread2.join();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am Thread3");
    }
}
