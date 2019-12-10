package com.aop.listen;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/4/17 16:34
 */
public class ChangeOrder implements PayListener {
    @Override
    public String execute(Object o) {
        System.out.println(this.getClass().getSimpleName());
        return null;
    }
}
