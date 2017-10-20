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

import java.util.regex.Pattern;

/**
 * 常用的验证工具
 *
 * @author mzlion on 2016/11/9.
 */
public class ValidationUtils {

    /**
     * 手机号简单校验正则
     */
    public static final Pattern MOBILE = Pattern.compile("1\\d{10}");
    /**
     * 手机号较为严格校验正则
     */
    public static final Pattern STRICT_MOBILE = Pattern.compile("1[34578]\\d{9}");
    /**
     * IPv4的正则
     */
    public static final Pattern IPV4_REGEX = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");

    /**
     * IPv6的正则
     */
    public static final Pattern IPV6_REGEX = Pattern.compile("^([\\da-fA-F]{1,4}:){6}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^::([\\da-fA-F]{1,4}:){0,4}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:):([\\da-fA-F]{1,4}:){0,3}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){2}:([\\da-fA-F]{1,4}:){0,2}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){3}:([\\da-fA-F]{1,4}:){0,1}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){4}:((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$|^:((:[\\da-fA-F]{1,4}){1,6}|:)$|^[\\da-fA-F]{1,4}:((:[\\da-fA-F]{1,4}){1,5}|:)$|^([\\da-fA-F]{1,4}:){2}((:[\\da-fA-F]{1,4}){1,4}|:)$|^([\\da-fA-F]{1,4}:){3}((:[\\da-fA-F]{1,4}){1,3}|:)$|^([\\da-fA-F]{1,4}:){4}((:[\\da-fA-F]{1,4}){1,2}|:)$|^([\\da-fA-F]{1,4}:){5}:([\\da-fA-F]{1,4})?$|^([\\da-fA-F]{1,4}:){6}:$");

    /**
     * 判断是否是手机号(中国)
     *
     * @param mobile 手机号
     * @return 验证成功则返回{@code true},否则返回{@code false}
     */
    public static boolean isMobile(String mobile) {
        return isMatchRegex(MOBILE, mobile);
    }

    /**
     * 判断是否是手机号(中国),使用较为严格的规则,但正则具有时效性,如果新增了号段则校验不通过.
     *
     * @param mobile 手机号
     * @return 验证成功则返回{@code true},否则返回{@code false}
     */
    public static boolean isStrictMobile(String mobile) {
        return isMatchRegex(STRICT_MOBILE, mobile);
    }

    /**
     * 判断是否是IPv4
     *
     * @param ip ip
     * @return 验证成功则返回{@code true},否则返回{@code false}
     */
    public static boolean isIPv4(String ip) {
        return isMatchRegex(IPV4_REGEX, ip);
    }

    public static boolean isIPv6(String ip) {
        return isMatchRegex(IPV6_REGEX, ip);
    }

    /**
     * 校验
     *
     * @param pattern 正则
     * @param value   待验证的字符串
     * @return 验证成功则返回{@code true},否则返回{@code false}
     */
    private static boolean isMatchRegex(Pattern pattern, String value) {
        if (pattern == null || StringUtils.isEmpty(value)) return false;//空字符串则直接返回{@code false}
        //if () return true; //正则表达式为{@code null}则全匹配
        return pattern.matcher(value).matches();
    }
}
