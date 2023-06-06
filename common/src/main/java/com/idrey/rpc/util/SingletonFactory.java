package com.idrey.rpc.util;

import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {
    public static Map<Class,Object> objectFactory = new HashMap<Class, Object>();

    public static <T> T getInstance(Class<T> c){
        synchronized (c) {
            Object instance= objectFactory.get(c);
            if (instance == null) {
                try {
                    instance = c.newInstance();
                    objectFactory.put(c, instance);
                } catch(IllegalAccessException | InstantiationException e){
                    throw new RuntimeException("Exception while creating singleton instance" + e);
                }
            }
            return c.cast(instance);
        }
    }
}
