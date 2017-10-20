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
package com.mzlion.core.json.gson;

import java.io.StringWriter;

/**
 * 将非ASCII码的字符转为Unicode码
 *
 * @author mzlion on 2016/7/6.
 */
class UnicodeStringWriter extends StringWriter {

    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    private static final int[] ESCAPE_CODES;

    static {
        int[] table = new int[128];
        // Control chars need generic escape sequence
        for (int i = 0; i < 32; ++i) {
            // 04-Mar-2011, tatu: Used to use "-(i + 1)", replaced with constants
            table[i] = -1;
        }
        /* Others (and some within that range too) have explicit shorter
         * sequences
         */
        //table['"'] = '"';
        //table['\\'] = '\\';
        // Escaping of slash is optional, so let's not add it
        //table[0x08] = 'b';
        //table[0x09] = 't';
        //table[0x0C] = 'f';
        //table[0x0A] = 'n';
        //table[0x0D] = 'r';
        table['/'] = '/';
        ESCAPE_CODES = table;
    }

    /**
     * Return the buffer's current value as a string.
     */
    @Override
    public String toString() {
        //https://github.com/google/gson/issues/388

        String src = super.toString();
        StringBuffer builder = new StringBuffer(src.length() * 4);
        for (char c : src.toCharArray()) {
            if (c >= 0x80) {
                // 为所有非ASCII字符生成转义的unicode字符
                writeUnicodeEscape(builder, c);
            } else {
                // 为ASCII字符中前128个字符使用转义的unicode字符
                int code = (c < ESCAPE_CODES.length ? ESCAPE_CODES[c] : 0);
                if (code == 0) {
                    // 此处不用转义
                    builder.append(c);
                } else if (code < 0) {
                    // 通用转义字符
                    writeUnicodeEscape(builder, (char) (-code - 1));
                } else {
                    // 短转义字符 (\n \t ...)
                    builder.append("\\").append((char) code);
                }
            }
        }
        return builder.toString();
    }

    private void writeUnicodeEscape(StringBuffer builder, char c) {
        builder.append("\\u");
        builder.append(HEX_CHARS[(c >> 12) & 0xF]);
        builder.append(HEX_CHARS[(c >> 8) & 0xF]);
        builder.append(HEX_CHARS[(c >> 4) & 0xF]);
        builder.append(HEX_CHARS[c & 0xF]);
    }
}
