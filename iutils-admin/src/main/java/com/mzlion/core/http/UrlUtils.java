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
package com.mzlion.core.http;

import com.mzlion.core.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 与URL相关的工具类
 *
 * @author mzlion on 2017/06/12.
 */
public class UrlUtils {

    /**
     * url编码，字符集采用{@code UTF-8}
     *
     * @param url 待编码的url
     * @return 编码后的url
     */
    public static String encode(final String url) {
        return encode(url, StandardCharsets.UTF_8);
    }

    /**
     * url编码，指定字符集
     *
     * @param url     待编码的url
     * @param charset 指定字符集
     * @return 编码后的url
     */
    public static String encode(final String url, Charset charset) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY_STRING;
        }
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        try {
            return URLEncoder.encode(url, charset.name());
        } catch (UnsupportedEncodingException e) {
            //never happen
            throw new RuntimeException(e);
        }
    }

    /**
     * URL解码，字符集采用{@code UTF-8}
     *
     * @param url 待解码的url
     * @return 解码后的url
     */
    public static String decode(final String url) {
        return decode(url, StandardCharsets.UTF_8);
    }

    /**
     * URL解码，指定字符集
     *
     * @param url     待解码的url
     * @param charset 指定字符集
     * @return 解码后的url
     */
    public static String decode(final String url, Charset charset) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY_STRING;
        }
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        try {
            return URLDecoder.decode(url, charset.name());
        } catch (UnsupportedEncodingException e) {
            //never happen
            throw new RuntimeException(e);
        }
    }

}
