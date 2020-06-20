package com.jiean.vrcloud.security.annotion;

import java.lang.annotation.*;

/**
 * Created by zhangkang on 2020/6/20
 * 自定义注解，有该注解的缓存方法会抛出异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
