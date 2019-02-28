package com.github.dragonhht.async;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 异步处理.
 * User: huang
 * Date: 18-6-7
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Async {

    String name() default "";

    int size() default 1;


}
