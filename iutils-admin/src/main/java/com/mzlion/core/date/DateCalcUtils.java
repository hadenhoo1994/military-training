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

import java.util.Calendar;
import java.util.Date;

/**
 * 日期计算工具类，该类主要值对{@code Date}增改查【添加、修改、获取年月日时分秒】。还提供了两个日期之间相差月、天数计算。
 * 如果需要使用日期格式化、解析请查看{@link DateUtils}.
 *
 * @author mzlion on 2016-04-16
 */
public class DateCalcUtils {

    private DateCalcUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 给指定时间加/减毫秒数
     *
     * @param date        日期
     * @param milliSecond 毫秒数
     * @return {@link Date}
     */
    public static Date addMilliSecond(Date date, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }

    /**
     * 给指定日期加（减）秒数
     * <pre>
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addSecond(date,2); 2014-05-12 12:00:02
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addSecond(date,-2); 2014-05-12 11:59:58
     * </pre>
     *
     * @param date    日期
     * @param seconds 秒，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addSecond(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * 给指定日期加（减）分钟数
     * <pre>
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addMinute(date,2); 2014-05-12 12:02:00
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addMinute(date,-2); 2014-05-12 11:58:00
     * </pre>
     *
     * @param date    日期
     * @param minutes 分钟，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addMinute(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 给指定日期加（减）小时数
     * <pre>
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addHour(date,1); 2014-05-12 13:00:00
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addHour(date,-1); 2014-05-12 11:00:00
     * </pre>
     *
     * @param date  日期
     * @param hours 小时，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addHour(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 给指定日期加（减）天数
     * <pre>
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addDate(date,1); 2014-05-11 12:00:00
     *     比如时间2014-05-12 12:00:00  DateCalcUtils.addDate(date,-1); 2014-05-13 12:00:00
     * </pre>
     *
     * @param date 日期
     * @param day  天，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 给指定日期加（减）月数
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addMonth(date,1); 2014-06-12 12:10:00
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addMonth(date,-1); 2014-04-12 12:10:00
     * </pre>
     *
     * @param date   日期
     * @param months 月，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addMonth(Date date, int months) {
        return addMonth(date, months, false);
    }

    /**
     * 给指定日期加（减）月数
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addMonth(date,1,false); 2014-06-12 12:10:00
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addMonth(date,1,true);  2014-06-01 00:00:00
     * </pre>
     *
     * @param date       日期
     * @param months     月，如果为正整数则添加，否则相减
     * @param escapeDays 如果值为{@code true}则表示会清空时分秒毫秒，并且日期跳到当月的第一天；如果是{@code false}则不会
     * @return {@link Date}
     */
    public static Date addMonth(Date date, int months, boolean escapeDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (escapeDays) {
            cal.set(Calendar.DATE, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 给指定日期加（减）年份数
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addYear(date,1); 2015-06-12 12:10:00
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.addYear(date,-1); 2013-04-12 12:10:00
     * </pre>
     *
     * @param date  日期
     * @param years 年，如果为正整数则添加，否则相减
     * @return {@link Date}
     */
    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 从日期中获取年份
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.getYear(date); 2014
     * </pre>
     *
     * @param date 日期对象
     * @return 年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    /**
     * 从日期中获取月份
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.getMonth(date); 5
     * </pre>
     *
     * @param date 日期对象
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 从日期中获取天
     * <pre>
     *     比如时间2014-05-12 12:10:00  DateCalcUtils.getDay(date); 12
     * </pre>
     *
     * @param date 日期对象
     * @return 天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 从日期中获取小时（24制）
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.get24Hour(date); 22
     * </pre>
     *
     * @param date 日期对象
     * @return 小时（24制）
     */
    public static int get24Hour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 从日期中获取小时（12制）
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.get12Hour(date); 10
     * </pre>
     *
     * @param date 日期对象
     * @return 小时（12制）
     */
    public static int get12Hour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 从日期中获取分钟
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.getMinute(date); 10
     * </pre>
     *
     * @param date 日期对象
     * @return 分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 从日期中获取秒数
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.getSecond(date); 0
     * </pre>
     *
     * @param date 日期对象
     * @return 秒数
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 从日期中获取毫秒
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.getMillisecond(date); 151
     * </pre>
     *
     * @param date 日期对象
     * @return 毫秒
     */
    public static int getMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.currentTimeMillis();
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获取时间的毫秒数，该毫秒是时间转换为毫秒，而不是日期时间中的转动的毫秒。
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.getTimeMillis(date); 1402582200732L
     * </pre>
     *
     * @param date 日期对象
     * @return 毫秒数
     */
    public static long getTimeMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 判断是否是闰年，闰年规则：<a href="http://zh.wikipedia.org/wiki/%E9%97%B0%E5%B9%B4">闰年查看</a>
     * <pre>
     *     比如时间2014-05-12 22:10:00  DateCalcUtils.isLeapYear(date); false
     * </pre>
     *
     * @param date 日期对象
     * @return 是否为闰年
     */
    public static boolean isLeapYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        boolean leap = false;

        if (year % 400 == 0) {
            leap = true;
        } else if (year % 100 == 0) {
            leap = false;
        } else if ((year % 4 == 0)) {
            leap = true;
        }
        return leap;
    }

    /**
     * 从日期中获取月份第一天
     * <pre>
     *     比如时间2014-05-12  DateCalcUtils.getBeginDayInMonth("2014-05-12","yyyy-MM-dd"); 204-05-01
     * </pre>
     *
     * @param date 日期
     * @return 每月第一天
     */
    public static Date getBeginDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 从日期中获取月份最后一天
     * <pre>
     *     比如时间2014-05-12  DateCalcUtils.getEndDayInMonth(date); 204-05-31
     * </pre>
     *
     * @param date 日期对象
     * @return 每月最后一天
     */
    public static Date getEndDayInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 计算两个日期之间相差的天数,测试发现还是有问题(没问题)
     * <pre>
     *     比如Date start = 2012-2-12
     *     Date end = 2012-3-1
     *     DateCalcUtils.getDaysBetween(start,end)  18
     * </pre>
     *
     * @param start 日期对象
     * @param end   日期对象
     * @return 天数
     */
    public static int getDaysBetween(Date start, Date end) {
        Calendar srcCalendar = Calendar.getInstance();
        srcCalendar.setTime(start);
        srcCalendar.set(Calendar.HOUR_OF_DAY, 0);
        srcCalendar.set(Calendar.MINUTE, 0);
        srcCalendar.set(Calendar.SECOND, 0);

        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(end);
        destCalendar.set(Calendar.HOUR_OF_DAY, 0);
        destCalendar.set(Calendar.MINUTE, 0);
        destCalendar.set(Calendar.SECOND, 0);

        if (srcCalendar.after(destCalendar)) {
            Calendar tempCalendar = srcCalendar;
            srcCalendar = destCalendar;
            destCalendar = tempCalendar;
        }

//        return (int) TimeUnit.DAYS.convert(destCalendar.getTimeInMillis() - srcCalendar.getTimeInMillis(), TimeUnit.MILLISECONDS);
        return (int) ((destCalendar.getTimeInMillis() - srcCalendar.getTimeInMillis()) / 86400000L);
    }

    /**
     * 计算两个日期之间相差的月份
     * <pre>
     *     比如Date start = 2012-2-12
     *     Date end = 2012-3-1
     *     DateCalcUtils.getMonthsBetween(start,end)  1
     * </pre>
     *
     * @param start 日期对象
     * @param end   日期对象
     * @return 月份
     */
    public static int getMonthsBetween(Date start, Date end) {
        Calendar srcCalendar = Calendar.getInstance();
        srcCalendar.setTime(start);

        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(end);

        if (srcCalendar.after(destCalendar)) {
            Calendar tempCalendar = srcCalendar;
            srcCalendar = destCalendar;
            destCalendar = tempCalendar;
        }

        //获取月份之差
        int months = destCalendar.get(Calendar.MONTH) - srcCalendar.get(Calendar.MONTH);

        int destYear = destCalendar.get(Calendar.YEAR);
        if (srcCalendar.get(Calendar.YEAR) != destYear) {
            srcCalendar = (Calendar) srcCalendar.clone();
            do {
                months += srcCalendar.getActualMaximum(Calendar.MONTH) + 1;//getActualMaximum最大值11
                srcCalendar.add(Calendar.YEAR, 1);
            } while (srcCalendar.get(Calendar.YEAR) != destYear);
            return months;
        }
        return months;
    }

    public static int dateDiff(int datePart, Date startDate, Date endDate) {
        //http://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
        return -1;
    }
}
