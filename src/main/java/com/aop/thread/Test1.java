package com.aop.thread;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/8/19 11:25
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        new MyThread("低级", 1).start();
        new MyThread("中级", 5).start();
        new MyThread("高级", 10).start();
    }
}

class MyThread extends Thread {
    public MyThread(String name, int pro) {
        super(name);// 设置线程的名称
        this.setPriority(pro);// 设置优先级
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(this.getName() + "线程第" + i + "次执行！");
            if (i % 5 == 0)
                Thread.yield();
        }
    }
}