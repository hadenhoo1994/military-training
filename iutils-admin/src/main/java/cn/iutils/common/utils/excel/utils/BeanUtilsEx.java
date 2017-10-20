package cn.iutils.common.utils.excel.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wangzs
 * @Title 扩展BeanUtils.copyProperties支持data类型
 * @Description
 * @date 2017-1-5
 */
public class BeanUtilsEx extends BeanUtils {
    private static Logger logger = Logger.getLogger(BeanUtilsEx.class);

    static {
        ConvertUtils.register(new DateConvert(), java.util.Date.class);
        ConvertUtils.register(new DateConvert(), String.class);
    }

    public static String getProperty(final Object bean, final String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        return BeanUtilsBean.getInstance().getProperty(bean, name);

    }

    public static void copyProperties(Object target, Object source) {
        // 支持对日期copy
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("扩展BeanUtils.copyProperties支持data类型:" + e.getMessage());
            e.printStackTrace();
        }
    }
}