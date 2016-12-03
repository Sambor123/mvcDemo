package com.jrose.jrose.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.jrose.jrose.utils.ClassUtil;
/**
 * 类操作助手类
 */
public final class ClassHelper {

    /**
     * 定义类集合（用于存放所加载的类）
     */
    private static Set<Class<?>> CLASS_SET;
    private static String basePackage;
    private static String controllerPackage;
    private static String servicePackage;
    static {
        basePackage = ConfigHelper.getPackageBase();
        controllerPackage=ConfigHelper.getPackageController();
        servicePackage=ConfigHelper.getPackageService();
        CLASS_SET =ClassUtil.getClassSet(basePackage);
        
    }
    /**
     * 获取应用包名下的所有类
     */
    public static Set<Class<?>> getClassSet() {
    	CLASS_SET = com.jrose.jrose.utils.ClassUtil.getClassSet(basePackage);
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有 Service 类
     */
    public static Set<Class<?>> getServiceClassSet() {
    	CLASS_SET = com.jrose.jrose.utils.ClassUtil.getClassSet(servicePackage);
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(com.jrose.jrose.Annotation.Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有 Controller 类
     */
    public static Set<Class<?>> getControllerClassSet() {
    	CLASS_SET = com.jrose.jrose.utils.ClassUtil.getClassSet(controllerPackage);
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(com.jrose.jrose.Annotation.Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有 Bean 类（包括：Service、Controller 等）
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
