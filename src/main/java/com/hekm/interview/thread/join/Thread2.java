package com.hekm.interview.thread.join;

/**
 * @Author: heKangMin
 * @Description:
 * @Date: Created in 16:16 2019/7/25
 * @Modified By:
 */
public class Thread2 implements Runnable {

    private Thread1 t1;

    public Thread2(){
        t1 = new Thread1();
    }

    @Override
    public void run() {

        Thread thread1 = new Thread(t1);
        thread1.start();
        try {
            thread1.join();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am thread2");
    }
}
