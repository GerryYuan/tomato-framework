package com.tomato.framework.core.util;

import com.google.common.collect.Sets;
import com.tomato.framework.core.exception.SysException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * @author Created by gerry
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtils {
    
    public static <T> T copy(Object source, Class<T> clazz) {
        if (EmptyUtils.isEmpty(source)) {
            return null;
        }
        Assert.notNull(clazz, "The class must not be null");
        T target = instantiate(clazz);
        BeanCopier copier = BeanCopier.create(source.getClass(), clazz, false);
        copier.copy(source, target, null);
        return target;
    }
    
    /**
     * 基于cglib进行对象复制
     *
     * @param source 被复制的对象
     * @param target 复制对象
     */
    public static void copy(Object source, Object target) {
        if (EmptyUtils.isEmpty(source) || EmptyUtils.isEmpty(target)) {
            return;
        }
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }
    
    /**
     * 基于cglib进行对象组复制
     *
     * @param datas 被复制的对象数组
     * @param clazz 复制对象
     */
    public static <T> List<T> copyByList(List<?> datas, Class<T> clazz) {
        if (EmptyUtils.isEmpty(datas)) {
            return new ArrayList<>();
        }
        Assert.notNull(clazz, "The 'class' must not be null!");
        List<T> result = new ArrayList<>(datas.size());
        for (Object data : datas) {
            result.add(copy(data, clazz));
        }
        return result;
    }
    
    public static <T> Set<T> copyBySet(Collection<?> datas, Class<T> clazz) {
        if (EmptyUtils.isEmpty(datas)) {
            return Sets.newHashSet();
        }
        Assert.notNull(clazz, "The 'class' must not be null!");
        Set<T> result = new HashSet<>(datas.size());
        for (Object data : datas) {
            result.add(copy(data, clazz));
        }
        return result;
    }
    
    /**
     * 通过class实例化对象
     */
    public static <T> T instantiate(Class<T> clazz) {
        Assert.notNull(clazz, "The class must not be null");
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new SysException(clazz + ":Is it an abstract class?", ex);
        } catch (IllegalAccessException ex) {
            throw new SysException(clazz + ":Is the constructor accessible?", ex);
        }
    }
    
    /**
     * Copy the property values of the given source bean into the target bean.
     *
     * @param ignoreNullField 忽略空的属性
     */
    public static void copy(Object source, Object target, boolean ignoreNullField) throws BeansException {
        if (EmptyUtils.isEmpty(source)) {
            return;
        }
        copyProperties(source, target, null, null, ignoreNullField);
    }
    
    /**
     * Copy the property values of the given source bean into the target bean.
     */
    public static <T> void copy(Object source, Class<T> clazz, boolean ignoreNullField) throws BeansException {
        if (EmptyUtils.isEmpty(source)) {
            return;
        }
        Assert.notNull(clazz);
        T target = instantiate(clazz);
        copyProperties(source, target, null, null, ignoreNullField);
    }
    
    private static void copyProperties(Object source, Object target, Class<?> editable, String[] ignoreProperties,
        boolean ignoreNullField) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException(
                    "Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable
                        .getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils
                    .getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils
                        .isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (ignoreNullField) {
                                if (EmptyUtils.isEmpty(value)) {
                                    continue;
                                }
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
    
}
