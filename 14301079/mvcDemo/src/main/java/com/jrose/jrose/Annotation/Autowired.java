package com.jrose.jrose.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 依赖注入注解
 * @author kumaha
 *
 */
 @Target(ElementType.FIELD)
 @Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {

}