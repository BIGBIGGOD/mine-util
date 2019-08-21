package com.mine.util.testUtil;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        System.out.printf("我是ThreadTest");
    }
}
