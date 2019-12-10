package com.aop.listen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TreeMap;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringUtilsTest {


    @Test
    public void test(){
        TreeMap<Integer, PayListener> listeners = SpringUtils.getListener();
        for(Integer key : listeners.keySet()){
            PayListener listener = listeners.get(key);
            listener.execute(1);
            System.out.println("1111111111111");
        }

    }

}