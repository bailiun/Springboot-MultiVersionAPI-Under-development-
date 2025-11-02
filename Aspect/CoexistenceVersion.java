package org.bailiun.multipleversionscoexist.Aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CoexistenceVersion {
    String version() default "";// 版本名称,建议英文或数字,如果为空则默认使用1,2,3来制定
}
