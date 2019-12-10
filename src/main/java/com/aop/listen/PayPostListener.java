package com.aop.listen;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/4/17 16:44
 */
@Component
@Order(2)
public class PayPostListener implements PayListener{
    @Override
    public String execute(Object o) {
        System.out.println(this.getClass().getSimpleName());
        return null;
    }
}
