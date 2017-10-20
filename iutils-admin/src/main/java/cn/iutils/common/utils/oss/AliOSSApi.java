package cn.iutils.common.utils.oss;

import cn.iutils.common.utils.PropertiesUtil;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AliOSSApi {

    public static final String bucketCDN = "tj-cdn";
    private static Properties properties = PropertiesUtil.loadProperties("classpath:aliyun.properties");
    private static String endpoint = getConfig("oss.endpoint");
    private static String accessKeyId = getConfig("access.key.id");
    private static String accessKeySecret = getConfig("access.key.secret");
    private static String bucket = getConfig("oss.bucket");

    private static OSSClient client;
    private static ClientConfiguration conf;

    static {
        // 创建ClientConfiguration实例
        conf = new ClientConfiguration();
        // 设置OSSClient使用的最大连接数，默认1024
        conf.setMaxConnections(1024 * 1024);
        // 设置请求超时时间，默认50秒
        conf.setSocketTimeout(50 * 1000);
        // 设置失败请求重试次数，默认3次
        conf.setMaxErrorRetry(5);
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public static OSSClient getClient() {
        return client;
    }

    public static String getBucket() {
        return bucket;
    }

    private static String getConfig(String key) {
        return properties.getProperty(key);
    }

    /**
     * 删除图片
     *
     * @param imageName 图片名称，改图片名称包括文件夹名称和“/”
     */
    public static void delFile(String imageName) {
        delFile(bucket, imageName);
    }

    /**
     * 删除图片
     *
     * @param bucket
     * @param imageName
     */
    public static void delFile(String bucket, String imageName) {
        client.deleteObject(bucket, imageName);
    }

    /**
     * 上传文件
     *
     * @param fileName 文件名称，包括文件夹名称和“/”
     * @param filePath 本地文件路径
     * @throws FileNotFoundException
     */
    public static void uploadFile(String fileName, String filePath) throws FileNotFoundException {
        uploadFile(bucket, fileName, filePath);
    }

    /**
     * 上传文件
     *
     * @param bucket
     * @param fileName
     * @param filePath
     * @throws FileNotFoundException
     */
    public static void uploadFile(String bucket, String fileName, String filePath) throws FileNotFoundException {
        // 获取指定文件的输入流
        File file = new File(filePath);
        InputStream content = new FileInputStream(file);
        // 上传Object.
        client.putObject(bucket, fileName, content);
    }

    /**
     * 上传文件
     *
     * @param fileName   文件名称，包括文件夹名称和“/”
     * @param fileStream 本地流
     * @throws FileNotFoundException
     */
    public static void uploadFile(String fileName, InputStream fileStream) throws FileNotFoundException {
        // 上传Object.
        uploadFile(bucket, fileName, fileStream);
    }

    /**
     * 上传文件
     *
     * @param bucket
     * @param fileName
     * @param fileStream
     * @throws FileNotFoundException
     */
    public static void uploadFile(String bucket, String fileName, InputStream fileStream) throws FileNotFoundException {
        // 上传Object.
        client.putObject(bucket, fileName, fileStream);
    }

    /**
     * 获得url链接
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
//        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
//        // 生成URL
//        URL url = client.generatePresignedUrl(bucket, key, expiration);
//        if (url != null) {
//            return url.toString();
//        }
        return endpoint + "/" + url;
    }

    public static void main(String[] args) {
//        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 列举Object
        final String keyPrefix = "video/";
        final int maxKeys = 200;
        String nextMarker = null;
        ObjectListing objectListing = null;
        List<OSSObjectSummary> sumList = new ArrayList<>();
        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(bucket).withMarker(nextMarker).withMaxKeys(maxKeys).withPrefix(keyPrefix));
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                sumList.add(s);
                System.out.println("\t" + s.getKey());
            }
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        System.out.println(sumList.size());
        // 关闭client
        ossClient.shutdown();
    }

}
