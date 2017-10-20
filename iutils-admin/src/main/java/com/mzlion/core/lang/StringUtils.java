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
import java.util.List;
import java.util.StringTokenizer;

/**
 * StringUtils工具类主要提供了针对字符串，字符串数组常用的工具类方法.
 *
 * @author mzlion on 2016-05-05 22:03
 */
public class StringUtils {

    //---------------------------------------------------------------------
    // 比较常用的处理字符串String类的操作方法
    // ---------------------------------------------------------------------

    /**
     * 判断字符串是否为{@code null}或空字符串,判断字符串是否为空请使用该方法.
     *
     * @param str 字符串
     * @return 如果字符串是{@code}或空字符串则返回{@code true}，否则返回{@code false}
     */
    public static boolean isEmpty(CharSequence str) {
        return !hasLength(str);
    }

    /**
     * 判断字符串是否为{@code null}或空字符串,判断字符串是否为空请使用该方法.
     *
     * @param str 字符串
     * @return 如果字符串是{@code}或空字符串则返回{@code true}，否则返回{@code false}
     */
    public static boolean isEmpty(String str) {
        return isEmpty((CharSequence) str);
    }

    /**
     * <p>
     * 判断字符串是否为既不为{@code null}，字符串长度也不为0.当传入参数是一个空白符时也返回{@code true}
     * </p>
     * <pre class="code">
     * StringUtils.hasLength(null);// --- false
     * StringUtils.hasLength("");// --- false
     * StringUtils.hasLength(" ");// --- true
     * StringUtils.hasLength("Hi");// --- true
     * </pre>
     *
     * @param str 字符串
     * @return 当且仅当字符串类型不为{@code null},长度也不为0时就返回{@code true},反之则返回{@code false}.
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null) && (str.length() > 0);
    }

    /**
     * 判断字符串是否为既不为{@code null}，字符串长度也不为0.当传入参数是一个空白符时也返回{@code true}
     *
     * @param str 字符串
     * @return 当且仅当字符串类型不为{@code null},长度也不为0时就返回{@code true},反之则返回{@code false}.
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * <p>
     * 判断字符串是否存在文本字符，即字符只是存在一个非空白字符。
     * </p>
     * <pre>
     *  StringUtils.hasText(null); //--- false
     *  StringUtils.hasText(""); //--- false
     *  StringUtils.hasText(" "); //--- false
     *  StringUtils.hasText(" abc"); //--- true
     *  StringUtils.hasText("a"); //--- true
     * </pre>
     *
     * @param str 字符串
     * @return 当字符串至少存在一个不为空白字符时返回{@code true},否则返回{@code false}
     * @see Character#isWhitespace(char)
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        for (int len = str.length(), i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否存在文本字符，即字符只是存在一个非空白字符。
     *
     * @param str 字符串
     * @return 当字符串至少存在一个不为空白字符时返回{@code true},否则返回{@code false}
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * <p>
     * 判断字符串中是否存在空白符,方法会检查字符串是否为{@code null}
     * </p>
     * <pre class="code">
     * StringUtils.containsWhitespace(null); //--- false
     * StringUtils.containsWhitespace(""); //--- false
     * StringUtils.containsWhitespace(" "); //--- true
     * StringUtils.containsWhitespace("ax x"); //--- true
     * </pre>
     *
     * @param str 字符串
     * @return 当字符串存在一个空白符时就返回{@code true},否则返回{@code false}
     * @see Character#isWhitespace(char)
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        for (int len = str.length(), i = 0; i < len; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串中是否存在空白符,方法会检查字符串是否为{@code null}
     *
     * @param str 字符串
     * @return 当字符串存在一个空白符时就返回{@code true},否则返回{@code false}
     * @see #containsWhitespace(CharSequence)
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * <p>
     * 去除字符串左右两侧的空白符
     * </p>
     * <pre>
     *  StringUtils.trim(null); //--- null
     *  StringUtils.trim(""); //--- ""
     *  StringUtils.trim("    "); //--- ""
     *  StringUtils.trim("  a b  "); //--- a b
     * </pre>
     *
     * @param str 字符串
     * @return 返回已经去除左右两边的空白的字符串
     * @see Character#isWhitespace(char)
     */
    public static String trim(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        //删除左边的空白符
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        //删除右边的空白符
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
//
//    /**
//     * 去除字符串中所有的空白字符
//     * </p>
//     * <pre class="code">
//     * StringUtils.trimAllWhiteSpace(null); //--- null;
//     * StringUtils.trimAllWhiteSpace(" a "); //--- a
//     * StringUtils.trimAllWhiteSpace(" a b c "); //--- abc
//     * </pre>
//     *
//     * @param str 字符串
//     * @return 返回去除后的字符串
//     * @see Character#isWhitespace(char)
//     */
//    public static String trimAllWhiteSpace(String str) {
//        if (!hasLength(str)) {
//            return str;
//        }
//        StringBuilder sb = new StringBuilder(str);
//        int index = 0;
//        while (sb.length() > index) {
//            if (Character.isWhitespace(sb.charAt(index))) {
//                sb.deleteCharAt(index);
//            } else {
//                index++;
//            }
//        }
//        return sb.toString();
//    }

    /**
     * <p>
     * 去除字符串左侧的空白字符
     * </p>
     * <pre class="code">
     * StringUtils.trimLeftWhiteSpace(null); //--- null
     * StringUtils.trimLeftWhiteSpace("ab"); //--- null
     * StringUtils.trimLeftWhiteSpace(("  ab c");//--- ab c
     * </pre>
     *
     * @param str 字符串
     * @return 返回去除后的字符串
     */
    public static String trimLeftWhiteSpace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * <p>
     * 去除字符串右侧的空白字符
     * </p>
     * <pre class="code">
     * StringUtils.trimRightWhiteSpace(null); //--- null
     * StringUtils.trimRightWhiteSpace("ab"); //--- null
     * StringUtils.trimRightWhiteSpace((" ab c   ");//---  ab c
     * </pre>
     *
     * @param str 字符串
     * @return 返回去除后的字符串
     */
    public static String trimRightWhiteSpace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * <p>
     * 判断字符串中是否以给定的字符串开头，不考虑大小写问题
     * </p>
     * <pre class="code">
     * StringUtils.startsWithIgnoreCase("abcd",null); //--- false
     * StringUtils.startsWithIgnoreCase("abcd","ab"); //--- true
     * StringUtils.startsWithIgnoreCase("abcd","bc"); //--- false
     * StringUtils.startsWithIgnoreCase("abcd","AB"); //--- true
     * StringUtils.startsWithIgnoreCase(null,"ab"); //--- false
     * </pre>
     *
     * @param str    字符串
     * @param prefix 给定的字符串，以该字符串开头
     * @return 如果字符串{code str}是以{@code prefix}开头(忽略大小写)则返回{@code true},否则返回{@code false}
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if ((null == str) || (null == prefix)) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lowerCaseStr = str.substring(0, prefix.length()).toLowerCase();
        String lowerCasePrefix = prefix.toLowerCase();
        return lowerCasePrefix.equals(lowerCaseStr);
    }

    /**
     * <p>
     * 判断字符串中是否以给定的字符串结尾,不考虑大小写问题.
     * </p>
     * <pre class="code">
     * StringUtils.endsWithIgnoreCase("ddcbab",null); //--- false
     * StringUtils.endsWithIgnoreCase("ddcbab","ab"); //--- true
     * StringUtils.endsWithIgnoreCase("ddcbab","bc"); //--- false
     * StringUtils.endsWithIgnoreCase("ddcbab","AB"); //--- true
     * StringUtils.endsWithIgnoreCase(null,"ab"); //--- false
     * </pre>
     *
     * @param str    字符串
     * @param suffix 给定的字符串，以该字符串开头
     * @return 如果字符串{code str}是以{@code prefix}开头(忽略大小写)则返回{@code true},否则返回{@code false}
     * @see String#endsWith(String)
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if ((null == str) || (null == suffix)) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }
        String lowerStr = str.substring(str.length() - suffix.length()).toLowerCase();
        String lowerSuffix = suffix.toLowerCase();
        return lowerSuffix.equals(lowerStr);
    }

//    /**
//     * <p>
//     * 判断字符串从指定位置是否和给定的字符串匹配
//     * </p>
//     * <pre class="code">
//     * StringUtils.matchSubstring("abcdef",2,"cd"); //--- true
//     * StringUtils.matchSubstring("abc",2,"abcd"); //--- false
//     * </pre>
//     *
//     * @param str       字符串
//     * @param index     匹配的起始位置
//     * @param substring 匹配的字符串
//     * @return 匹配成功则返回{@code true},否则返回{@code false}
//     */
//    public static boolean matchSubstring(CharSequence str, int index, CharSequence substring) {
//        if (str == null || null == substring) {
//            return false;
//        }
//        if (str.length() < substring.length()) {
//            return false;
//        }
//        if (index < 0 || index > (str.length() - substring.length())) {
//            return false;
//        }
//        for (int subLen = substring.length(), j = 0; j < subLen; j++) {
//            int i = j + index;
//            if (str.charAt(i) != substring.charAt(j)) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * <p>
     * 将字符串中所有出现{@code oldPattern}替换为{@code newPattern}.
     * </p>
     * <pre class="code">
     * StringUtils.replace("com.mzlion.utility.","l","x"); //--- "hexxo worxd"
     * </pre>
     *
     * @param str        字符串
     * @param oldPattern 需要替换的字符串
     * @param newPattern 新的字符串
     * @return 返回替换后的字符串
     */
    public static String replace(String str, String oldPattern, String newPattern) {
        if ((!hasLength(str)) || (!hasLength(oldPattern)) || (null == newPattern)) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = str.indexOf(oldPattern, pos);
        int patternLen = oldPattern.length();

        while (index >= 0) {
            sb.append(str.substring(pos, index));
            sb.append(newPattern);
            pos = index + patternLen;
            index = str.indexOf(oldPattern, pos);
        }
        sb.append(str.substring(pos));
        return sb.toString();
    }

    /**
     * <p>
     * 将字符串{@code str}中的出现了指定子串{@code pattern}全部删除,删除的字符串不支持正则表达式.
     * </p>
     * <pre class="code">
     * StringUtils.delete("hello world","l"); //--- "heo word";
     * </pre>
     *
     * @param str     字符串
     * @param pattern 要删除的字符串
     * @return 返回删除后的字符串
     */
    public static String delete(String str, String pattern) {
        return replace(str, pattern, "");
    }

    /**
     * <p>
     * 将字符串的第一个字符(必须在{@linkplain Character#toUpperCase(char)}中，否则就不会改变)转换为大写.
     * </p>
     * <pre class="code">
     * StringUtils.capitalize("she si just a kid"); //--- "She is just a kid"
     * </pre>
     *
     * @param str 字符串
     * @return 返回字符串中的第一个字符转换为大写
     */
    public static String capitalize(String str) {
        return changeCharacterCase(str, 0, true);
    }

    /**
     * <p>
     * 将字符串的第一个字符(必须在{@linkplain Character#toLowerCase(char)}中，否则就不会改变)转换为小写.
     * </p>
     * <pre class="code">
     * StringUtils.unCapitalize("Hello"); //--- "hello"
     * </pre>
     *
     * @param str 字符串
     * @return 返回字符串中的第一个字符转换为小写
     */
    public static String unCapitalize(String str) {
        return changeCharacterCase(str, 0, false);
    }

    /**
     * 将字符串中指定位置({@code index})的字符(必须在{@linkplain Character#toLowerCase(char)})转为大(小)写,否则就不会改变
     * <pre class="code">
     * StringUtils.changeCharacterCase("Hello",2,true); //--- "HeLlo"
     * </pre>
     *
     * @param str     字符串
     * @param index   大小写更改位置
     * @param capital 大写还是小写，当值为{@code true}时则大写，值为{@code false}时则小写
     * @return 返回修改后的字符串
     */
    public static String changeCharacterCase(String str, int index, boolean capital) {
        if (!hasLength(str)) {
            return str;
        }
        int pos;
        if (index < 0)
            pos = str.length() + index;
        else {
            pos = index;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str.subSequence(0, pos));
        if (capital) {
            sb.append(Character.toUpperCase(str.charAt(pos)));
        } else {
            sb.append(Character.toLowerCase(str.charAt(pos)));
        }
        sb.append(str.substring(pos + 1));
        return sb.toString();
    }

    //---------------------------------------------------------------------
    // 比较常用的处理字符串String类数组的操作方法
    // ---------------------------------------------------------------------

    /**
     * 从识别的第一处分割字符串
     *
     * @param toSplit   要分隔的字符串
     * @param delimiter 分隔符
     * @return 返回分割后的字符串数组
     */
    public static String[] splitAtFirst(String toSplit, String delimiter) {
        if (StringUtils.isEmpty(toSplit)) {
            return new String[0];
        }
        if (StringUtils.isEmpty(delimiter)) {
            return new String[0];
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return new String[0];
        }
        return new String[]{toSplit.substring(0, offset), toSplit.substring(offset + delimiter.length())};
    }

    /**
     * 字符串分割，默认采用了英文半角逗号分割
     *
     * @param toSplit 待分割的字符串
     * @return 分割后的字符串数组
     * @see #split(String, String)
     */
    public static String[] split(String toSplit) {
        return split(toSplit, ",");
    }

    /**
     * 字符串分割
     *
     * @param toSplit   要分隔的字符串
     * @param delimiter 分隔符
     * @return 分割后的字符串数组
     * @see StringTokenizer
     */
    public static String[] split(String toSplit, String delimiter) {
        if (StringUtils.isEmpty(toSplit)) {
            return new String[0];
        }
        if (StringUtils.isEmpty(delimiter)) {
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(toSplit, delimiter);
        List<String> values = new ArrayList<>();
        String token;
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if (StringUtils.hasText(token)) {
                values.add(token);
            }
        }
        if (CollectionUtils.isNotEmpty(values)) {
            String[] splitOfStrs = new String[values.size()];
            values.toArray(splitOfStrs);
            return splitOfStrs;
        }
        return new String[0];
    }

    /**
     * 将驼峰式命名的字符串转换为下划线字符串。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * <pre>
     *     例如：HelloWorld to hello_world
     * </pre>
     *
     * @param name 驼峰式的字符串
     * @return 返回下划线式的字符串
     */
    public static String toUnderline(String name) {
        return toUnderline(name, false);
    }

    /**
     * 将驼峰式命名的字符串转换为下划线字符串。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     *
     * @param name  驼峰式的字符串
     * @param upper 如果为{@code true}则将字符串均转为大写
     * @return 返回下划线的字符串
     */
    public static String toUnderline(String name, boolean upper) {
        if (!hasText(name)) {
            return null;
        }
        StringBuilder builder = new StringBuilder(name.length());
        char[] chars = name.toCharArray();
        char ch;
        for (int i = 0, length = chars.length; i < length; i++) {
            ch = chars[i];
            if (Character.isUpperCase(ch)) {
                if (i != 0) {
                    builder.append("_");
                }
                builder.append(upper ? ch : Character.toLowerCase(ch));
            } else {
                builder.append(upper ? Character.toUpperCase(ch) : ch);
            }
        }
        return builder.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
     * <pre>
     *     例如：HELLO_WORLD - helloWorld
     * </pre>
     *
     * @param name 下划线式字符串
     * @return 返回驼峰式字符串
     */
    public static String toCamelCase(String name) {
        if (!hasText(name)) {
            return name;
        }
        boolean headerFirstUnderline = false;
        StringBuilder builder = new StringBuilder(name.length());
        char[] chars = name.toCharArray();
        char ch;

        for (int i = 0, length = chars.length; i < length; i++) {
            ch = chars[i];

            if (ch == '_') {
                if (chars[i + 1] == '_') {
                    if (i == 0) headerFirstUnderline = true;
                    continue;
                } else if (i == 0) {
                    headerFirstUnderline = true;
                }
                i++;
                ch = chars[i];
                if (headerFirstUnderline) {
                    builder.append(Character.toLowerCase(ch));
                    headerFirstUnderline = false;
                } else builder.append(Character.toUpperCase(ch));
            } else {
                builder.append(Character.toLowerCase(ch));
            }
        }
        return builder.toString();
    }

    public static final String EMPTY_STRING = "";
}
