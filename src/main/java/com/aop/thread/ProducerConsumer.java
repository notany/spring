package com.aop.thread;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/8/19 13:54
 */
import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer p1 = new Producer(buffer, 1);
        Consumer c1 = new Consumer(buffer, 1);
        p1.start();
        c1.start();
    }
}

/**
 * 缓冲区
 */
class Buffer {
    private List<Integer> data = new ArrayList<>();
    private static final int MAX = 2;
    private static final int MIN = 0;

    public synchronized int get() {
        while (MIN == data.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Integer i = data.remove(0);
        notifyAll();
        return i;
    }

    public synchronized void put(int value) {
        while (MAX == data.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        data.add(value);
        notifyAll();
    }
}

class Consumer extends Thread {
    private Buffer buffer;
    private int number;

    public Consumer(Buffer b, int number) {
        buffer = b;
        this.number = number;
    }

    public void run() {
        int value;
        for (int i = 0; i < 10; i++) {
            // 从缓冲区中获取数据
            value = buffer.get();
            try {
                // 模拟消费数据所用时间
                sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("消费者 #" + this.number + " got: " + value);
        }
    }
}

class Producer extends Thread {
    private Buffer buffer;
    private int number;

    public Producer(Buffer b, int number) {
        buffer = b;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                // 模拟生产数据所用时间
                sleep(500);
            } catch (InterruptedException e) {
            }

            // 将数据放入缓冲区
            buffer.put(i);
            System.out.println("生产者 #" + this.number + " put: " + i);
        }
    }
}