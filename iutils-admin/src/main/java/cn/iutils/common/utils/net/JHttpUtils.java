package cn.iutils.common.utils.net;

import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网页处理工具
 *
 * @author cc
 */
public class JHttpUtils {

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 30 * 1000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();

    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, "UTF-8", Maps.newHashMap());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, String charset,
                               Map<Object, Object> params) {
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (Object key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        url += param;
        String result = null;
        HttpClient httpClient = HttpClients.createSystem();
        try {
            HttpGet httpGet = new HttpGet(url);

            httpGet.addHeader("Accept", "text/html");
            httpGet.addHeader("Accept-Charset", "utf-8");
            httpGet.addHeader("Accept-Encoding", "gzip");
            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, "UTF-8", Maps.newHashMap());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param url    API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String url, String charset,
                                Map<Object, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);

            httpPost.addHeader("Accept", "text/html");
            httpPost.addHeader("Accept-Charset", "utf-8");
            httpPost.addHeader("Accept-Encoding", "gzip");
            httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<Object, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey()
                        .toString(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset
                    .forName(charset)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param url
     * @param json json对象
     * @return
     */
    public static String doPost(String url, String charset, Object json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            httpPost.addHeader("Accept", "text/html");
            httpPost.addHeader("Accept-Charset", "utf-8");
            httpPost.addHeader("Accept-Encoding", "gzip");
            httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

            StringEntity stringEntity = new StringEntity(json.toString(),
                    charset);// 解决中文乱码问题
            stringEntity.setContentEncoding(charset);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }
}
