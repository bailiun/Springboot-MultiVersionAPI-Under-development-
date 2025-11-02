package org.bailiun.multipleversionscoexist.Aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * &#064;作者:  bailiun
 * &#064;版本:  1.0.0
 * &#064;功能:  设置一个类内的同名方法的生效优先级,数字越大,优先级越高,默认为0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfacePriority {
    int value() default 0;
}
