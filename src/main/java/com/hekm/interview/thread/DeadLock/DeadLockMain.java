package com.hekm.interview.thread.DeadLock;

/**
 * @Author: heKangMin
 * @Description: 模拟死锁程序
 * 产生死锁有四个必要条件：
 * 1、互斥：一个资源每次只能被一个进程使用；
 * 2、请求与保持：一个进程因请求资源而阻塞时，对已获得的资源保持不进行释放
 * 3：不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺
 * 4：循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
 * 解决方法：
 * 破坏掉任意一个必要条件就可以避免死锁，一般从第二个条件和第四个条件进行破坏
 * 1：避免无限期的等待，用Lock.tryLock(),wait()/notify()方法请求一定时间后，并释放资源（Condition中await()可以设置阻塞时间）
 * 2：注意锁的顺序，多个线程已固定的顺序去请求锁，可以避免死锁
 * 3：- 开放调用：即只对有请求的进行封锁。你应当只想你要运行的资源获取封锁，比如在上述程序中我在封锁的完全的对象资源。但是如果我们只对它所属领域中的一个感兴趣，那我们应当封锁住那个特殊的领域而并非完全的对象。
 * @Date: Created in 15:48 2019/7/26
 * @Modified By:
 */
public class DeadLockMain {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        ThreadA ta = new ThreadA(a,b);
        ThreadB tb = new ThreadB(a,b);
        new Thread(ta).start();
        new Thread(tb).start();
    }

    static class ThreadA implements Runnable{
        private final Object a;
        private final Object b;

        public ThreadA(Object a, Object b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            while (true) {
                synchronized (a){
                    try {
                        Thread.sleep(1000);
                        synchronized (b){
                            System.out.println("I am threa a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    static class ThreadB implements Runnable{
        private final Object a;
        private final Object b;

        public ThreadB(Object a, Object b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            while (true) {
                synchronized (b){
                    try {
                        Thread.sleep(1000);
                        synchronized (a){
                            System.out.println("I am threa b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
