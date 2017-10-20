package cn.iutils.common.utils.excel.utils;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzs
 * @Title 扩展BeanUtils.copyProperties支持data类型
 * @Description
 * @date 2017-1-5
 */
public class DateConvert implements Converter {
    @Override
    public Object convert(Class class1, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            Integer integerValue = (Integer) value;
            return integerValue.toString();
        }
        if (value instanceof Long) {
            Long longValue = (Long) value;
            return longValue.toString();
        }
        if (value instanceof Double) {
            Double doubleValue = (Double) value;
            return doubleValue.toString();
        }
        if (value instanceof Date) {
            return org.apache.commons.lang3.time.DateFormatUtils.format((Date) value, DateUtils.DATE_FORMAT_DAY);
        }
        if (value instanceof String) {
            String dateStr = (String) value;
            Date endTime = null;
            try {
                String regexp1 = "([0-9]{4})-([0-1][0-9])-([0-3][0-9])T([0-2][0-9]):([0-6][0-9]):([0-6][0-9])";
                String regexp2 = "([0-9]{4})-([0-1][0-9])-([0-3][0-9])(/t)([0-2][0-9]):([0-6][0-9]):([0-6][0-9])";
                String regexp3 = "([0-9]{4})-([0-1][0-9])-([0-3][0-9])";
                if (dateStr.matches(regexp1)) {
                    dateStr = dateStr.split("T")[0] + " " + dateStr.split("T")[1];
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    endTime = sdf.parse(dateStr);
                    return endTime;
                } else if (dateStr.matches(regexp2)) {
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    endTime = sdf.parse(dateStr);
                    return endTime;
                } else if (dateStr.matches(regexp3)) {
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    endTime = sdf.parse(dateStr);
                    return endTime;
                } else {
                    return dateStr;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
