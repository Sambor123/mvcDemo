package com.jrose.jrose.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 * @author kumaha
 *
 */
 @Target(ElementType.TYPE)
 @Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
