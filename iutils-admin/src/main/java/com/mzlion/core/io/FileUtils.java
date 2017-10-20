/*
 * Copyright (C) 2016-2017 mzlion(mzllon@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mzlion.core.io;

import com.mzlion.core.exceptions.FatalFileException;
import com.mzlion.core.lang.Assert;
import com.mzlion.core.lang.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 文件工具类,该工具类的部分实现参照了<code>commons-io</code>框架提供的方法。
 *
 * @author mzlion on 2016-06-05 09:30
 */
public class FileUtils {

    /**
     * 1KB
     */
    public static final long ONE_KB = 1024;

    /**
     * 1MB
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The file copy buffer size (30 MB)
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    /**
     * 平台临时目录
     *
     * @return 目录文件对象
     */
    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    /**
     * 平台的临时目录
     *
     * @return 目录路径字符串
     */
    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 用户主目录
     *
     * @return 用户主目录{@code File}
     */
    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    /**
     * 返回用户的主目录
     *
     * @return 用户主目录路径
     */
    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    //region======================== readLines ========================

    /**
     * 将文件的内容全部读取,采用系统默认编码
     *
     * @param file 待读的文件
     * @return 文件内容
     */
    public static List<String> readLines(File file) {
        return readLines(file, Charset.defaultCharset());
    }

