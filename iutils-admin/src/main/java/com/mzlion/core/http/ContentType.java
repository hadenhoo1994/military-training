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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 针对MIME的一些封装
 *
 * @author mzlion on 2016/8/26.
 */
public final class ContentType {

    // constants、
    public static final ContentType ALL = create("*/*", (Charset) null);
    public static final ContentType TEXT_XML = create("text/xml", StandardCharsets.UTF_8);
    public static final ContentType TEXT_PLAIN = create("text/plain", StandardCharsets.UTF_8);
    public static final ContentType TEXT_HTML = create("text/html", StandardCharsets.UTF_8);
    public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", StandardCharsets.UTF_8);
    public static final ContentType APPLICATION_XML = create("application/xml", StandardCharsets.UTF_8);
    public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset) null);
    public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", StandardCharsets.UTF_8);
    public static final ContentType APPLICATION_JSON = create("application/json", StandardCharsets.UTF_8);

    //imgage
    public static final ContentType IMAGE_PNG = create("image/png", (Charset) null);
    public static final ContentType IMAGE_JPEG = create("image/jpeg", (Charset) null);
    public static final ContentType IMAGE_JPG = create("image/jpeg", (Charset) null);
    public static final ContentType IMAGE_GIF = create("image/gif", (Charset) null);
    public static final ContentType IMAGE_BMP = create("image/bmp", (Charset) null);

    //zip
    public static final ContentType APPLICATION_ZIP = create("application/zip", (Charset) null);
    public static final ContentType APPLICATION_GZ = create("application/x-gzip", (Charset) null);

    //pdf
    public static final ContentType APPLICATION_PDF = create("application/pdf", (Charset) null);

    //ms
    public static final ContentType APPLICATION_DOC = create("application/msword", (Charset) null);
    public static final ContentType APPLICATION_XLS = create("application/vnd.ms-excel", (Charset) null);
    public static final ContentType APPLICATION_PPT = create("application/vnd.ms-powerpoint", (Charset) null);

    //apk or ios
    public static final ContentType APPLICATION_APK = create("application/vnd.android.package-archive", (Charset) null);
    public static final ContentType APPLICATION_IPA = create("application/iphone", (Charset) null);

    // defaults
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;

    //support file extension
    private static final Map<String, ContentType> supportFileExts;

    static {
        Map<String, ContentType> _supportFileExts = new ConcurrentHashMap<>();
        _supportFileExts.put("xml", APPLICATION_XML);
        _supportFileExts.put("json", APPLICATION_JSON);
        _supportFileExts.put("doc", APPLICATION_DOC);
        _supportFileExts.put("docx", APPLICATION_DOC);
        _supportFileExts.put("xls", APPLICATION_XLS);
        _supportFileExts.put("xlsx", APPLICATION_XLS);
        _supportFileExts.put("ppt", APPLICATION_PPT);
        _supportFileExts.put("pptx", APPLICATION_PPT);
        _supportFileExts.put("pdf", APPLICATION_PDF);
        _supportFileExts.put("zip", APPLICATION_ZIP);
        _supportFileExts.put("gzip", APPLICATION_GZ);
        _supportFileExts.put("png", IMAGE_PNG);
        _supportFileExts.put("jpeg", IMAGE_JPEG);
        _supportFileExts.put("jpg", IMAGE_JPG);
        _supportFileExts.put("gif", IMAGE_GIF);
        _supportFileExts.put("html", TEXT_HTML);
        _supportFileExts.put("txt", TEXT_PLAIN);
        _supportFileExts.put("apk", APPLICATION_APK);
        _supportFileExts.put("ipa", APPLICATION_IPA);

        supportFileExts = _supportFileExts;
    }

    private String mimeType;

    private Charset charset;

    ContentType(String mimeType, Charset charset) {
        this.mimeType = mimeType;
        this.charset = charset;
    }

    /**
     * 获取MIME类型
     *
     * @return MIME类型
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * 获取字符集编码
     *
     * @return 字符集编码
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 根据MIME类型创建
     *
     * @param mimeType MIME类型
     * @param charset  字符集编码
     * @return {@link ContentType}
     */
    public static ContentType create(final String mimeType, final Charset charset) {
        return StringUtils.isEmpty(mimeType) ? ContentType.DEFAULT_BINARY :
                new ContentType(mimeType.toLowerCase(Locale.CHINESE), charset);
    }

    /**
     * 根据MIME类型创建
     *
     * @param mimeType MIME类型
     * @param charset  字符集编码
     * @return {@link ContentType}
     */
    public static ContentType create(final String mimeType, final String charset) {
        return create(mimeType, StringUtils.isEmpty(charset) ? null : Charset.forName(charset));
    }

    /**
     * 根据MIME类型创建
     *
     * @param mimeType MIME类型
     * @return {@link ContentType}
     */
    public static ContentType create(final String mimeType) {
        return create(mimeType, (Charset) null);
    }

    /**
     * 通过文件后缀名转为MIME
     *
     * @param fileExt 文件后缀名
     * @return {@link ContentType}
     */
    public static ContentType parseByFileExt(String fileExt) {
        if (StringUtils.isEmpty(fileExt)) return DEFAULT_BINARY;
        fileExt = fileExt.toLowerCase();
        ContentType contentType = supportFileExts.get(fileExt);
        return (contentType == null) ? DEFAULT_BINARY : contentType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append(mimeType);
        if (this.charset != null) {
            sb.append("; charset=").append(this.charset.name());
        }
        return sb.toString();
    }
}
