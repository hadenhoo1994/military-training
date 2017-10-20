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
package com.mzlion.core.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组工具类
 *
 * @author mzlion on 2016-05-05 22:15
 */
public class ArrayUtils {

    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>判断是否是数组类型</p>
     * <pre class="code">
     * ObjectUtils.isArray(null); //--- false;
     * ObjectUtils.isArray(new String[]{"aa","bb"}); //--- true
     * </pre>
     *
     * @param obj 对象
     * @return 如果是数组类型则返回{@code true},否则返回{@code false}
     */
    public static boolean isArray(Object obj) {
        return (obj != null && obj.getClass().isArray());
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final boolean[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或者为{@code null}
     *
     * @param array 数组
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static boolean isEmpty(final double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否为空或为{@code null}
     *
     * @param array 数组
     * @param <T>   泛型类
     * @return 当数组为空或{@code null}时返回{@code true}
     */
    public static <T> boolean isEmpty(final T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断是否不为空或不为{@code null}
     *
     * @param array 数组
     * @param <T>   泛型类
     * @return 当数组不为空且不是{@code null}时返回{@code true}
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return array != null && array.length != 0;
    }

    /**
     * 判断数组里的元素是否为空
     *
     * @param array 数组
     * @param <T>   泛型类
     * @return 如果数组的元素中存在{@code null}或空则返回{@code true}
     */
    public static <T> boolean isEmptyElement(final T[] array) {
        boolean empty = isEmpty(array);
        if (!empty) {
            empty = false;
            for (T element : array) {
                if (element == null) {
                    empty = true;
                } else {
                    if (element instanceof String) {
                        empty = StringUtils.isEmpty((String) element);
                    }
                }
                if (empty) {
                    return true;
                }
            }
        }
        return empty;
    }

    /**
     * <p>判断数组中是否包含了指定的元素</p>
     * <pre class="code">
     * ObjectUtils.containsElement(new String[]{"aaaa","bbb","cc",null},null); //--- true
     * ObjectUtils.containsElement(new String[]{"aaaa","bbb","cc"},"cc"); //--- true
     * ObjectUtils.containsElement(new String[]{"aaaa","bbb","cc",null},"xx"); //--- false
     * </pre>
     *
     * @param array   数组
     * @param element 检查的元素对象
     * @param <T>     泛型类型声明
     * @return 如果数组中存在则返回{@code true},否则返回{@code false}
     * @see ObjectUtils#nullSafeEquals(Object, Object)
     */
    public static <T> boolean containsElement(T[] array, T element) {
        if (isEmpty(array)) {
            return false;
        }
        for (Object arrayEle : array) {
            if (ObjectUtils.nullSafeEquals(arrayEle, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(Object[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(Object[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(String.valueOf(array[i]));
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(char[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(char[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(boolean[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(boolean[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(byte[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(byte[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(short[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(short[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(int[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(int[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(long[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(long[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(float[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(float[] array) {
        return toString(array, ",");
    }

    /**
     * 将数组转为字符串，使用英文半角逗号连接
     *
     * @param array     数组
     * @param delimiter 数组元素分隔符
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(double[] array, String delimiter) {
        if (isEmpty(array)) {
            return "";
        }
        if (delimiter == null) {
            delimiter = "";
        }
        StringBuilder builder = new StringBuilder();
        int length = array.length - 1;
        for (int i = 0; ; i++) {
            builder.append(array[i]);
            if (i == length) {
                return builder.toString();
            }
            builder.append(delimiter);
        }
    }

    /**
     * 将数组转为字符串，使用{@code delimiter}将元素连接起来
     *
     * @param array 数组
     * @return 如果数组为空则返回空字符串
     */
    public static String toString(double[] array) {
        return toString(array, ",");
    }

    /**
     * <p>
     * 向数组中追加一个字符串，返回一个新的字符串数组.
     * </p>
     * <pre class="code">
     * StringUtils.addStringToArray(new String[]{"hello"},"world"); //--- [hello,world]
     * StringUtils.addStringToArray(null,"hah"); //--- [haha]
     * </pre>
     *
     * @param array  原始数组
     * @param addStr 追加的字符串
     * @return 返回一个新的数组，该数组永远不为{@code null}
     */
    public static String[] addStringToArray(String[] array, String addStr) {
        if (ArrayUtils.isEmpty(array)) {
            return new String[]{addStr};
        }
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = addStr;
        return newArray;
    }

    /**
     * <p>
     * 将两个字符串数组对接,返回一个新的字符串数组.
     * </p>
     * <pre class="code">
     * StringUtils.concatStringArrays(new String[]{"girl","women"},new String[]{"boy","girl"}); //--- [girl,women,boy,girl]
     * </pre>
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 返回一个新的字符串数组
     */
    public static String[] concatStringArrays(String[] arr1, String[] arr2) {
        if (ArrayUtils.isEmpty(arr1)) {
            return arr2;
        }
        if (ArrayUtils.isEmpty(arr2)) {
            return arr1;
        }
        String[] newArr = new String[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
        return newArr;
    }

    /**
     * <p>
     * 合并两个数组,其中数组元素重复的直计算一次.
     * </p>
     * <pre class="code">
     * StringUtils.mergeStringArrays(new String[]{"girl","women"},new String[]{"boy","girl"}); //--- [girl,women,boy]
     * </pre>
     *
     * @param array1 数组
     * @param array2 数组
     * @return 返回合并后的新数组
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (ArrayUtils.isEmpty(array1)) {
            return array2;
        }
        if (ArrayUtils.isEmpty(array2)) {
            return array1;
        }
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(array1));
        for (String str : array2) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return CollectionUtils.toStringArray(result);
    }

    /**
     * <p>
     * 数组排序
     * </p>
     * <pre class="code">
     * StringUtils.sortStringArray(new String[]{"hello","boy","amazing"}); //--- [amazing,boy,hello]
     * </pre>
     *
     * @param array 数组
     * @return 排序后的字符串数组
     */
    public static String[] sortStringArray(String[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return new String[0];
        }
        Arrays.sort(array);
        return array;
    }

}
