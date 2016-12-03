package com.jrose.jrose.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action方法注解
 * @author kumaha
 *
 */
 @Target(ElementType.METHOD)
 @Retention(RetentionPolicy.RUNTIME)
public @interface Action {
	 /**
	  * 请求路径
	  */
	 String path();
	 /**
	  * 请求方法
	  */
	 String method();
}
