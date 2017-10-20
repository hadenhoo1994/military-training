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

import com.mzlion.core.io.FileUtils;
import com.mzlion.core.io.FilenameUtils;
import com.mzlion.core.io.IOUtils;
import com.mzlion.core.lang.Assert;
import com.mzlion.core.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * {@link HttpServletResponse}相关的工具类。当处理出现异常或失败时，会有日志打印。
 *
 * @author mzlion on 2016-04-15
 */
public class ResponseDownloadUtils {
    //log
    private static Logger logger = LoggerFactory.getLogger(ResponseDownloadUtils.class);

    /**
     * 文件输出，针对Image等支持的格式会直接在浏览器显示，不会提示下载；如果是浏览器不能识别的文件，则浏览器会下载。
     *
     * @param request      HTTP请求对象
     * @param response     HTTP响应对象
     * @param downloadFile 下载文件
     */
    public static void downloadInline(HttpServletRequest request, HttpServletResponse response, File downloadFile) {
        downloadInline(request, response, downloadFile, false);
    }

    /**
     * 文件输出，针对Image等支持的格式会直接在浏览器显示，不会提示下载；如果是浏览器不能识别的文件，则浏览器会下载。
     *
     * @param request      HTTP请求对象
     * @param response     HTTP响应对象
     * @param downloadFile 下载文件
     * @param isDeleted    下载后是否需要删除原始文件
     */
    public static void downloadInline(HttpServletRequest request, HttpServletResponse response, File downloadFile, boolean isDeleted) {
        downloadInline(request, response, downloadFile.getName(), downloadFile, isDeleted);
    }

    /**
     * 文件输出，针对Image等支持的格式会直接在浏览器显示，不会提示下载；如果是浏览器不能识别的文件，则浏览器会下载。
     *
     * @param request         HTTP请求对象
     * @param response        HTTP响应对象
     * @param displayFilename 对外的显示的文件名
     * @param downloadFile    下载文件
     * @param isDeleted       下载后是否需要删除原始文件
     */
    public static void downloadInline(HttpServletRequest request, HttpServletResponse response, String displayFilename, File downloadFile, boolean isDeleted) {
        doDownload(request, response, displayFilename, downloadFile, isDeleted, true);
    }

    /**
     * 下载文件，原文件不会被删除。
     *
     * @param request      HTTP请求对象
     * @param response     HTTP响应对象
     * @param downloadFile 下载文件对象
     */
    public static void download(File downloadFile, HttpServletRequest request, HttpServletResponse response) {
        download(downloadFile, false, request, response);
    }

    /**
     * 下载文件
     *
     * @param downloadFile 要下载的文件
     * @param isDeleted    是否需要删除当前文件
     * @param request      HTTP请求对象
     * @param response     HTTP响应对象
     */
    public static void download(File downloadFile, boolean isDeleted, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(downloadFile, "Download file must not be null.");
        download(downloadFile.getName(), downloadFile, isDeleted, request, response);
    }

