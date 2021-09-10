package com.deng.commons.utils;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * cglib 动态代理工具类
 * 参考：https://itsoku.blog.csdn.net/article/details/104726044?spm=1001.2014.3001.5506
 *
 * @Auther ZongCai
 * @Date 2021/8/2
 */
public class CglibProxyUtil implements MethodInterceptor {
    //目标对象
    private Object target;

    public CglibProxyUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        //调用被代理对象（即target）的方法，获取结果
        Object result = method.invoke(target, objects); //@1
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒)：" + (endTime - starTime));
        return result;
    }

    /**
     * 创建任意类的代理对象
     *
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T createProxy(T target) {
        CglibProxyUtil costTimeProxy = new CglibProxyUtil(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }

    /*public static void main(String[] args) {
        IService serviceA = CglibProxy.createProxy(new ServiceA());
        serviceA.m1();
    }*/
}
