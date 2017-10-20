package cn.iutils.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
public class IPUtil {
    public static final String BAIDU_AK = "Z28De3sGcYeUMf4VSpIPZCnrX1ToAq3I";
    public static final String BAIDU_url = "http://api.map.baidu.com/location/ip";

    public static String findAddressByIp(String ip) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ak", BAIDU_AK);
        params.put("ip", ip);
        params.put("coor", "bd09ll");
        String json = null;
        try {
            json = HttpClient431Util.doPost(params, BAIDU_url);
        } catch (Exception e) {
            throw e;
        }
        return json;
    }

    /*public static void main(String[] args) {
        System.out.println(findAddressByIp("127.0.0.1"));
    }*/
}
