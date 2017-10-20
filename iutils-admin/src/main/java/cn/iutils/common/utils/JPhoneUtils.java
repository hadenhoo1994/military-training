package cn.iutils.common.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号工具处理工具
 *
 * @author iutils.cn
 * @version 1.0
 */
public class JPhoneUtils {

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger(JPhoneUtils.class);

    private static String isChinaMobile = "^134[0-8]\\d{7}$|^(?:13[5-9]|147|15[0-27-9]|178|18[2-478])\\d{8}$";// 移动
    private static String isChinaUnion = "^(?:13[0-2]|145|15[56]|176|18[56])\\d{8}$";// 联通
    private static String isChinaTelcom = "^(?:133|153|177|18[019])\\d{8}$";// 电信
    private static String isOtherTelphone = "^170([059])\\d{7}$";// 其他运营商

    public static Map<String, String> cookieMap = new HashMap<String, String>(64);

    /**
     * 判断是否移动号码
     *
     * @param phone
     * @return
     */
    public static boolean isChinaMobile(String phone) {
        boolean flag = false;
        try {
            Pattern p = null;
            Matcher m = null;
            p = Pattern.compile(isChinaMobile);
            m = p.matcher(phone);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断是否联通号码
     *
     * @param phone
     * @return
     */
    public static boolean isChinaUnion(String phone) {
        boolean flag = false;
        try {
            Pattern p = null;
            Matcher m = null;
            p = Pattern.compile(isChinaUnion);
            m = p.matcher(phone);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断是否电信号码
     *
     * @param phone
     * @return
     */
    public static boolean isChinaTelcom(String phone) {
        boolean flag = false;
        try {
            Pattern p = null;
            Matcher m = null;
            p = Pattern.compile(isChinaTelcom);
            m = p.matcher(phone);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断是否其他号码
     *
     * @param phone
     * @return
     */
    public static boolean isOtherTelphone(String phone) {
        boolean flag = false;
        try {
            Pattern p = null;
            Matcher m = null;
            p = Pattern.compile(isOtherTelphone);
            m = p.matcher(phone);
            flag = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询通话详单
     *
     * @param phone 手机号
     * @param pwd   服务密码
     * @param code  验证码
     */
    public static void queryCallDetails(String phone, String pwd, String code) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        String loginUrl = "https://uac.10010.com/portal/Service/MallLogin?userName=" + phone + "&password=" + pwd + "&pwdType=01&productType=01&redirectType=01";
        HttpGet loginGet = new HttpGet(loginUrl);
        HttpResponse loginResponse = httpClient.execute(loginGet);
        System.out.print("result:" + EntityUtils.toString(loginResponse.getEntity()));
        //判断是否登录成功
        String cookie = setCookie(loginResponse);
        System.out.println(cookie);
        String dataUrl = "http://iservice.10010.com/e3/static/query/callDetail?_=1481782066817&accessURL=http://iservice.10010.com/e4/query/bill/call_dan-iframe.html?menuCode=000100030001&&menuId=000100030001&menuid=000100030001";
        HttpPost dataPost = new HttpPost(dataUrl);
        dataPost.addHeader(new BasicHeader("Cookie", cookie));
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("pageNo", "1"));
        nvps.add(new BasicNameValuePair("pageSize", "20"));
        nvps.add(new BasicNameValuePair("beginDate", "2016-11-01"));
        nvps.add(new BasicNameValuePair("endDate", "2016-11-30"));
        dataPost.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse dataResponse = httpClient.execute(dataPost);
        System.out.print("result:" + EntityUtils.toString(dataResponse.getEntity()));
    }

    /**
     * 从响应信息中获取cookie
     *
     * @param httpResponse
     * @return
     */
    public static String setCookie(HttpResponse httpResponse) {
        Header headers[] = httpResponse.getHeaders("Set-Cookie");
        if (headers == null || headers.length == 0) {
            return null;
        }
        String cookie = "";
        for (int i = 0; i < headers.length; i++) {
            cookie += headers[i].getValue();
            if (i != headers.length - 1) {
                cookie += ";";
            }
        }
        String cookies[] = cookie.split(";");
        for (String c : cookies) {
            c = c.trim();
            if (cookieMap.containsKey(c.split("=")[0])) {
                cookieMap.remove(c.split("=")[0]);
            }
            cookieMap.put(c.split("=")[0], c.split("=").length == 1 ? "" : (c.split("=").length == 2 ? c.split("=")[1] : c.split("=", 2)[1]));
        }
        String cookiesTmp = "";
        for (String key : cookieMap.keySet()) {
            cookiesTmp += key + "=" + cookieMap.get(key) + ";";
        }
        return cookiesTmp.substring(0, cookiesTmp.length() - 2);
    }

    public static void main(String[] args) {
        try {
            queryCallDetails("18673335930", "688261", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
