package com.aop.lamda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：张红
 * @date ：2019/11/20 15:58
 * @description： list map
 * @version:
 */
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();//存放apple对象集合

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果1", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        list.add(apple1);
        list.add(apple12);
        list.add(apple2);
        list.add(apple3);
        /**
         * 加减乘除
         */

        System.out.println(list.stream().mapToDouble(Apple::getNum).sum());//和
        System.out.println(list.stream().mapToDouble(Apple::getNum).max());//最大
        System.out.println(list.stream().mapToDouble(Apple::getNum).min());//最小
        System.out.println(list.stream().mapToDouble(Apple::getNum).average());//平均值
        // 将user对象的mongey取出来map为Bigdecimal,使用reduce聚合函数,实现累加器
        BigDecimal add = list.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(add);

        /**
         * map
         * 取出某一属性
         */
        List<String> collect = list.stream().map(e -> e.getNum().toString()).collect(Collectors.toList());
        List<Integer> integers = list.stream().map(Apple::getNum).collect(Collectors.toList());

        /**
         * collect
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = list.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        Map<Integer, String> nameMap = list.stream().collect(Collectors.toMap(Apple::getId, a -> a.getName(), (k1, k2) -> k1));
        System.out.println(appleMap);
        System.out.println(nameMap);

        /**
         * collect 以ID分组 Map<Integer,List<Apple>>
         */
        Map<Integer, List<Apple>> groupBy = list.stream().collect(Collectors.groupingBy(Apple::getId));

        System.out.println("groupBy:"+groupBy);

        /**
         * filter 过滤出符合条件的数据
         */
        List<Apple> filterList = list.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());

        System.out.println("filterList:"+filterList);


        /**
         * forEach/collect
         * 字符串连接
         */

        List<Integer> string = new ArrayList<>();
        string.add(1);
        string.add(2);
        string.add(3);

        StringBuilder b = new StringBuilder();
        string.forEach(b::append);
        String s = string.stream().map(e -> e.toString()).reduce("", String::concat);
        String ss = string.stream().map(i -> i.toString()).collect(Collectors.joining(","));

        System.out.println(b);
        System.out.println(s);
        System.out.println(ss);

        List<String> strList = Arrays.asList("AA","BB","CC");

        String newStr = strList.stream().collect(Collectors.joining(","));

        System.out.println("Output:" + newStr);// Output:AA,BB,CC

    }
}
