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
package com.mzlion.core.utils;

import com.mzlion.core.exceptions.FileArchiveException;
import com.mzlion.core.lang.Assert;
import com.mzlion.core.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * ZIP的压缩和解压工具
 *
 * @author mzlion on 2016/7/26.
 */
public class ZipUtils {

    /**
     * zip压缩,如果参数校验失败则直接返回{@code false},默认采用UTF8编码
     *
     * @param zipPath 压缩后保存的文件路径
     * @param files   待压缩的文件列表
     */
    public static void pack(String zipPath, File... files) {
        pack(zipPath, StandardCharsets.UTF_8, files);
    }

    /**
     * zip压缩,如果参数校验失败则直接返回{@code false}
     *
     * @param zipPath  压缩后保存的文件路径
     * @param files    待压缩的文件列表
     * @param encoding zip文件编码
     */
    public static void pack(String zipPath, Charset encoding, File... files) {
        if (StringUtils.hasLength(zipPath)) pack(new File(zipPath), encoding, files);
    }

    /**
     * zip压缩,如果参数校验失败则直接返回{@code false},默认采用UTF8编码
     *
     * @param zipFile 压缩后保存的文件路径
     * @param files   待压缩的文件列表
     */
    public static void pack(File zipFile, File... files) {
        pack(zipFile, StandardCharsets.UTF_8, files);
    }

    /**
     * zip压缩,如果参数校验失败则直接返回{@code false}
     *
     * @param zipFile  压缩后保存的文件路径
     * @param files    待压缩的文件列表
     * @param encoding zip文件编码
     */
    public static void pack(final File zipFile, final Charset encoding, File... files) {
        Assert.notNull(zipFile, "ZipFile is null.");
        Assert.notNull(encoding, "Encoding is null.");
        Assert.notEmpty(files, "Files is null or empty.");
        List<Path> pathList = new LinkedList<>();
        for (int i = 0, length = files.length; i < length; i++) {
            Assert.notNull(files[i], "The index[" + i + "] is null.");
            pathList.add(files[i].toPath());
        }
        Path[] paths = new Path[files.length];
        pathList.toArray(paths);
        pack(zipFile.toPath(), encoding, paths);
    }

    public static void pack(final Path zipPath, Path... paths) {
        pack(zipPath, StandardCharsets.UTF_8, paths);
    }

