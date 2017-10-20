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
package com.mzlion.core.reflect;

import com.mzlion.core.exceptions.FatalBeanException;
import com.mzlion.core.lang.ArrayUtils;
import com.mzlion.core.lang.Assert;
import com.mzlion.core.lang.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Class反射工具类
 *
 * @author mzlion on 2016/6/7.
 */
public class ReflectionUtils {
    //slf4j
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    private ReflectionUtils() {
        throw new UnsupportedOperationException();
    }

    private static final WeakHashMap<Class<?>, List<Field>> declaredFieldsCache = new WeakHashMap<>(255);

    private static final WeakHashMap<Class<?>, Method[]> declaredMethodCache = new WeakHashMap<>(255);

    /**
     * 获取Class定义的属性列表
     *
     * @param targetClass 目标类型
     * @return 返回属性列表
     */
    public static List<Field> getDeclaredFields(Class<?> targetClass) {
        Assert.notNull(targetClass, "Target class must not be null.");
        List<Field> declaredFields = declaredFieldsCache.get(targetClass);
        if (declaredFields == null) {
            logger.debug(" It cannot find field list in cache for targetClass->{}", targetClass);
            declaredFields = new ArrayList<>();
            Class<?> currentClass = targetClass;
            Field[] fields;
            while (currentClass != null) {
                fields = currentClass.getDeclaredFields();
                Collections.addAll(declaredFields, fields);
                currentClass = currentClass.getSuperclass();
            }
            logger.debug(" Reflect fields success,then put then into cache container.targetClass->{},fields->{}",
                    targetClass, declaredFields);
            declaredFieldsCache.put(targetClass, declaredFields.size() == 0 ? Collections.<Field>emptyList() : declaredFields);
        } else {
            logger.debug(" ===> The cache exist class [{}] reflection.", targetClass.getName());
        }
        return declaredFields;
    }

    /**
     * 根据过滤器过滤属性,最后返回类解析的属性列表
     *
     * @param targetClass  目标类型
     * @param fieldFilters 过滤器
     * @return 过滤后的属性列表
     */
    public static List<Field> getDeclaredFields(Class<?> targetClass, final FieldFilter... fieldFilters) {
        List<Field> fieldList = getDeclaredFields(targetClass);
        if (CollectionUtils.isEmpty(fieldList)) return fieldList;
        if (ArrayUtils.isEmpty(fieldFilters)) return fieldList;

        List<Field> filterFieldList = new ArrayList<>(fieldList.size());
        boolean filter;
        for (Field field : fieldList) {
            filter = false;
            for (FieldFilter fieldFilter : fieldFilters) {
                if (fieldFilter.matches(field)) {
                    filter = true;
                    break;
                }
            }
            if (!filter) filterFieldList.add(field);
        }
        return filterFieldList;
    }

    /**
     * 根据{@code fieldName}在{@code targetClass}查找，支持父类的属性查找。
     *
     * @param targetClass the class to introspect.
     * @param fieldName   the name of the field.
     * @return the Field object, or {@code null} if not found.
     */
    public static Field findField(Class<?> targetClass, String fieldName) {
        return findField(targetClass, fieldName, null);
    }

    /**
     * 根据{@code fieldName}或{@code fieldType}在{@code targetClass}查找，支持父类的属性查找。
     *
     * @param targetClass the class to introspect.
     * @param fieldName   the name of the field.
     * @param fieldType   the type of the field.
     * @return the Field object, or {@code null} if not found.
     */
    public static Field findField(Class<?> targetClass, String fieldName, Class<?> fieldType) {
        Assert.notNull(targetClass, "Target class must not be null.");
        Assert.isTrue(fieldName != null || fieldType != null, "Either fieldName or fieldType of the field must be specified.");
        Class<?> searchType = targetClass;
        while (Object.class != searchType && searchType != null) {
            List<Field> declaredFields = getDeclaredFields(searchType);
            for (Field declaredField : declaredFields) {
                if ((fieldName == null || fieldName.equals(declaredField.getName())) &&
                        (fieldType == null || fieldType.equals(declaredField.getType()))) return declaredField;
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * 通过内省的机制实现Bean的<code>toString()</code>值,自动过滤<code>static</code>和<code>transient</code>修饰的字段
     *
     * @param object 对象
     * @return <code>toString</code>值
     */
    public static String toString(final Object object) {
        return toString(object, false, false);
    }

    /**
     * 通过内省的机制实现Bean的<code>toString()</code>值
     *
     * @param object        对象
     * @param outputStatics 是否输出被<code>static</code>修饰的属性，值为{@code true}则输出，否则不输出
     * @return <code>toString</code>值
     */
    public static String toString(final Object object, final boolean outputStatics) {
        return toString(object, outputStatics, true);
    }

    /**
     * 通过内省的机制实现Bean的<code>toString()</code>值
     *
     * @param object           对象
     * @param outputStatics    是否输出被<code>static</code>修饰的属性，值为{@code true}则输出，否则不输出
     * @param outputTransients 是否输出被<code>transient</code>修饰的属性，值为{@code true}则输出，否则不输出
     * @return <code>toString</code>值
     */
    public static String toString(final Object object, final boolean outputStatics, final boolean outputTransients) {
        if (object == null) {
            return null;
        }
        Class<?> targetClass = object.getClass();
        if (Number.class.isAssignableFrom(targetClass) || String.class.equals(targetClass) ||
                Boolean.class.isAssignableFrom(targetClass) || Character.class.isAssignableFrom(targetClass)) {
            return String.format("%s[%s]", targetClass.getSimpleName(), object);
        }

        List<FieldFilter> fieldFilterList = new ArrayList<>();
        if (!outputStatics) fieldFilterList.add(new StaticFieldFilter());
        if (!outputTransients) fieldFilterList.add(new TransientFieldFilter());

        FieldFilter[] fieldFilters = new FieldFilter[fieldFilterList.size()];
        fieldFilterList.toArray(fieldFilters);
        List<Field> declaredFields = getDeclaredFields(targetClass, fieldFilters);
        Field[] fieldArray = new Field[declaredFields.size()];
        declaredFields.toArray(fieldArray);
        StringBuilder builder = new StringBuilder(targetClass.getSimpleName());
        builder.append('{');
        AccessibleObject.setAccessible(fieldArray, true);
        for (int i = 0, length = fieldArray.length; i < length; i++) {
            final Field field = fieldArray[i];
            if (i > 0) {
                builder.append(", ");
            }
            try {
                Object value = field.get(object);
                if (Number.class.isAssignableFrom(field.getType())) {
                    builder.append(field.getName()).append('=').append(value);
                } else if (String.class.isAssignableFrom(field.getType())) {
                    builder.append(field.getName()).append("='").append(value).append("'");
                } else {
                    builder.append(field.getName()).append('=').append(value);
                }
            } catch (ReflectiveOperationException e) {
                throw new FatalBeanException(e);
            }
        }
        builder.append('}');
        return builder.toString();
    }

}
