package com.gaoweizhen.myeventbus.bean;

/**
 * 类描述：传递参数类型
 * data:2019/5/25
 * author:gaoweizhen(gaoweizhen)
 */
public class EventBean {
    private String name;
    private String msg;

    public EventBean(final String name, final String msg) {
        this.name = name;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
