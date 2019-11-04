package com.xxx.mvplib.utils

import android.os.Environment
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * 路径协助类，所有路径在该类声明
 * →_→
 * 2019/9/5 15:47
 * 769856557@qq.com
 * yangyong
 */
object PathUtils {
    /**
     * app根文件夹路径，/storage/emulated/0/包名
     */
    val DIR_APP by lazy {
        (Environment.getExternalStorageDirectory().absolutePath + File.separator + AppUtils.getAppPackageName()).also {
            FileUtils.createOrExistsDir(it)
        }
    }

    /**
     * 照片保存文件夹路径,只能用于PictureSelector，/storage/emulated/0/包名/photo
     */
    val DIR_PHOTO by lazy {
        "/${AppUtils.getAppPackageName()}${File.separator}photo".also {
            FileUtils.createOrExistsDir("$DIR_APP${File.separator}photo")
        }
    }

    /**
     * 图片保存文件夹路径，/storage/emulated/0/包名/image
     */
    val DIR_IMAGE by lazy {
        "$DIR_APP${File.separator}image".also {
            FileUtils.createOrExistsDir(it)
        }
    }

    /**
     *okhttp缓存文件夹路径，/storage/emulated/0/Android/data/包名/cache/okhttp
     */
    val CACHE_OKHTTP by lazy {
        "${Utils.getApp().externalCacheDir.absolutePath}${File.separator}okhttp".also {
            FileUtils.createOrExistsDir(it)
        }
    }

}