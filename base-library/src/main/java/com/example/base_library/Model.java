package com.example.base_library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  16:42 <br>
 * 版本： v1.0<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Model {

    String value() default "";
}
