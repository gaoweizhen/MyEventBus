package com.gaoweizhen.myeventbus.myevent;

import android.os.Handler;
import android.os.Looper;

import com.gaoweizhen.myeventbus.MainActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Phaser;

/**
 * 类描述：定义一个单利的EventBus
 * data:2019/5/25
 * author:gaoweizhen(gaoweizhen)
 */
public class MyEventBus {
    //定义一个容器来存放方法
    private Map<Object, List<SubscribleMethod>> cacheMap;
   private Handler mHandler;
    private static volatile MyEventBus instance;

    private MyEventBus() {
        cacheMap = new HashMap<>();
        mHandler = new Handler();
    }


    public static MyEventBus getDefault() {
        if (instance == null) {
            synchronized (MyEventBus.class) {
                if (instance == null) {
                    instance = new MyEventBus();
                }
            }
        }
        return instance;
    }

    //  在这个方法中去判断所有当前类的所有方法
    public void register(Object obj) {
//      首先在map集合取当前类的集合
        List<SubscribleMethod> list = cacheMap.get(obj);
        if (list == null) {
//            定义方法取
            list = findSubscribleMethods(obj);
//            存到容器
            cacheMap.put(obj, list);
        }

    }

    private List<SubscribleMethod> findSubscribleMethods(final Object obj) {
//        定义一个集合
        List<SubscribleMethod> list = new ArrayList<>();
//
        Class<?> aClass = obj.getClass();
//        可以得到当前类的所有方法 此方法获取不带父类的方法
        Method[] methods = aClass.getDeclaredMethods();
//        //            若是取父类的方法 用下面方法
//        Method[] methods1 = aClass.getMethods();
        while (aClass != null) {//此循环是向父类一层层往上找
//            找父类的时候是要先判断下是否是系统级别的父类
            String name = aClass.getName();
//            判断是系统级别的名字就停止
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            //        遍历所有方法
            for (Method method : methods) {
//            找到带Subscrible注解的方法,
                Subscrible subscrible = method.getAnnotation(Subscrible.class);
                if (subscrible == null) {
                    continue;
                }
//            获取subscrible的参数类型
                Class<?>[] types = method.getParameterTypes();
//       不为空的时候就判断下带有subscrible注解的方法参数类型
                if (types.length == 1) {

                }
                ThreadMode threadMode = subscrible.threadMode();
                SubscribleMethod subscribleMethod = new SubscribleMethod(method, threadMode, types[0]);
                list.add(subscribleMethod);
            }
            //  等于父类的类
            aClass =aClass.getSuperclass();
        }

        return list;
    }

    //  发送evengtbus的方法
    public void post(final Object type) {
//        直接循环map里面的方法，找到对应的然后调用
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();//迭代器
        while (iterator.hasNext()){//循环
            final Object obj = iterator.next();
            List<SubscribleMethod> list = cacheMap.get(obj);//得到集合
            for (final SubscribleMethod subscribleMethod:list) {//遍历
//                判断方法的参数是否相等
                if (subscribleMethod.getType().isAssignableFrom(type.getClass())){
//                    判断下线程
                    switch (subscribleMethod.getThreadMode()){
                        case MAIN:
                            //主线程到  主线程
                            if (Looper.myLooper() == Looper.getMainLooper()){
                                invoke(subscribleMethod,obj,type);
                            }else {
//                                从子线程到主线程
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod,obj,type);
                                    }
                                });
                            }
                            break;
                        case BACHGROUND:  //

                                break;
                    }
                }
            }
        }
    }
//    是一个回调的方法
    private void invoke(final SubscribleMethod subscribleMethod, final Object obj, final Object type) {
        Method method = subscribleMethod.getMethod();
        try {
            method.invoke(obj,type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
