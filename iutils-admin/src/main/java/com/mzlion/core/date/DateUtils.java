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
package com.mzlion.core.date;

import com.mzlion.core.lang.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期工具类，提供了日期格式化和日期解析功能。
 *
 * @author mzlion on 2016-04-16
 */
public class DateUtils {

    /**
     * 日期格式化规则：yyyyMMddHHmmss
     */
    public static final String PATTERN_FULL = "yyyyMMddHHmmss";

    /**
     * 日期格式化规则：yyyyMMdd
     */
    public static final String PATTERN = "yyyyMMdd";

    /**
     * 格式化当前时间，格式化规则为{@link #PATTERN_FULL}
     *
     * @return 日期字符串
     */
    public static String formatDate() {
        return formatDate(PATTERN_FULL);
    }

    /**
     * 按照格式化规则对当前日期进行格式化返回
     *
     * @param pattern 格式化规则
     * @return 日期字符串
     */
    public static String formatDate(String pattern) {
        return formatDate(System.currentTimeMillis(), pattern);
    }

    /**
     * 将日期转为字符串，格式化规则yyyyMMddHHmmss
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date, PATTERN_FULL);
    }

    /**
     * 将日期转为字符串
     *
     * @param date    日期对象
     * @param pattern 格式化规则
     * @return 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if ((date == null) || !StringUtils.hasLength(pattern)) {
            return null;
        }
        return ThreadSafeDateParse.format(date, pattern);
    }

    /**
     * 将日期转为字符串
     *
     * @param date    日期
     * @param pattern 格式化规则
     * @return 日期字符串
     */
    public static String formatDate(long date, String pattern) {
        if ((date <= 0L) || !StringUtils.hasLength(pattern)) {
            return null;
        }
        return ThreadSafeDateParse.format(date, pattern);
    }

    /**
     * 将日期转为字符串，格式化规则yyyyMMddHHmmss
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String formatDate(long date) {
        return formatDate(date, PATTERN_FULL);
    }

    /**
     * 解析日期字符串
     *
     * @param strDate 日期字符串
     * @param pattern 解析规则
     * @return 日期对象
     */
    public static Date parseDate(String strDate, String pattern) {
        if (!StringUtils.hasText(strDate) || !StringUtils.hasText(pattern)) {
            return null;
        }
        try {
            return ThreadSafeDateParse.parse(strDate, pattern);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 解析日期字符串,默认解析规则yyyyMMddHHmmss
     *
     * @param strDate 日期字符串
     * @return 解析后的日期
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, PATTERN_FULL);
    }

    /**
     * 解析日期字符串，日期格式应该是长整数的字符串表现形式。
     *
     * @param longStrDate 带解析的日期字符串
     * @return 返回日期
     */
    public static Date parseDateByLongStr(String longStrDate) {
        if (StringUtils.hasText(longStrDate)) {
            return new Date(Long.parseLong(longStrDate));
        }
        return null;
    }

    /**
     * 转换日期格式，比如将2013/01/01转为2013-01-01
     *
     * @param srcDate      日期字符串
     * @param sourceFormat 原始的格式化规则
     * @param destFotrmat  转换的日期格式化规则
     * @return 转换后的日期字符串
     */
    public static String swapDateStr(String srcDate, String sourceFormat, String destFotrmat) {
        Date date = parseDate(srcDate, sourceFormat);
        return formatDate(date, destFotrmat);
    }

    /**
     * 获取当前日期后N天（可为负值，则表示前N天）日期，并且格式化为字符串
     *
     * @param days    天，如果为负值则表示以前
     * @param pattern 日期字符串样式
     * @return 日期字符串
     * @see DateCalcUtils#addDate(Date, int)
     */
    public static String formatNextDate(int days, String pattern) {
        return formatNextDate(new Date(), days, pattern);
    }

    /**
     * 获取指定日期后N天（可为负值，则表示前N天）日期，并且格式化为字符串
     *
     * @param date    参照日期
     * @param days    正数当前日期后N月 负数当前日期前N月
     * @param pattern 月，如果为负值则表示以前
     * @return 日期字符串
     */
    public static String formatNextDate(Date date, int days, String pattern) {
        Date nextDate = DateCalcUtils.addDate(date, days);
        return formatDate(nextDate, pattern);
    }

    /**
     * 获取距离当前时间相隔{@code months}月份(前几个月或后几个月)的时间，并且格式化为字符串
     *
     * @param months  相隔的月份
     * @param pattern 日期格式化
     * @return 格式化的日期字符串
     */
    public static String formatNextMonths(int months, String pattern) {
        return formatNextMonths(new Date(), months, pattern);
    }

    /**
     * 获取距离指定时间{@code specialTime}相隔{@code months}月份(前几个月或后几个月)的时间，并且格式化为字符串
     *
     * @param specialTime 指定的时间
     * @param months      相隔的月份
     * @param pattern     日期格式化
     * @return 格式化的日期字符串
     */
    public static String formatNextMonths(long specialTime, int months, String pattern) {
        return formatNextMonths(new Date(specialTime), months, pattern);
    }

    /**
     * 获取距离指定时间{@code specialTime}相隔{@code months}月份(前几个月或后几个月)的时间
     *
     * @param specialDate 指定的时间
     * @param months      相隔的月份
     * @param pattern     日期格式化
     * @return 格式化的日期字符串
     */
    public static String formatNextMonths(Date specialDate, int months, String pattern) {
        Date targetDate = DateCalcUtils.addMonth(specialDate, months);
        return formatDate(targetDate, pattern);
    }

    /**
     * 获取当前时间的月份第一天，并且格式化为字符串
     *
     * @param pattern 日期格式化风格
     * @return 格式化后的日期字符串
     */
    public static String formatBeginDayInMonth(String pattern) {
        return formatBeginDayInMonth(new Date(), pattern);
    }

    /**
     * 获取指定时间的月份第一天，并且格式化为字符串
     *
     * @param specialDate 指定的时间
     * @param pattern     日期格式化风格
     * @return 格式化后的日期字符串
     */
    public static String formatBeginDayInMonth(Date specialDate, String pattern) {
        if (specialDate == null) {
            return null;
        }
        specialDate = DateCalcUtils.getBeginDayInMonth(specialDate);
        return formatDate(specialDate, pattern);
    }
}
