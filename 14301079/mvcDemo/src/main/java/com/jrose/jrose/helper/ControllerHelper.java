package com.jrose.jrose.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jrose.jrose.Annotation.Action;
import com.jrose.jrose.bean.Handler;
import com.jrose.jrose.bean.Request;
import com.jrose.jrose.utils.ArrayUtil;
import com.jrose.jrose.utils.CollectionUtil;

/**
 * 控制器助手类
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> REQUEST_HANDLE_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String actionMethod = action.method();
                            String actionPath=action.path();
                            if (actionMethod.matches("\\w+")&&actionPath.matches("/\\w*")) {
                            	Request request = new Request(actionMethod, actionPath);
                                Handler handler = new Handler(controllerClass, method);
                                REQUEST_HANDLE_MAP.put(request, handler);
							}
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return REQUEST_HANDLE_MAP.get(request);
    }
}
