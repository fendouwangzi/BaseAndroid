package com.zzg.baseandroid.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Author: zhongzhigang
 * Create Date: 2017/06/6.
 */

public class CacheUtil {

    public static String getCacheDir(Context context){
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public static String getChoiceImageCacheDir(Context context){
        return getCacheDir(context)+ File.separator+"tmp";
    }

    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if(item.isFile()) {
                    item.delete();
                }else{
                    deleteFilesByDirectory(item);
                }
            }
        }
    }
}
