package com.deng.commons.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @ClassName: MyBeanUtils
 * @Description: BeanUtils 实现copyProperties 拷贝对象的 null 不复制
 * @author ZongCai
 * @date 2021/7/19
 */
public class MyBeanUtils extends BeanUtils {

    /**
     * 拷贝对象的 null 不复制
     * @param source 被拷贝对象
     * @param target 被粘贴对象
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            // 判断value是否为空
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }
    }

    /**
     *对象拷贝
     */
    public static<T> T deepCopy( T src){
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            T dest = (T) in.readObject();
            return dest;
        }
        catch (IOException ex){
            throw  new RuntimeException(ex.getMessage(),ex);
        }
        catch (ClassNotFoundException ex){
            throw  new RuntimeException(ex.getMessage(),ex);
        }
    }

    /**
     *列表对象拷贝
     */
    public static <T> List<T> deepCopyList(List<T> src){
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        }
        catch (IOException ex){
            throw  new RuntimeException(ex.getMessage(),ex);
        }
        catch (ClassNotFoundException ex){
            throw  new RuntimeException(ex.getMessage(),ex);
        }
    }
}
