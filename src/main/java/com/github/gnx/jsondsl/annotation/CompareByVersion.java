package com.github.gnx.jsondsl.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 标识字段或get方法 使用 version 模式进行比较
 * @author genx
 * @date 2020/3/20 23:35
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompareByVersion {
}
