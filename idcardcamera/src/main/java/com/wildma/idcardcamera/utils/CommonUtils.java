package com.wildma.idcardcamera.utils;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2019/07/16
 * Desc	        ${公用工具类}
 */
public class CommonUtils {

    private static long lastClickTime;

    /**
     * Determine whether it is a quick click
     *
     * @return true: yes, false: no
     */
    public static boolean isFastClick() {
        return isFastClick(1000);
    }

    /**
     * Determine whether it is a quick click
     *
     * @param intervalTime Interval time in milliseconds.
     * @return true: yes, false: no
     */
    public static boolean isFastClick(long intervalTime) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < intervalTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
