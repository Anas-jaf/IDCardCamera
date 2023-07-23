package com.wildma.idcardcamera.global;

import com.wildma.idcardcamera.utils.FileUtils;

import java.io.File;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/6/10
 * Desc	        ${常量}
 */
public class Constant {
    public static final String APP_NAME = "WildmaIDCardCamera";//app name
    public static final String BASE_DIR = APP_NAME + File.separator;//WildmaIDCardCamera/
    public static final String DIR_ROOT = FileUtils.getRootPath() + File.separator + Constant.BASE_DIR;//root folder  /storage/emulated/0/WildmaIDCardCamera/
}