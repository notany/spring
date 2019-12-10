package com.aop.sort;

/**
 * @author ：张红
 * @date ：2019/10/22 17:26
 * @description：睡眠大法好啊，交给时间
 * @version: 睡眠排序法
 */
/**
 * 微信公众号：Java技术栈
 */
public class ArraySort implements Runnable {

    private int number;

    public ArraySort(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{102, 338, 62, 9132, 580, 666};
        for (int number : numbers) {
            new Thread(new ArraySort(number)).start();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.number/10);
            System.out.println(this.number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}