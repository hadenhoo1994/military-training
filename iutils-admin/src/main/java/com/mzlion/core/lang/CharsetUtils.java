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

/**
 * GBK和UTF8之间的相互转码，还支持转Unicode码，在处理乱码时是一种解决方案
 *
 * @author mzlion on 2016/07/06.
 */
public class CharsetUtils {

    /**
     * gbk编码的字符串转为UTF8编码的字符串
     *
     * @param gbkStr gbk编码的字符串
     * @return UTF8编码的字符串
     */
    public static String gbk2utf8(String gbkStr) {
        String temp = gbk2Unicode(gbkStr);
        return unicodeToUtf8(temp);
    }

    /**
     * UTF8编码的字符串转为gbk编码的字符串
     *
     * @param utf8Str UTF8编码的字符串
     * @return gbk编码的字符串
     */
    public static String utf82gbk(String utf8Str) {
        String temp = utf8ToUnicode(utf8Str);
        return unicode2GBK(temp);
    }

    /**
     * GBK编码的字符串转为Unicode编码的字符串
     *
     * @param gbkStr GBK编码的字符串
     * @return Unicode编码的字符串
     */

    public static String gbk2Unicode(String gbkStr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < gbkStr.length(); i++) {
            char chr1 = gbkStr.charAt(i);

            if (!isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }
            result.append("\\u").append(Integer.toHexString((int) chr1));
        }

        return result.toString();
    }

    /**
     * Unicode编码的字符串转为GBK编码的字符串
     *
     * @param unicodeStr Unicode编码的字符串
     * @return GBK编码的字符串
     */
    public static String unicode2GBK(String unicodeStr) {
        int index = 0;
        StringBuilder buffer = new StringBuilder();
        int li_len = unicodeStr.length();
        while (index < li_len) {
            if (index >= li_len - 1 || !"\\u".equals(unicodeStr.substring(index, index + 2))) {
                buffer.append(unicodeStr.charAt(index));
                index++;
                continue;
            }

            String charStr;
            charStr = unicodeStr.substring(index + 2, index + 6);
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            index += 6;
        }
        return buffer.toString();
    }

    /**
     * 是否需要转换
     *
     * @param para 字符
     * @return 返回{@code true}则需要，否则不需要
     */
    private static boolean isNeedConvert(char para) {
        return ((para & (0x00FF)) != para);
    }

    /**
     * UTF8编码的字符串转为unicode编码的字符串
     *
     * @param utf8Str UTF8编码的字符串
     * @return unicode编码的字符串
     */
    public static String utf8ToUnicode(String utf8Str) {
        char[] myBuffer = utf8Str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < utf8Str.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
            if (ub == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * unicode编码的字符串转为UTF8编码的字符串
     *
     * @param theString unicode编码的字符串
     * @return UTF8编码的字符串
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuilder outBuffer = new StringBuilder(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