    public static void pack(final Path zipPath, final Charset encoding, Path... paths) {
        Assert.notNull(zipPath, "The zipPath is null.");
        Assert.notNull(encoding, "The encoding is null.");
        Assert.notNull(encoding, "The paths is null or empty.");
        validate(zipPath, paths);

        try (FileOutputStream fos = new FileOutputStream(zipPath.toFile());
             final ZipOutputStream zos = new ZipOutputStream(fos, encoding)) {

            for (Path path : paths) {
                final Path parent = path.getParent();
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (Files.size(dir) == 0) {
                            zos.putNextEntry(new ZipEntry(parent.relativize(dir).toString() + "/"));
                            zos.closeEntry();
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        zos.putNextEntry(new ZipEntry(parent.relativize(file).toString()));
                        Files.copy(file, zos);
                        zos.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (IOException e) {
            throw new FileArchiveException(e);
        }
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}，默认采用UTF8编码
     *
     * @param zipPath   zip文件路径
     * @param toDirPath 解压到指定目录
     */
    public static void unpack(String zipPath, String toDirPath) {
        unpack(zipPath, toDirPath, StandardCharsets.UTF_8);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}
     *
     * @param zipPath   zip文件路径
     * @param toDirPath 解压到指定目录
     * @param encoding  zip文件编码
     */
    public static void unpack(String zipPath, String toDirPath, Charset encoding) {
        if (StringUtils.hasLength(zipPath)) unpack(new File(zipPath), toDirPath, encoding);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}，默认采用UTF8编码
     *
     * @param zipPath   zip文件路径
     * @param toDirFile 解压到指定目录
     */
    public static void unpack(String zipPath, File toDirFile) {
        unpack(zipPath, toDirFile, StandardCharsets.UTF_8);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}
     *
     * @param zipPath   zip文件路径
     * @param toDirFile 解压到指定目录
     * @param encoding  zip文件编码
     */
    public static void unpack(String zipPath, File toDirFile, Charset encoding) {
        if (StringUtils.hasLength(zipPath)) unpack(new File(zipPath), toDirFile, encoding);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}，默认采用UTF8编码
     *
     * @param zipFile   zip文件路径
     * @param toDirFile 解压到指定目录
     */
    public static void unpack(File zipFile, File toDirFile) {
        unpack(zipFile, toDirFile, StandardCharsets.UTF_8);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}，默认采用UTF8编码
     *
     * @param zipFile   zip文件路径
     * @param toDirPath 解压到指定目录
     */
    public static void unpack(File zipFile, String toDirPath) {
        unpack(zipFile, toDirPath, StandardCharsets.UTF_8);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}
     *
     * @param zipFile   zip文件路径
     * @param toDirPath 解压到指定目录
     * @param encoding  zip文件编码
     */
    public static void unpack(File zipFile, String toDirPath, Charset encoding) {
        if (StringUtils.hasLength(toDirPath)) unpack(zipFile, new File(toDirPath), encoding);
    }

    /**
     * zip解压,如果参数校验失败则直接返回{@code false}
     *
     * @param zipFile   zip文件路径
     * @param toDirFile 解压到指定目录
     * @param encoding  zip文件编码
     */
    public static void unpack(final File zipFile, final File toDirFile, final Charset encoding) {
        Assert.notNull(zipFile, "The zipFile is null.");
        Assert.notNull(encoding, "The encoding is null.");
        Assert.notNull(toDirFile, "The toDirFile is null.");
        unpack(zipFile.toPath(), toDirFile.toPath(), encoding);
    }

    public static void unpack(final Path zipPath, final Path toDirPath) {
        unpack(zipPath, toDirPath, StandardCharsets.UTF_8);
    }

    public static void unpack(final Path zipPath, final Path toDirPath, final Charset encoding) {
        Assert.notNull(zipPath, "The zipPath is null.");
        Assert.notNull(encoding, "The encoding is null.");
        Assert.notNull(toDirPath, "The toDirPath is null.");
        if (!StringUtils.endsWithIgnoreCase(zipPath.getFileName().toString(), "zip"))
            throw new FileArchiveException("The zipFile is not a zip file.");
        if (!zipPath.toFile().exists()) throw new FileArchiveException("The zipFile does't exist.");
        if (toDirPath.toFile().exists() && !toDirPath.toFile().isDirectory())
            throw new FileArchiveException("The toDirPath is not a directory.");

        FileSystemProvider provider = getZipFS();
        Map<String, Object> env = new HashMap<>();

        try (FileSystem fs = provider.newFileSystem(zipPath, env)) {
            extract(fs, toDirPath.toString(), null);
        } catch (IOException e) {
            throw new FileArchiveException(e);
        }

//        try {
//            Files.walkFileTree(zipPath, new SimpleFileVisitor<Path>() {
//
//                @Override
//                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                    Files.createDirectories(toDirPath.resolve(dir));
//                    return FileVisitResult.CONTINUE;
//                }
//
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    Path path = toDirPath.resolve(file);
//                    Files.copy(file, path);
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        } catch (IOException e) {
//            throw new FileArchiveException(e);
//        }
    }

    public static void unpackx(final Path zipPath, String dir) {
        FileSystemProvider provider = getZipFS();
        Map<String, Object> env = new HashMap<>();

        try (FileSystem fs = provider.newFileSystem(zipPath, env)) {
            extract(fs, "/", Paths.get(dir));
        } catch (IOException e) {
            throw new FileArchiveException(e);
        }

    }

    private static void extract(FileSystem fs, String path, Path destPath) throws IOException {
        Path src = fs.getPath(path);
        if (Files.isDirectory(src)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(src)) {
                for (Path child : ds)
                    extract(fs, child.toString(), destPath);
            }
        } else {
            if (path.startsWith("/"))
                path = path.substring(1);
//            Path dst = FileSystems.getDefault().getPath(path);
            Path dst = destPath.resolve(path);
            Path parent = dst.getParent();
            if (Files.notExists(parent))
                mkdirs(parent);
            Files.copy(src, dst, REPLACE_EXISTING);
        }
    }

    private static void mkdirs(Path path) throws IOException {
        path = path.toAbsolutePath();
        Path parent = path.getParent();
        if (parent != null) {
            if (Files.notExists(parent))
                mkdirs(parent);
        }
        Files.createDirectory(path);
    }

    private static void validate(Path zipPath, Path... paths) {
        if (zipPath.toFile().isDirectory()) throw new FileArchiveException("The zipPath is a directory.");
        Path zipParent = zipPath.getParent();
        for (Path path : paths) {
            if (!path.toFile().exists())
                throw new FileArchiveException("The path[" + path.toString() + "] does not exist.");
            //当目标目录是原目录的子目录时,不支持压缩.
            if (path.toFile().isDirectory()) {
                if (zipParent.startsWith(path)) {
                    throw new FileArchiveException(String.format("Destination [%s] is child directory of source [%s].", zipParent, path));
                }
            } else {
                if (zipParent.startsWith(path.getParent())) {
                    throw new FileArchiveException(String.format("Destination [%s] is child directory of source [%s].", zipParent, path.getParent()));
                }
            }
        }
    }

    public static FileSystemProvider getZipFS() {
        for (FileSystemProvider fileSystemProvider : FileSystemProvider.installedProviders()) {
            if ("jar".equals(fileSystemProvider.getScheme())) {
                return fileSystemProvider;
            }
        }
        throw new FileArchiveException("ZIP filesystem provider is not installed");
    }

}
