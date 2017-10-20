package cn.iutils.common.config;

import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import cn.iutils.common.utils.PropertiesUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;

import java.util.Properties;

/**
 * 微信全局配置
 * Created by Haden on 2017/7/31.
 */
public class WechatConfig {

    private static Properties properties = PropertiesUtil.loadProperties("classpath:wechat.properties");

    public static WxMaInMemoryConfig getConfig() {
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(properties.getProperty("appid"));
        config.setSecret(properties.getProperty("secret"));
        return config;
    }

    public static WxPayConfig config() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(properties.getProperty("appid"));
        payConfig.setMchId(properties.getProperty("mchId"));
        payConfig.setMchKey(properties.getProperty("mchKey"));
        payConfig.setNotifyUrl(properties.getProperty("notifyUrl"));
        return payConfig;
    }

}
