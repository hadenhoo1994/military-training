package cn.iutils.common.utils.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;

/**
 * FastDFS功能调用类
 */
public class FastDFS {

    private static Logger log = Logger.getLogger(FastDFS.class);

    private static final String FAST_DFS_CONF_FILE = FastDFS.class.getResource("/").getPath() + File.separator + "fastdfs-client.properties";

    static {
        try {
            // 初始化配置文件
            ClientGlobal.initByProperties(FAST_DFS_CONF_FILE);
            System.out.println(FAST_DFS_CONF_FILE);
            System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取存储服务器连接
     *
     * @return
     * @throws IOException
     */
    private static StorageClient getStorageClient() throws IOException {

        // 建立tracker server 的连接
        /*
         * TrackerGroup tg = new TrackerGroup( new InetSocketAddress[] { new
         * InetSocketAddress( TRACKER_SERVER_IP, TRACKER_SERVER_PORT) });
         */

        // TrackerClient tc = new TrackerClient();
        TrackerServer ts = ClientGlobal.getG_tracker_group().getConnection();
        if (ts == null) {
            System.out.println("getConnection return null");
            return null;
        }

        // 建立存储服务器的连接
        StorageServer ss = null;// tc.getStoreStorage(ts);
        /*
         * if (ss == null) { System.out.println("getStoreStorage return null");
         * return null; }
         */

        // 建立存储客户端
        StorageClient sc = new StorageClient(ts, ss);

        /* for test only */
        // System.out.println("active test to storage server: " +
        // ProtoCommon.activeTest(ss.getSocket()));
        // ss.close();
        /* for test only */
        // System.out.println("active test to tracker server: " +
        // ProtoCommon.activeTest(ts.getSocket()));
        // ts.close();

        /* for test only */
        System.out.println("active test to storage server: " + ProtoCommon.activeTest(ss.getSocket()));
        ss.close();
        /* for test only */
        System.out.println("active test to tracker server: " + ProtoCommon.activeTest(ts.getSocket()));
        ts.close();

        return sc;
    }

    /**
     * 上传文件
     *
     * @return
     */
    public static String[] uploadFile(String fileName, String fileExt, byte[] contents) {
        try {
            if (fileName == null || contents == null) {
                System.out.println("Upload file[" + fileName + "] is null");
                return null;
            }

            long startTime = System.currentTimeMillis();
            System.out.println("Upload file[" + fileName + "] startTime:" + startTime);

            // TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = ClientGlobal.getG_tracker_group().getConnection();
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer, storageServer);

            NameValuePair[] meta_list = new NameValuePair[1];
            meta_list[0] = new NameValuePair("filename", fileName);
            String[] fileInfo = client.upload_file(contents, fileExt, meta_list);

            System.out.println("Upload file[" + fileName + "] ok");
            System.out.println(fileInfo[0]);// 文件存储所在的组 如：group1 group2等
            System.out.println(fileInfo[1]);// 文件在服务器上的路径及文件名
            // 如：M00/00/00/eWXds1DJpzCASX0oAAAAA4i3nNI382.txt

            long endTime = System.currentTimeMillis();
            System.out.println("Upload file[" + fileName + "] endTime:" + endTime);

            /* for test only */
            // System.out.println("active test to storage server: " +
            // ProtoCommon.activeTest(storageServer.getSocket()));
            // storageServer.close();
            /* for test only */
            // System.out.println("active test to tracker server: " +
            // ProtoCommon.activeTest(trackerServer.getSocket()));
            // trackerServer.close();

            return fileInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName
     * @param fileName
     * @return
     */
    public static Integer deleteFile(String groupName, String fileName) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("Delete file[" + fileName + "] startTime:" + startTime);

            // 建立存储客户端
            // StorageClient sc = getStorageClient();

            // 建立tracker server 的连接
            // TrackerClient tc = new TrackerClient();
            TrackerServer ts = ClientGlobal.getG_tracker_group().getConnection();
            if (ts == null) {
                System.out.println("getConnection return null");
                return null;
            }

            // 建立存储服务器的连接
            StorageServer ss = null;// tc.getStoreStorage(ts);

            // 建立存储客户端
            StorageClient sc = new StorageClient(ts, ss);

            Integer x = sc.delete_file(groupName, fileName);

            System.out.println("Delete file[" + fileName + "] ok");

            long endTime = System.currentTimeMillis();
            System.out.println("Delete file[" + fileName + "] endTime:" + endTime);

            /* for test only */
            System.out.println("active test to storage server: " + ProtoCommon.activeTest(ss.getSocket()));
            ss.close();
            /* for test only */
            System.out.println("active test to tracker server: " + ProtoCommon.activeTest(ts.getSocket()));
            ts.close();

            return x;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param fileName
     * @return
     */
    public static byte[] downloadFile(String groupName, String fileName) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("Download file[" + fileName + "] startTime:" + startTime);

            // 建立存储客户端
            // StorageClient sc = getStorageClient();

            // 建立tracker server 的连接
            // TrackerClient tc = new TrackerClient();
            TrackerServer ts = ClientGlobal.getG_tracker_group().getConnection();
            if (ts == null) {
                System.out.println("getConnection return null");
                return null;
            }

            // 建立存储服务器的连接
            StorageServer ss = null;// tc.getStoreStorage(ts);

            // 建立存储客户端
            StorageClient sc = new StorageClient(ts, ss);

            byte[] localfileByteArr = sc.download_file(groupName, fileName);

            System.out.println("Download file[" + fileName + "] ok");

            long endTime = System.currentTimeMillis();
            System.out.println("Download file[" + fileName + "] endTime:" + endTime);

            /* for test only */
            System.out.println("active test to storage server: " + ProtoCommon.activeTest(ss.getSocket()));
            ss.close();
            /* for test only */
            System.out.println("active test to tracker server: " + ProtoCommon.activeTest(ts.getSocket()));
            ts.close();

            return localfileByteArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {

            String fileName = "timg";
            String fileExt = "jpg";
            String classPath = new File(FastDFS.class.getResource("/").getFile()).getCanonicalPath();
            File file = new File(classPath + File.separator + "timg.jpg");
            uploadFile(fileName, fileExt, FileUtils.readFileToByteArray(file));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}