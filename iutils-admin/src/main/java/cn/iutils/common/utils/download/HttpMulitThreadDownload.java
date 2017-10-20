package cn.iutils.common.utils.download;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HTTP多线程下载
 *
 * @Author DuanCZ
 * @Date 2015年9月30日-上午9:28:28
 */

public class HttpMulitThreadDownload {

    // 下载文件路径
    private String downloadFilePath = null;
    // 保存文件路径
    private String saveFileDir = "Download";
    // 默认合理并发线程数
    private int threadCount = Runtime.getRuntime().availableProcessors() * 2;
    // 新文件名称
    private String newFileName = null;

    public HttpMulitThreadDownload(int threadCount, String downloadFilePath, String saveFileDir, String newFileName) {
        // 用户指定线程数如果小于默认线程数则使用用户指定线程数
        if (threadCount < this.threadCount) {
            this.threadCount = threadCount;
        }
        this.downloadFilePath = downloadFilePath;
        this.saveFileDir = saveFileDir;
        this.newFileName = newFileName;
    }

    public void MulitThreadDownload() {

        // 数据合法性验证
        if (null == downloadFilePath || downloadFilePath.isEmpty()) {
            throw new RuntimeException("请指定下载路径!");
        }
        if (null == saveFileDir || saveFileDir.isEmpty()) {
            throw new RuntimeException("请指定保存路径!");
        }

        // 创建保存文件路径如果不存在
        File saveFileDirTemp = new File(saveFileDir);
        if (!saveFileDirTemp.exists()) {
            saveFileDirTemp.mkdirs();
        }

        // 处理文件名称
        if (null == newFileName || newFileName.isEmpty()) {
            newFileName = downloadFilePath.substring(downloadFilePath.lastIndexOf(File.separator) + 1, downloadFilePath.length());
        }

        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        try {
            // 根据文件长度计算合理线程数开始
            URLConnection urlConnection = new URL(downloadFilePath).openConnection();
            // 获取文件长度
            int downloadFileLength = urlConnection.getContentLength();
            // 计算每个线程负责的文件字节长度
            int averageThreadLength = downloadFileLength / threadCount;
            int residueThreadLength = downloadFileLength % threadCount;
            // 让每一个线程开始工作
            int startIndex = 0;
            int endIndex = 0;
            for (int i = 0; i < threadCount; i++) {

                // 计算每一个线程开始和计数索引
                startIndex = i * averageThreadLength;
                // 如果是最后一个线程，则将剩余的全部下载
                if ((i + 1) == threadCount) {
                    endIndex = (i + 1) * averageThreadLength + residueThreadLength - 1;
                }
                endIndex = (i + 1) * averageThreadLength - 1;

                // 创建下载线程对象
                cn.iutils.common.download.DownloadHandlerThread downloadHandlerThread = new cn.iutils.common.download.DownloadHandlerThread();
                downloadHandlerThread.setDownloadFilePath(downloadFilePath);
                downloadHandlerThread.setSaveFilePath(saveFileDir + newFileName);
                downloadHandlerThread.setStartIndex(startIndex);
                downloadHandlerThread.setEndIndex(endIndex);
                threadPool.execute(downloadHandlerThread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}