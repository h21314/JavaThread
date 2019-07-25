package com.hekm.interview.thread.join;

/**
 * @Author: heKangMin
 * @Description: 现在有线程 T1、T2 和 T3。你如何确保 T2 线程在 T1 之后执行，并且 T3 线程在 T2 之后执行？
 * @Tag: EASY
 * @Date: Created in 16:17 2019/7/25
 * @Modified By:
 */
public class JoinMain {
    public static void main(String[] args) {
        Thread3 r3 = new Thread3();
        new Thread(r3).start();
    }
}
