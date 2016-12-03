package com.jrose.jrose.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jrose.jrose.helper.ClassHelper;
import com.jrose.jrose.utils.ReflectionUtil;

/**
 * Bean容器类，用于封装Bean的map集合
 *
 */
public final class BeanContainer {
	/**
	 * Bean容器
	 */
    private static final Map<Class<?>, Object> BEAN_CONTAINER = new HashMap<Class<?>, Object>();
    
	static{
    	Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_CONTAINER.put(beanClass, obj);
        }
    }
    /**
     * 获取 Bean 映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_CONTAINER;
    }

    /**
     * 获取 Bean 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_CONTAINER.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_CONTAINER.get(cls);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_CONTAINER.put(cls, obj);
    }
}
