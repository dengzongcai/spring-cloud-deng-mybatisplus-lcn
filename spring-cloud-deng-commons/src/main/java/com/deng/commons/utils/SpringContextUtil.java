package com.deng.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 类描述：获取Spring代理类（上下文）
 * @Auther ZongCai
 * @Date 2021/7/16
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境(上下文对象实例)
     */
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取HttpServletRequest bean对象
     *
     * @return HttpServletRequest对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpServletResponse bean对象
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

    /**
     * 根据bean 名称获取bean对象
     *
     * @param name beanq名称
     * @return  bean对象
     * @throws BeansException
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }


    /**
     * 根据Class<T>获取bean对象
     *
     * @param requiredType Class<T>
     * @return beanq对象
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return  applicationContext.getBean(requiredType);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param       <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取 HttpSession bean对象
     *
     * @return HttpSession
     */
    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getSession();
    }
}
