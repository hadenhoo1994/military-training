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

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static com.mzlion.core.lang.StringUtils.isEmpty;

/**
 * 数值相关的工具类
 *
 * @author mzlion on 2016-06-20
 */
public class NumberUtils {

    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 将科学计数法转为字符串
     *
     * @param val 以科学计数法形式的数字
     * @return 字符串
     */
    public static String avoidScientificNotation(double val) {
        return avoidScientificNotation(String.valueOf(val));
    }

    /**
     * 将科学计数法转为字符串
     *
     * @param val 以科学计数法形式的数字
     * @return 字符串
     */
    public static String avoidScientificNotation(String val) {
        return isEmpty(val) ? StringUtils.EMPTY_STRING : (val.matches("^\\d(.\\d+)?[eE](\\d+)$") ? new BigDecimal(val).toPlainString() : val);
    }

    /**
     * 判断字符串是否为纯数字组成
     *
     * @param str 数值字符串
     * @return {@link Boolean}
     * @see #isNumeric(String) 带有小数点的判断请调用该方法
     */
    public static boolean isDigital(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是数值,如果是则返回true,否则返回false
     * <p>
     * 支持类似'1','-1','0.01','.01'等格式的数值字符串,但是不支持进制数字字符串
     * </p>
     *
     * @param str 数值字符串
     * @return {@linkplain Boolean}
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.hasText(str)) {
            String regex = "^[-+]?(\\d+)?(\\.\\d+)?$";
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(str).find();
        }
        return false;
    }

    /**
     * 人民币金额转为分
     *
     * @param yuan 金额,单位为元
     * @return 金额分
     */
    public static long yuan2Fen(long yuan) {
        return yuan * 100;
    }

    /**
     * 人民币金额转为分,默认保留2位小数,超过则四舍五入.
     *
     * @param yuan 金额,单位为元
     * @return 金额分
     */
    public static long yuan2Fen(double yuan) {
        return yuan2Fen(Double.toString(yuan));
    }

    /**
     * 人民币金额转为分,默认保留2位小数,超过则四舍五入.
     *
     * @param yuan 金额,单位为元
     * @return 金额分
     */
    public static long yuan2Fen(String yuan) {
        return yuan2Fen(yuan, true);
    }

    /**
     * 人民币金额转为分,保留小数位位数
     *
     * @param yuan  金额,单位为元
     * @param round 如果值{@code true}则超出部分四舍五入，否则直接忽略
     * @return 金额分
     */
    public static long yuan2Fen(double yuan, boolean round) {
        return yuan2Fen(Double.toString(yuan), round);
    }

    /**
     * 人民币金额转为分,保留小数位位数.
     *
     * @param yuan  金额,单位为元
     * @param round 如果值{@code true}则超出部分四舍五入，否则直接忽略
     * @return 金额分
     */
    public static long yuan2Fen(String yuan, boolean round) {
        BigDecimal multiplier = new BigDecimal(String.valueOf(yuan)), multiplicand = new BigDecimal(100);
        if (round) multiplier = multiplier.setScale(2, BigDecimal.ROUND_HALF_UP);
        else multiplier = multiplier.setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal result = multiplier.multiply(multiplicand);
        return result.longValue();
    }

    /**
     * 将人民币金额(字符串)转成分(结果为仍为字符串)
     *
     * @param yuan 金额，单位为元
     * @return 金额，单位为分
     */
    public static String yuan2FenString(String yuan) {
        return String.valueOf(yuan2Fen(yuan, true));
    }

    /**
     * 人民币金额分转元，保留2位小数
     *
     * @param fen 金额，单位分
     * @return 金额元
     */
    public static double fen2Yuan(long fen) {
        return fen2Yuan(Long.toString(fen));
    }

    /**
     * 人民币金额分转元，保留2位小数
     *
     * @param fen 金额，单位分
     * @return 金额元
     */
    public static double fen2Yuan(String fen) {
        BigDecimal dividend = new BigDecimal(fen), divisor = new BigDecimal(100);
        BigDecimal result = dividend.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
        return result.doubleValue();
    }

}
