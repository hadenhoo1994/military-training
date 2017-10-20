package cn.iutils.common.utils;

import com.google.common.collect.Maps;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 系统信息获取工具
 *
 * @author iutils.cn
 */
public class SystemUtils extends BaseUtils {

    /**
     * 系统基本信息
     */
    private static Properties props = System.getProperties();

    /**
     * 运行时参数
     */
    private static Runtime runtime = Runtime.getRuntime();

    /**
     * 获取系统基本信息
     *
     * @return
     */
    public static Map<String, Object> getSystemInfo() {
        Map<String, Object> map = Maps.newConcurrentMap();
        //版本
        map.put("javaVersion", props.getProperty("java.version"));
        //供应商
        map.put("javaVendor", props.getProperty("java.vendor"));
        //虚拟机名称
        map.put("javaVmName", props.getProperty("java.vm.name"));
        //系统名称
        map.put("osName", props.getProperty("os.name"));
        //系统架构
        map.put("osArch", props.getProperty("os.arch"));
        //服务器时间
        map.put("javaTime", DateUtils.formatDate(new Date(), "yyyy年MM月dd日 HH时ss分mm秒"));
        //物理内存使用率
        map.put("memery", getMemery());
        //JVM内存
        long freeMemoery = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemoery;
        long maxMemory = runtime.maxMemory();
        long useableMemory = maxMemory - totalMemory + freeMemoery;
        map.put("usedMemory", usedMemory / 1024 / 1024);
        map.put("maxMemory", maxMemory / 1024 / 1024);
        map.put("useableMemory", useableMemory / 1024 / 1024);
        return map;
    }

    /**
     * 获取物理内存使用率
     *
     * @return
     */
    private static int getMemery() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存+虚拟内存
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        Double compare = (Double) (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
        return Math.abs(compare.intValue());
    }
}