    /**
     * 下载文件
     *
     * @param displayName  对外显示的下载文件名
     * @param downloadFile 要下载的文件
     * @param isDeleted    是否需要删除当前文件
     * @param request      HTTP请求对象
     * @param response     HTTP响应对象
     */
    public static void download(String displayName, File downloadFile, boolean isDeleted, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(downloadFile, "Download file must not be null.");
        String fileExt = FilenameUtils.getFileExt(displayName);
        if (fileExt == null) fileExt = FilenameUtils.getFileExt(downloadFile);
        ContentType contentType = ContentType.parseByFileExt(fileExt);
        try (FileInputStream in = new FileInputStream(downloadFile)) {
            download(displayName, in, downloadFile.length(), contentType, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (isDeleted) FileUtils.delete(downloadFile);
    }

    /**
     * 下载文件
     *
     * @param displayName 对外显示的下载文件名
     * @param in          输入流
     * @param request     HTTP请求对象
     * @param response    HTTP响应对象
     */
    public static void download(String displayName, InputStream in, HttpServletRequest request, HttpServletResponse response) {
        Assert.hasLength(displayName, "DisplayName must not be null or empty.");
        String fileExt = FilenameUtils.getFileExt(displayName);
        download(displayName, in, -1, ContentType.parseByFileExt(fileExt), request, response);
    }

    /**
     * 下载文件
     *
     * @param displayName   对外显示的下载文件名
     * @param in            输入流，要下载的内容
     * @param contentLength 内容长度
     * @param contentType   内容类型
     * @param request       HTTP请求对象
     * @param response      HTTP响应对象
     */
    public static void download(String displayName, InputStream in, long contentLength, ContentType contentType,
                                HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(request, "Request must not be null.");
        Assert.notNull(response, "Response must not be null.");
        Assert.hasLength(displayName, "DisplayName must not be null or empty.");
        Assert.notNull(in, "InputStream must not be null.");
        Assert.notNull(contentType, "ContentType may not be null.");

        response.setContentType(contentType.toString());
        if (contentLength != -1) response.setHeader("content-length", String.valueOf(contentLength));
        response.setHeader("Content-disposition", "attachment; filename=" + getDownloadFilename(request, displayName));
        try (OutputStream out = response.getOutputStream()) {
            if (IOUtils.copy(in, out) == -1) {
                throw new IOException("Copy input stream to output stream failed.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //内部方法实现文件下载
    private static void doDownload(HttpServletRequest request, HttpServletResponse response, String filename, File downloadFile,
                                   boolean isDeleted, boolean tryDisplay) {
        Assert.notNull(request, "Request must not be null.");
        Assert.notNull(response, "Response must not be null.");
        Assert.hasLength(filename, "Filename must not be null or empty.");
        Assert.notNull(downloadFile, "Download file must not be null.");

        if (!downloadFile.exists()) {
            throw new IllegalArgumentException("File can not be found.");
        }
        if (downloadFile.isDirectory()) {
            throw new IllegalArgumentException("File exists but is a directory.");
        }
        if (!downloadFile.canRead()) {
            throw new IllegalArgumentException("File exists but cannot read.");
        }

        //设置响应输出类型
        String fileExt = FilenameUtils.getFileExt(filename);
        if (fileExt == null) fileExt = FilenameUtils.getFileExt(downloadFile);
        response.setContentType(ContentType.parseByFileExt(fileExt).toString());
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(downloadFile.length()));

        OutputStream out = null;
        try {
            //设置响应头
            if (tryDisplay) {
                response.setHeader("Content-disposition", "inline; filename=" + getDownloadFilename(request, filename));
            } else {
                response.setHeader("Content-disposition", "attachment; filename=" + getDownloadFilename(request, filename));
            }
            out = response.getOutputStream();
            FileUtils.copyFile(downloadFile, out);
            if (isDeleted) {
                logger.debug(" ===> File [{}] will be deleted.", downloadFile);
                FileUtils.delete(downloadFile);
            }
        } catch (IOException e) {
            logger.error(" ===> Download error->", e);
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 转义文件下载名
     *
     * @param request          HTTP请求对象
     * @param downloadFilename 原下载文件名
     * @return 返回转义后的下载名
     */
    public static String getDownloadFilename(HttpServletRequest request, String downloadFilename) {
        String browser = getBrowser(request);
        if (BROWSER_IE.equals(browser) || BROWSER_EDGE.equals(browser)) {
            //IE浏览器，采用URLEncoder编码
            String filename = toUtf8String(downloadFilename);
            // see http://support.microsoft.com/default.aspx?kbid=816868
            if (filename.length() > 150) {
                // 根据request的locale 得出可能的编码
                filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            return filename;
        }
        if (BROWSER_SAFARI.equals(browser)) {
            //Safari浏览器，采用ISO编码的中文输出
            return new String(downloadFilename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
        //Chrome浏览器，可以采用MimeUtility编码或ISO编码的中文输出
        //FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
        //MimeUtility.encodeText(downloadFilename,)
        return new String(downloadFilename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

    /**
     * 转成UTF8格式的字符串
     */
    private static String toUtf8String(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    logger.error("将文件名中的汉字转为UTF8编码的串时错误，输入的字符串为：" + str);
                    b = new byte[0];
                }
                for (byte aB : b) {
                    int k = aB;
                    if (k < 0)
                        k += 256;
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取浏览器类型
     *
     * @param request HTTP请求对象
     * @return 浏览器类型
     * @see #BROWSER_IE
     * @see #BROWSER_EDGE
     * @see #BROWSER_CHROME
     * @see #BROWSER_SAFARI
     * @see #BROWSER_FIREFOX
     */
    private static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        logger.debug(" ===> User-Agent={}", userAgent);
        if (StringUtils.isEmpty(userAgent)) {
            logger.warn(" ===> User-Agent cannot find.");
            return BROWSER_CHROME;
        }
        /*首先判断是否是IE浏览器*/
        Pattern pattern = Pattern.compile("MISE", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_IE;

        pattern = Pattern.compile("Trident", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_IE;

        pattern = Pattern.compile("Edge", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_EDGE;

        pattern = Pattern.compile("Chrome", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_CHROME;

        pattern = Pattern.compile("Safari", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_SAFARI;

        pattern = Pattern.compile("Firefox", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(userAgent).find()) return BROWSER_FIREFOX;

        logger.warn(" ===> Browser type is unknown.");
        return BROWSER_CHROME;
    }

    private static final String BROWSER_IE = "MSIE";
    private static final String BROWSER_EDGE = "Edge";
    private static final String BROWSER_CHROME = "Chrome";
    private static final String BROWSER_SAFARI = "Safari";
    private static final String BROWSER_FIREFOX = "Firefox";
}
