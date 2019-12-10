package com.aop.listen;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author DataValuable
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext _applicationContext)
            throws BeansException {
        applicationContext = _applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return applicationContext.getBean(name, tClass);
    }

    public static TreeMap<Integer, PayListener> getListener() {
        TreeMap<Integer, PayListener> listenerTreeMap = new TreeMap<>();
        Map<String, PayListener> listeners = applicationContext.getBeansOfType(PayListener.class);
        for(PayListener validator : listeners.values()){
            Order order= validator.getClass().getAnnotation(Order.class);
            listenerTreeMap.put(order.value(), validator);
        }
        return listenerTreeMap;
    }

    public static void execute(Object object) {
        TreeMap<Integer, PayListener> listeners = getListener();
        for(Integer key : listeners.keySet()){
            PayListener listener = listeners.get(key);
            listener.execute(object);
        }
    }
}