    /**
     * 将文件的内容全部读取
     *
     * @param file    待读的文件
     * @param charset 字符编码
     * @return 文件内容
     */
    public static List<String> readLines(File file, final Charset charset) {
        Assert.notNull(file, "The parameter[destFile] is null.");
        Assert.notNull(charset, "The parameter[charset] is null.");
        FileInputStream in = null;
        try {
            in = openFileInputStream(file);
            return IOUtils.readLines(in, charset);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    //endregion===================== readLines ========================

    //region======================== FileCopy =========================

    /**
     * 文件内容拷贝指定的输出流中
     *
     * @param srcFile 原文件
     * @param output  输出流
     */
    public static void copyFile(File srcFile, OutputStream output) {
        FileInputStream in = openFileInputStream(srcFile);
        try {
            IOUtils.copyLarge(new BufferedInputStream(in), output);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 文件拷贝
     *
     * @param srcFile  原文件
     * @param destFile 目标文件
     */
    public static void copyFile(File srcFile, File destFile) {
        copyFile(srcFile, destFile, true);
    }

    /**
     * 文件拷贝
     *
     * @param srcFile      原文件
     * @param destFile     目标文件
     * @param holdFileDate 保持最后修改日期不变
     */
    public static void copyFile(File srcFile, File destFile, boolean holdFileDate) {
        Assert.notNull(srcFile, "Source file must not be null.");
        Assert.notNull(destFile, "Destination file must not be null.");
        if (!srcFile.exists()) throw new FatalFileException("Source [" + srcFile + "] does not exist.");
        if (srcFile.isDirectory())
            throw new FatalFileException("Source [" + srcFile + "] exists but it is a directory.");

        try {
            if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath()))
                throw new FatalFileException(String.format("Source [%s] and destination [%s] are the same.", srcFile, destFile));
            File parentFile = destFile.getParentFile();
            if (parentFile != null) {
                if (!parentFile.mkdirs() && !parentFile.isDirectory())
                    throw new FatalFileException("Destination [" + parentFile + "] directory cannot be created.");
            }
            if (destFile.exists() && !destFile.canWrite())
                throw new FatalFileException(" ===> Destination [" + parentFile + "] directory cannot be written.");
            doCopyFile(srcFile, destFile, holdFileDate);
        } catch (IOException e) {
            throw new FatalFileException(e);
        }
    }

    /**
     * 文件复制内部方法
     *
     * @param srcFile      原文件
     * @param destFile     目标文件
     * @param holdFileDate 保持最后修改日期不变
     * @throws IOException I/O异常
     */
    private static void doCopyFile(File srcFile, File destFile, boolean holdFileDate) throws IOException {
        if (destFile.exists() && destFile.isDirectory())
            throw new FatalFileException("Destination [" + destFile + "] exists but it is a directory.");

        try (FileInputStream in = new FileInputStream(srcFile);
             FileOutputStream out = new FileOutputStream(destFile);
             FileChannel inChannel = in.getChannel();
             FileChannel outChannel = out.getChannel()) {
            long size = inChannel.size(), pos = 0, count;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += outChannel.transferFrom(inChannel, pos, count);
            }
        }
        //必须放在try(){}之外，否则该修改无效
        if (srcFile.length() != destFile.length()) {
            throw new IOException(String.format("Failed to copy full contents from [%s] to [%s]", srcFile.getPath(), destFile.getPath()));
        }
        if (holdFileDate) destFile.setLastModified(srcFile.lastModified());
    }

    /**
     * 将文件拷贝到目录
     *
     * @param srcFile 原文件
     * @param destDir 目标目录
     */
    public static void copyFileToDirectory(File srcFile, File destDir) {
        copyFileToDirectory(srcFile, destDir, true);
    }

    /**
     * 将文件拷贝到目录
     *
     * @param srcFile      原文件
     * @param destDir      目标目录
     * @param holdFileDate 保持最后修改日期不变
     */
    private static void copyFileToDirectory(File srcFile, File destDir, boolean holdFileDate) {
        Assert.notNull(srcFile, "Source file must not be null.");
        Assert.notNull(destDir, "Destination Directory must not be null.");
        if (destDir.exists() && !destDir.isDirectory())
            throw new FatalFileException("Destination [" + destDir + "] is not a directory.");

        File destFile = new File(destDir, srcFile.getName());
        copyFile(srcFile, destFile, holdFileDate);
    }

    /**
     * 将输入流的数据输出到文件中
     *
     * @param in       输入流,非空
     * @param destFile 目标文件,非空
     */
    public static void copyStream(InputStream in, File destFile) {
        Assert.notNull(in, "The parameter[in] is null.");
        Assert.notNull(destFile, "The parameter[destFile] is null.");
        FileOutputStream fos = null;
        try {
            fos = openFileOutputStream(destFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            IOUtils.copy(in, bos);
            IOUtils.closeQuietly(bos);
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(in);
        }

    }
    //endregion==========================================================

    //--------------------------------Directory copy--------------------------------

    /**
     * 目录复制
     *
     * @param srcDir  原目录
     * @param destDir 目标目录
     */
    public static void copyDirectory(File srcDir, File destDir) {
        copyDirectory(srcDir, destDir, true);
    }

    /**
     * 目录复制
     *
     * @param srcDir       原目录
     * @param destDir      目标目录
     * @param holdFileDate 保持最后修改日期不变
     */
    public static void copyDirectory(File srcDir, File destDir, boolean holdFileDate) {
        copyDirectory(srcDir, destDir, holdFileDate, null);
    }

    /**
     * 目录复制
     *
     * @param srcDir       原目录
     * @param destDir      目标目录
     * @param holdFileDate 保持最后修改日期不变
     * @param filter       文件过滤器
     */
    public static void copyDirectory(File srcDir, File destDir, boolean holdFileDate, FileFilter filter) {
        Assert.notNull(srcDir, "Source Directory must not be null.");
        Assert.notNull(destDir, "Destination Directory must not be null.");
        if (!srcDir.exists()) throw new FatalFileException("Source [" + srcDir + "] does not exist.");
        if (destDir.isFile())
            throw new FatalFileException("Destination [" + destDir + "] exists but is not a directory.");

        try {
            if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath()))
                throw new FatalFileException(String.format("Source [%s] and destination [%s] are the same.", srcDir, destDir));
            //当目标目录是原目录的子目录时,不支持复制.
            if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath() + File.separator))
                throw new FatalFileException(String.format("Destination [%s] is child directory of source [%s].", destDir, srcDir));
            doCopyDirectory(srcDir, destDir, holdFileDate, filter);
        } catch (IOException e) {
            throw new FatalFileException(e);
        }
    }

    /**
     * 目录复制
     *
     * @param srcDir       原目录
     * @param destDir      目标目录
     * @param holdFileDate 保持最后修改日期不变
     * @param filter       文件过滤器
     * @throws FatalFileException 拷贝异常
     */
    private static void doCopyDirectory(File srcDir, File destDir, boolean holdFileDate, FileFilter filter) throws IOException {
        File[] srcFiles = filter == null ? srcDir.listFiles() : srcDir.listFiles(filter);
        if (srcFiles == null) {
            throw new IOException("Failed to list contents of [" + srcDir + "]");
        }
        if (destDir.exists() && !destDir.isDirectory())
            throw new IOException("Destination [" + destDir + "] exists but is not a directory.");
        if (!destDir.mkdirs() && !destDir.isDirectory())
            throw new IOException("Destination [" + destDir + "] directory cannot be created.");
        if (!destDir.canWrite()) throw new IOException("Destination [" + destDir + "] cannot be written to.");

        for (File srcFile : srcFiles) {
            File destFile = new File(destDir, srcFile.getName());
            if (srcFile.isDirectory()) {
                doCopyDirectory(srcFile, destFile, holdFileDate, filter);
            } else {
                doCopyFile(srcFile, destFile, holdFileDate);
            }
        }

        if (holdFileDate) {
            destDir.setLastModified(srcDir.lastModified());
        }
    }

    //--------------------------------move--------------------------------

    /**
     * 移动文件
     *
     * @param srcFile  原文件
     * @param destFile 目标文件
     * @throws FatalFileException 文件处理异常
     */
    public static void moveFile(File srcFile, File destFile) {
        Assert.notNull(srcFile, "Source must not be null.");
        Assert.notNull(destFile, "Destination must not be null.");
        if (!srcFile.exists()) throw new FatalFileException("Source [" + srcFile + "] does not exist.");
        if (srcFile.isDirectory()) throw new FatalFileException("Source [" + srcFile + "] is a directory.");
//        if (!destFile.exists()) throw new FatalFileException("Destination [" + destFile + "] does not exist.");
        if (destFile.isFile() && destFile.exists())
            throw new FatalFileException("Destination [" + destFile + "] already exists.");
        if (destFile.isDirectory() && !destFile.canWrite())
            throw new FatalFileException("Destination [" + destFile + "] cannot be written to.");

        File targetFile;
        if (destFile.isDirectory()) {
            targetFile = new File(destFile, srcFile.getName());
        } else {
            targetFile = destFile;
        }
        boolean renameTo = srcFile.renameTo(targetFile);
        if (!renameTo) {
            //调用系统的重命名失败(移动),可能属于不同FS文件系统
            copyFile(srcFile, targetFile);
            if (!srcFile.delete()) {
                targetFile.delete();
                throw new FatalFileException(String.format("Failed to delete original file [%s], after copy to [%s]", srcFile, destFile));
            }
        }
    }

    /**
     * 移动目录
     *
     * @param source  原文件或目录
     * @param destDir 目标目录
     * @throws FatalFileException 文件处理异常
     */
    public static void moveDirectory(File source, File destDir) {
        moveDirectory(source, destDir, false);
    }

    /**
     * 移动目录
     *
     * @param srcDir  原文件或目录
     * @param destDir 目标目录
     * @param toDir   如果目录不存在，是否创建
     * @throws FatalFileException 文件处理异常
     */
    public static void moveDirectory(File srcDir, File destDir, boolean toDir) {
        Assert.notNull(srcDir, "Source must not be null.");
        Assert.notNull(destDir, "Destination must not be null.");
        if (!srcDir.exists()) throw new FatalFileException("Source [" + srcDir + "] does not exist.");
        if (!srcDir.isDirectory()) throw new FatalFileException("Destination [" + srcDir + "] is not a directory.");
        if (destDir.exists() && !destDir.isDirectory())
            throw new FatalFileException("Destination [" + destDir + "] is not a directory.");

        File targetDir = toDir ? new File(destDir, srcDir.getName()) : destDir;

        if (!targetDir.mkdirs()) throw new FatalFileException("Directory [" + targetDir + "] could not be created.");
        boolean renameTo = srcDir.renameTo(targetDir);
        if (!renameTo) {
            copyDirectory(srcDir, targetDir);
            delete(srcDir);
            if (srcDir.exists())
                throw new FatalFileException(String.format("Failed to delete original directory '%s' after copy to '%s'", srcDir, destDir));
        }
    }

    //--------------------------------delete--------------------------------

    /**
     * 文件删除，支持目录删除
     *
     * @param file 文件
     * @throws FatalFileException 文件处理异常
     */
    public static void delete(File file) {
        Assert.notNull(file, "File must not be null.");
        if (!file.exists()) return;
        if (file.isDirectory()) {
            cleanDirectory(file);
        }
        if (!file.delete()) {
            throw new FatalFileException("Unable to delete file: " + file);
        }
    }

    /**
     * 清理目录
     *
     * @param directory 目录
     * @throws FatalFileException 文件处理异常
     */
    public static void cleanDirectory(File directory) {
        Assert.notNull(directory, "Directory must not be null.");
        if (!directory.exists()) throw new FatalFileException("Directory [" + directory + "] does not exist.");
        if (!directory.isDirectory()) throw new FatalFileException("The [" + directory + "] is not a directory.");
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            throw new FatalFileException("Failed to list contents of " + directory);
        }
        for (File listFile : listFiles) {
            if (listFile.isDirectory()) {
                cleanDirectory(listFile);
            }
            if (!listFile.delete()) {
                throw new FatalFileException("Unable to delete file: " + listFile);
            }
        }
    }

    /**
     * 打开文件的输入流，提供了比<code>new FileInputStream(file)</code>更好更优雅的方式.
     *
     * @param file 文件
     * @return {@link FileInputStream}
     * @throws FatalFileException 文件处理异常
     */
    public static FileInputStream openFileInputStream(File file) {
        Assert.notNull(file, "File must not be null.");
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new FatalFileException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new FatalFileException("File '" + file + "' cannot be read");
            }
            try {
                return new FileInputStream(file);
            } catch (IOException e) {
                throw new FatalFileException(e);
            }
        }
        throw new FatalFileException("File '" + file + "' does not exist");
    }

    /**
     * 打开件输出流
     *
     * @param file 文件
     * @return {@link FileOutputStream}
     */
    public static FileOutputStream openFileOutputStream(File file) {
        return openFileOutputStream(file, false);
    }

    /**
     * 打开件输出流
     *
     * @param file   文件
     * @param append 附加
     * @return {@link FileOutputStream}
     */
    private static FileOutputStream openFileOutputStream(File file, boolean append) {
        Assert.notNull(file, "File must not be null.");
        if (file.exists()) {
            if (file.isDirectory())
                throw new FatalFileException("Destination [" + file + "] exists but is a directory.");
            if (!file.canWrite())
                throw new FatalFileException(String.format("Destination [%s] exists but cannot write.", file));
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory())
                    throw new FatalFileException("Directory [" + parent + "] could not be created.");
            }
        }
        try {
            return new FileOutputStream(file, append);
        } catch (IOException e) {
            throw new FatalFileException(e);
        }
    }

    //region--------------------------------checksum-----------------------------

    /**
     * 计算文件的MD5值
     *
     * @param file 文件
     * @return 返回文件的md5
     */
    public static String md5Hex(File file) {
        FileInputStream in = null;
        try {
            in = openFileInputStream(file);
            return IOUtils.md5Hex(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 计算文件的SHA-1值
     *
     * @param file 文件
     * @return 返回文件的sha-1
     */
    public static String sha1Hex(File file) {
        FileInputStream in = null;
        try {
            in = openFileInputStream(file);
            return IOUtils.sha1Hex(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    //endregion-----------------------------checksum-----------------------------

    //region--------------------------------others-------------------------------

    /**
     * 将文件大小格式化输出
     *
     * @param fileSize 文件大小，单位为{@code Byte}
     * @return 格式化的大小
     */
    public static String formatSize(long fileSize) {
        if (fileSize < 0) return StringUtils.EMPTY_STRING;
        return formatSize((double) fileSize);
    }

    /**
     * 将文件大小格式化输出
     *
     * @param fileSize 文件大小，单位为{@code Byte}
     * @return 格式化的大小
     */
    public static String formatSize(double fileSize) {
        if (fileSize < 0) return StringUtils.EMPTY_STRING;
        //byte
        double size = fileSize;
        if (size < 1024) {
            return size + "Byte";
        }
        size /= 1024;
        if (size < 1024) {
            return Math.round(size * 100) / 100.0 + "KB";
        }
        size /= 1024;
        if (size < 1024) {
            return Math.round(size * 100) / 100.0 + "MB";
        }
        size /= 1024;
        if (size < 1024) {
            return Math.round(size * 100) / 100.0 + "GB";
        }
        return Math.round(size * 100) / 100.0 + "TB";
    }

    /**
     * 将文件大小格式化输出
     *
     * @param fileSize 文件大小，单位为{@code Byte}
     * @return 格式化的大小
     */
    public static String formatSizeAsString(String fileSize) {
        if (StringUtils.isEmpty(fileSize)) return StringUtils.EMPTY_STRING;
        double size = Double.parseDouble(fileSize);
        return formatSize(size);
    }

    /**
     * 创建目录
     *
     * @param directoryPath 目录地址
     */
    public static void forceMakeDir(String directoryPath) {
        forceMakeDir(new File(directoryPath));
    }

    /**
     * 创建目录
     *
     * @param directory 目录地址
     */
    public static void forceMakeDir(File directory) {
        if (directory.exists()) {
            if (!directory.isDirectory())
                throw new FatalFileException("The file[" + directory + "] exists and is not a directory.Unable to create directory.");
        } else if (!directory.mkdirs() && !directory.isDirectory())
            throw new FatalFileException("Unable to create directory[" + directory + "]");
    }
    //endregion-----------------------------others-------------------------------
}
