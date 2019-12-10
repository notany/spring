package com.aop.listen;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/4/17 16:33
 */
//@Service("")
public class ZhiYouBao implements PayListener {
    @Override
    public String execute(Object o) {
        System.out.println(this.getClass().getSimpleName());
        return null;
    }
}
