package cn.iutils.common.utils;

import cn.iutils.common.config.JConfig;
import cn.iutils.common.spring.SpringUtils;
import org.apache.shiro.cache.CacheManager;

/**
 * Cache工具类
 *
 * @author iutils.cn
 */
public class CacheUtils {

    private static CacheManager cacheManager = SpringUtils.getBean("cacheManager");

    private static final String SYS_CACHE = JConfig.getConfig("iutils.default.cache");

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        return getCache(cacheName).get(key);
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得一个Cache
     *
     * @param cacheName
     * @return
     */
    private static org.apache.shiro.cache.Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

}
