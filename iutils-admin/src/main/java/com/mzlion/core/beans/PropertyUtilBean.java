/*
 * Copyright (C) 2016-2017 mzlion(mzllon@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mzlion.core.beans;

import com.mzlion.core.exceptions.FatalBeanException;
import com.mzlion.core.lang.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Javabean属性工具类
 *
 * @author mzlion on 2016/5/22.
 */
public class PropertyUtilBean {

    /**
     * 获取对象{@link PropertyUtilBean}
     *
     * @return {@link PropertyUtilBean}
     */
    public static PropertyUtilBean getInstance() {
        return new PropertyUtilBean();
    }

    /**
     * 缓存已经内省过的Class
     */
    private final WeakHashMap<Class<?>, List<PropertyDescriptor>> descriptorsCache = new WeakHashMap<>();

    /**
     * 私有化构造器
     */
    private PropertyUtilBean() {
    }

    /**
     * 获取Javabean的属性描述列表
     *
     * @param bean 对象内容
     * @return {@code PropertyDescriptor}数组
     * @see #getPropertyDescriptors(Class)
     */
    public List<PropertyDescriptor> getPropertyDescriptors(Object bean) {
        Assert.notNull(bean, "Bean must not be null.");
        return this.getPropertyDescriptors(bean.getClass());
    }

    /**
     * 获取Javabean的属性描述列表
     *
     * @param beanClass 对象内容
     * @return {@code PropertyDescriptor}数组
     */
    public List<PropertyDescriptor> getPropertyDescriptors(Class<?> beanClass) {
        Assert.notNull(beanClass, "BeanClass must not be null.");
        List<PropertyDescriptor> propertyDescriptorList;
        synchronized (descriptorsCache) {
            propertyDescriptorList = descriptorsCache.get(beanClass);
        }
        if (propertyDescriptorList == null) {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                propertyDescriptorList = new ArrayList<>(propertyDescriptors.length);
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (!"class".equals(propertyDescriptor.getName())) {
                        propertyDescriptorList.add(propertyDescriptor);
                    }
                }
                synchronized (descriptorsCache) {
                    descriptorsCache.put(beanClass, propertyDescriptorList);
                }
            } catch (IntrospectionException e) {
                throw new FatalBeanException(String.format("Failed to obtain BeanInfo for class [%s]", beanClass.getName()), e);
            }
        }
        return propertyDescriptorList;
    }

    /**
     * 根据属性名称获取对应属性对象
     *
     * @param bean         实例对象
     * @param propertyName 属性名
     * @return {@linkplain PropertyDescriptor}
     */
    public PropertyDescriptor getPropertyDescriptor(Object bean, String propertyName) {
        Assert.notNull(bean, "Bean must not be null.");
        return this.getPropertyDescriptor(bean.getClass(), propertyName);
    }

    /**
     * 根据属性名称获取对应属性对象
     *
     * @param beanClass    类型
     * @param propertyName 属性名
     * @return {@linkplain PropertyDescriptor}
     */
    public PropertyDescriptor getPropertyDescriptor(Class<?> beanClass, String propertyName) {
        List<PropertyDescriptor> propertyDescriptors = this.getPropertyDescriptors(beanClass);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName)) {
                return propertyDescriptor;
            }
        }
        return null;
    }

    /**
     * 清理缓存
     */
    public void clearDescriptors() {
        descriptorsCache.clear();
    }
}
