package cn.iutils.common.utils;

import cn.iutils.common.exception.ApiException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient431Util {
    protected static final Logger log = Logger.getLogger(HttpClient431Util.class);

    private static final RequestConfig config;

    public static final String DEFAULT_SEND_CHARSET = "UTF-8";

    public static final String DEFAULT_RES_CHARSET = "UTF-8";

    static {
        config = RequestConfig.custom().setConnectTimeout(15000).setSocketTimeout(15000).build();
    }

    public static String doGet(Map<String, String> params, String url, String cookie) throws Exception {
        return doGet(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, cookie);
    }

    public static String doGet(Map<String, String> params, String url) throws Exception {
        return doGet(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, "");
    }

    public static String doPost(Map<String, String> params, String url) throws Exception {
        return doPost(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET);
    }

    public static String doDualSSLPost(Map<String, String> params, String url, String keyStorePath, String keyStorePass) throws Exception {
        return doDualSSLPost(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET, keyStorePath, keyStorePass);
    }

    public static String doPostContent(String dataContent, String contentType, String contentCharset, String resCharset, String url) throws ApiException {
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Connection", "close");
            HttpEntity reqentity = new StringEntity(dataContent, ContentType.create(contentType, contentCharset));
            httpPost.setEntity(reqentity);

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new ApiException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);

            return result;
        } catch (Exception e) {
            throw new ApiException(e);
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * HTTP Get 获取内容
     *
     * @param params     请求的参数
     * @param url        请求的url地址 ?之前的地址
     * @param reqCharset 编码格式
     * @param resCharset 编码格式
     * @return 页面内容
     */
    public static String doGet(Map<String, String> params, String url, String reqCharset, String resCharset, String cookie) throws ApiException {
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, reqCharset == null ? DEFAULT_SEND_CHARSET : reqCharset));
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpGet.addHeader("Connection", "close");
            if (!Strings.isNullOrEmpty(cookie)) {
                httpGet.setHeader("Cookie", cookie);
            }

            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new ApiException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            System.out.println(entity);
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new ApiException(e);
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * 微信多媒体下载audio-->.amr-->.mp3
     *
     * @param url
     * @param params
     * @return
     */
    public static URLConnection doGet(String url, Map<String, String> params) {
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
            }
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            /*Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            *//*for (String key : map.keySet()) {
                log.info(key + " : " + map.get(key));
            }*//*
            String filePath = "E:/download/aadd"+ ContentTypeUtil.getExtension(map.get("Content-Type").toString());
            File file = new File(filePath);
            // 定义 BufferedReader输入流来读取URL的响应
            bis = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();
            log.info("下载多媒体文件成功，filePath=" + filePath);*/
            return connection;
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP Post 获取内容
     *
     * @param params     请求的参数
     * @param url        请求的url地址 ?之前的地址
     * @param reqCharset 编码格式
     * @param resCharset 编码格式
     * @return 页面内容
     */
    public static String doPost(Map<String, String> params, String url, String reqCharset, String resCharset) throws Exception {
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection", "close");
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, reqCharset == null ? DEFAULT_SEND_CHARSET : reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new ApiException("", "HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * HTTP Post 获取内容
     *
     * @param params     请求的参数
     * @param url        请求的url地址 ?之前的地址
     * @param reqCharset 编码格式
     * @param resCharset 编码格式
     * @return 页面内容
     */
    public static String doPost(Map<String, String> params, String url, String reqCharset, String resCharset, String contentType) throws Exception {
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Connection", "close");
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, reqCharset == null ? DEFAULT_SEND_CHARSET : reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new ApiException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new ApiException(e);
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    public static String doPost(JSONObject jsonObject, String url, String resCharset) throws Exception {
        CloseableHttpClient httpClient = getSingleSSLConnection();
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type", "JSON");
            httpPost.addHeader("Connection", "close");
            StringEntity myEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
            httpPost.setEntity(myEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new ApiException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new ApiException(e);
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * HTTP Post 获取内容
     *
     * @param params     请求的参数
     * @param url        请求的url地址 ?之前的地址
     * @param reqCharset 编码格式
     * @param resCharset 编码格式
     * @return 页面内容
     */
    public static String doDualSSLPost(Map<String, String> params, String url, String reqCharset, String resCharset, String keyStorePath, String keyStorePass) throws Exception {
        CloseableHttpClient httpClient = getCloaseableHttpClient(keyStorePath, keyStorePass);
        CloseableHttpResponse response = null;
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection", "close");
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, reqCharset == null ? DEFAULT_SEND_CHARSET : reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new ApiException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw new ApiException(e);
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                }
        }
    }

    public static String sendXmlData(String url, Map<String, String> params) throws Exception {
        HttpClient client = HttpClients.createDefault();
//        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "text/xml;charset=GB2312");
        post.setEntity(new UrlEncodedFormEntity(pairs, "GB2312"));
        HttpResponse response = client.execute(post);
        Integer code = response.getStatusLine().getStatusCode();
        if (code == 200) {
            String result = EntityUtils.toString(response.getEntity(), "GB18030");
            EntityUtils.consume(response.getEntity());
            post.abort();
            return result;
        }
        return "";
    }

    /**
     * 创建双向ssl的连接
     *
     * @param keyStorePath
     * @param keyStorePass
     * @return
     * @throws ApiException
     */
    private static CloseableHttpClient getDualSSLConnection(String keyStorePath, String keyStorePass) throws ApiException {
        CloseableHttpClient httpClient = null;
        try {
            File file = new File(keyStorePath);
            URL sslJksUrl = file.toURI().toURL();
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            InputStream is = null;
            try {
                is = sslJksUrl.openStream();
                keyStore.load(is, keyStorePass != null ? keyStorePass.toCharArray() : null);
            } finally {
                if (is != null) is.close();
            }
            SSLContext sslContext = new SSLContextBuilder().loadKeyMaterial(keyStore, keyStorePass != null ? keyStorePass.toCharArray() : null)
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate,
                                                 String paramString) throws CertificateException {
                            return true;
                        }
                    })
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
            return httpClient;
        } catch (Exception e) {
            throw new ApiException(e);
        }

    }

    /**
     * 创建单向ssl的连接
     *
     * @return
     * @throws ApiException
     */
    private static CloseableHttpClient getSingleSSLConnection() throws ApiException {
        CloseableHttpClient httpClient = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate,
                                         String paramString) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
            return httpClient;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    private static CloseableHttpClient getCloaseableHttpClient(String cartPath, String mchId) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(cartPath));
        try {
            keyStore.load(instream, mchId.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,
                mchId.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    /**
     * 发送PKCS12证书请求（POST）
     */
    public static String doCartPost(String cartPath, String mchId, String requestXml) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = getCloaseableHttpClient(cartPath, mchId);
        try {

            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");// 退款接口

            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            System.out.println("executing request" + httpPost.getRequestLine());
            //请求的xml需转码为ISO8859-1编码，否则易出现签名错误或红包上的文字显示有误
            StringEntity reqEntity = new StringEntity(new String(requestXml.getBytes("UTF-8"), "ISO8859-1"));
            // 设置类型
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    httpPost.abort();
                    throw new ApiException("HttpClient,error status code :" + statusCode);
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "iso8859-1");
                }
                EntityUtils.consume(entity);
                response.close();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }

    public static void main(String[] args) {
        testMsg();
//        JSONObject jsonObject = new JSONObject();
//        Map<String, String> params = new HashMap<String,String>();
////        params.put("access_token","tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVohrpdTeMp8AgYVPcAHACZT");
//        jsonObject.put("type","news");
//        jsonObject.put("offset","0");
//        jsonObject.put("count","20");
//        try {
//            String result = HttpClient431Util.doPost(jsonObject,"https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+
//                    "tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVohrpdTeMp8AgYVPcAHACZT","UTF-8");
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void testMsg() {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> params = new HashMap<String, String>();
//        params.put("access_token","tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVohrpdTeMp8AgYVPcAHACZT");
        jsonObject.put("media_id", "OlgPH4Gv2U2fjFhsG70xXxP9-mwILh0HAAkzAWQrQiQ");
        try {
            String result = HttpClient431Util.doPost(jsonObject, "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" +
                    "tuaK9aczk27g5AEofuXljRPlUoCK0QJjnW9RltbMAdsGjDLbEJM2ugVDEFhLFsXBrApzsiXqTXtPmm0K8U0Ogm-JhUoGWhMgWENbsa5c-3cNGRMvvSEVohrpdTeMp8AgYVPcAHACZT", "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
