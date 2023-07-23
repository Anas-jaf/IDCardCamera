package com.wildma.idcardcamera.utils;

import android.content.Context;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/12/30
 * Desc	        ${屏幕相关工具类}
 */
public class ScreenUtils {

    /**
     * Get screen width（px）
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * Get screen height（px）
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
