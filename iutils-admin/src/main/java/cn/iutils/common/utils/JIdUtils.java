package cn.iutils.common.utils;

import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @author cc
 */
public class JIdUtils {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuidRandom() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return uuidRandom().replaceAll("-", "");
    }

    /**
     * 生成自定义位数的随机数
     */
    public static String random4custom(int carry){
        int max = (int) Math.pow(10,carry);
        int min = (int) Math.pow(10,carry-1);
        int num = getRandom(max,min);
        return num + "";
    }

    /**
     * 生成随机数
     * @param max
     * @param min
     * @return
     */
    private static int getRandom(int max, int min) {
        int num = (int) (min + Math.random()*(max-min));
        return num;
    }

    /**
     * 随机函数 生成6位短信验证码
     */
    public static String smsCode(){
       int i = getRandom(1000000,100000);
        return i+"";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            System.out.println(random4custom(6));
        }

    }

}
