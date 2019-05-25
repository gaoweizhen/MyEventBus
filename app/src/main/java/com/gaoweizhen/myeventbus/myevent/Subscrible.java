package com.gaoweizhen.myeventbus.myevent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述:注解处理器
 * data:2019/5/25
 * author:gaoweizhen(gaoweizhen)
 */

@Target(ElementType.METHOD)//注解到方法上面
@Retention(RetentionPolicy.RUNTIME)//什么时候调用
public @interface Subscrible {
    //
    ThreadMode threadMode() default ThreadMode.MAIN;
}
