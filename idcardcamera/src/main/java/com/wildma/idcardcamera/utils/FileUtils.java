package com.wildma.idcardcamera.utils;

import android.content.Context;
import android.os.Environment;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/6/10
 * Desc	        ${文件相关工具类}
 */
public final class FileUtils {

    /**
     * Get the root directory of the SD card, if the SD card is not available, get the root directory of the internal storage
     */
    public static File getRootPath() {
        File path = null;
        if (sdCardIsAvailable()) {
            path = Environment.getExternalStorageDirectory(); //SD卡根目录    /storage/emulated/0
        } else {
            path = Environment.getDataDirectory();//内部存储的根目录    /data
        }
        return path;
    }

    /**
     * Whether the SD card is available
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

    /**
     * Determine whether the directory exists, if it does not exist, determine whether the creation is successful
     *
     * @param dirPath file path
     * @return {@code true}: exists or created successfully<br>{@code false}: does not exist or failed to create
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * Determine whether the directory exists, if it does not exist, determine whether the creation is successful
     *
     * @param file file
     * @return {@code true}: exists or created successfully<br>{@code false}: does not exist or failed to create
     */
    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * Determine whether the file exists, if it does not exist, determine whether the creation is successful
     *
     * @param filePath file path
     * @return {@code true}: exists or created successfully<br>{@code false}: does not exist or failed to create
     */
    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    /**
     * Determine whether the file exists, if it does not exist, determine whether the creation is successful
     *
     * @param file file
     * @return {@code true}: exists or created successfully<br>{@code false}: does not exist or failed to create
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null)
            return false;
        // If it exists, it returns true if it is a file, and false if it is a directory
        if (file.exists())
            return file.isFile();
        if (!createOrExistsDir(file.getParentFile()))
            return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get the file according to the file path
     *
     * @param filePath file path
     * @return file
     */
    public static File getFileByPath(String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * Determine whether the string is null or all blank characters
     *
     * @param s
     * @return
     */
    private static boolean isSpace(final String s) {
        if (s == null)
            return true;
        for (int i = 0, len = s. length(); i < len; ++i) {
            if (!Character. isWhitespace(s. charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * close IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null)
            return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable. close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the directory of cached images
     *
     * @param context Context
     * @return directory of cached images
     */
    public static String getImageCacheDir(Context context) {
        File file;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            file = context. getCacheDir();
        }
        String path = file. getPath() + "/cache";
        File cachePath = new File(path);
        if (!cachePath. exists())
            cachePath.mkdir();
        return path;
    }

    /**
     * Delete all pictures in the cache picture directory
     *
     * @param context
     */
    public static void clearCache(Context context) {
        String cacheImagePath = getImageCacheDir(context);
        File cacheImageDir = new File(cacheImagePath);
        File[] files = cacheImageDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

}
