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

import com.mzlion.core.exceptions.GenericException;
import com.mzlion.core.json.gson.JsonUtil;
import com.mzlion.core.lang.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 对外输出数据的工具类
 *
 * @author mzlion on 2016/8/26.
 */
public class ResponseMessageUtils {

    /**
     * 对浏览器输出文本内容
     *
     * @param data     待输出的字符串
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithTextPlan(final String data, HttpServletResponse response) {
        _print(data, response, ContentType.TEXT_PLAIN);
    }

    /**
     * 对浏览器输出文本内容
     *
     * @param data     待输出的字符串
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithTextPlan(final String data, HttpServletResponse response, final Charset charset) {
        Assert.notNull(charset, "The parameter[charset] is null.");
        _print(data, response, ContentType.create("text/plain", charset));
    }

    /**
     * 对浏览器输出文本内容
     *
     * @param data     待输出的对象，最终会转为JSON字符串
     * @param response {@linkplain HttpServletResponse}
     * @see JsonUtil#toJson(Object)
     */
    public static void printWithTextPlan(Object data, HttpServletResponse response) {
        printWithTextPlan(JsonUtil.toJson(data), response);
    }

    /**
     * 对浏览器输出JSON
     *
     * @param data     待输出的数据
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithApplicationJson(String data, HttpServletResponse response, Charset charset) {
        _print(data, response, ContentType.create("application/json", charset));
    }

    /**
     * 对浏览器输出JSON
     *
     * @param data     待输出的数据
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithApplicationJson(String data, HttpServletResponse response) {
        _print(data, response, ContentType.APPLICATION_JSON);
    }

    /**
     * 对浏览器输出JSON
     *
     * @param data     待输出的数据
     * @param response {@linkplain HttpServletResponse}
     * @see JsonUtil#toJson(Object)
     */
    public static void printWithApplicationJson(Object data, HttpServletResponse response) {
        _print(JsonUtil.toJson(data), response, ContentType.APPLICATION_JSON);
    }

    /**
     * 对浏览器输出HTML片段
     *
     * @param html     待输出的内容
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithHtml(String html, HttpServletResponse response) {
        _print(html, response, ContentType.TEXT_HTML);
    }

    /**
     * 对浏览器输出HTML片段
     *
     * @param html     待输出的内容
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithHtml(String html, HttpServletResponse response, Charset charset) {
        _print(html, response, ContentType.create("text/html", charset));
    }

    /**
     * 对浏览器输出CSS片段
     *
     * @param css      待输出的内容
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithCss(String css, HttpServletResponse response) {
        printWithCss(css, response, Charset.forName("UTF-8"));
    }

    /**
     * 对浏览器输出CSS片段
     *
     * @param css      待输出的内容
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithCss(String css, HttpServletResponse response, Charset charset) {
        _print(css, response, ContentType.create("text/css", charset));
    }

    /**
     * 对浏览器输出Javascript片段
     *
     * @param js       待输出的内容
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithJavascript(String js, HttpServletResponse response) {
        printWithJavascript(js, response, Charset.forName("UTF-8"));
    }

    /**
     * 对浏览器输出Javascript片段
     *
     * @param js       待输出的内容
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithJavascript(String js, HttpServletResponse response, Charset charset) {
        _print(js, response, ContentType.create("application/javascript", charset));
    }

    /**
     * 对浏览器输出Xml片段
     *
     * @param xml      待输出的内容
     * @param response {@linkplain HttpServletResponse}
     */
    public static void printWithXml(String xml, HttpServletResponse response) {
        _print(xml, response, ContentType.TEXT_XML);
    }

    /**
     * 对浏览器输出Xml片段
     *
     * @param xml      待输出的内容
     * @param response {@linkplain HttpServletResponse}
     * @param charset  字符集编码
     */
    public static void printWithXml(String xml, HttpServletResponse response, Charset charset) {
        _print(xml, response, ContentType.create("text/xml", charset));
    }

    /**
     * 对浏览器输出数据的核心方法
     *
     * @param data        待输出的内容
     * @param response    {@linkplain HttpServletResponse}
     * @param contentType 输出的格式
     */
    private static void _print(String data, HttpServletResponse response, ContentType contentType) {
        Assert.notNull(response, "The parameter[response] is null.");
        if (data == null) data = "";

        try {
            response.setContentType(contentType.toString());
            response.getWriter().write(data);
        } catch (IOException e) {
            throw new GenericException(e);
        }
    }

}
