package com.jrose.jrose.helper;

import java.lang.reflect.Field;
import java.util.Map;

import com.jrose.jrose.Annotation.Autowired;
import com.jrose.jrose.core.BeanContainer;
import com.jrose.jrose.utils.ArrayUtil;
import com.jrose.jrose.utils.CollectionUtil;
import com.jrose.jrose.utils.ReflectionUtil;

/**
 * 依赖注入助手类
 *
 */
public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanContainer.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Autowired.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
