package cn.iutils.common.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 下载文件处理线程
 *
 * @Author DuanCZ
 * @Date 2015年9月30日-上午11:38:42
 */

public class DownloadHandlerThread implements Runnable {

    // 待下载的HTTP文件路径
    private String downloadFilePath = null;
    // 下载保存文件路径
    private String saveFilePath = null;
    // 文件随机写入开始索引
    private int startIndex = 0;
    // 文件随机写入结束索引
    private int endIndex = 0;

    @Override
    public void run() {

        System.out.println("线程名称[" + Thread.currentThread().getName() + "]于时间["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]开始下载");

        // 文件输入流对象
        InputStream fileInputStream = null;
        // 随机访问文件
        RandomAccessFile randomAccessFile = null;

        // ----------------------------------------------------------------------------------------------------
        // 获取随机文件流开始

        // 获取一个URL打开链接对象
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(downloadFilePath).openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 设置该链接允许和用户交互
        urlConnection.setAllowUserInteraction(true);
        // 设置请求属性字节范围
        urlConnection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex + "");
        try {
            // 获取指定的文件流
            fileInputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取随机文件流结束
        // ----------------------------------------------------------------------------------------------------

        // ----------------------------------------------------------------------------------------------------
        // 写文件流到指定的文件开始
        try {
            // 创建文件随机访问对象
            randomAccessFile = new RandomAccessFile(saveFilePath, "rw");
            // 将文件写入位置移动到其实点
            randomAccessFile.seek(startIndex);
            // 写入文件
            int bytes = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((bytes = fileInputStream.read(buffer, 0, buffer.length)) != -1) {
                randomAccessFile.write(buffer, 0, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != randomAccessFile) {
                    randomAccessFile.close();
                }
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写文件流到指定的文件结束
        // ----------------------------------------------------------------------------------------------------

        System.out.println("线程名称[" + Thread.currentThread().getName() + "]于时间["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]下载完成");
    }

    public String getDownloadFilePath() {
        return downloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

}