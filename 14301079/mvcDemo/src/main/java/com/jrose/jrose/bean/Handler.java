package com.jrose.jrose.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action 信息
 *
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controller;

    /**
     * Action 方法
     */
    private Method action;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controller = controllerClass;
        this.action = actionMethod;
    }

    public Class<?> getController() {
        return controller;
    }

    public Method getAction() {
        return action;
    }
}
