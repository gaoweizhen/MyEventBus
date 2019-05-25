package com.gaoweizhen.myeventbus.myevent;

import java.lang.reflect.Method;

/**
 * 类描述：存放所有的方法的实体类
 * data:2019/5/25
 * author:gaoweizhen(gaoweizhen)
 */
public class SubscribleMethod {
//    回调方法
    private Method method;
//    线程模式
    private ThreadMode threadMode;
    //    方法的参数类型
    private Class<?> type;
    public Method getMethod() {
        return method;
    }

    public void setMethod(final Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(final ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(final Class<?> type) {
        this.type = type;
    }

    public SubscribleMethod(final Method method, final ThreadMode threadMode, final Class<?> type) {
        this.method = method;
        this.threadMode = threadMode;
        this.type = type;
    }
}
