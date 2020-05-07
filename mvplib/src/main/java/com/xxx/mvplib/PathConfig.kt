package com.xxx.mvplib

import android.os.Environment
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * 路径配置
 * →_→
 * 2019/9/5 15:47
 * 769856557@qq.com
 * yangyong
 */
object PathConfig {
    /* -------------------------------- /storage/emulated/0/包名 相关路径---------------------------------*/
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
    val DIR_PHOTO_PICTURESELECTOR by lazy {
        "/${AppUtils.getAppPackageName()}${File.separator}photo".also {
            FileUtils.createOrExistsDir("$DIR_APP${File.separator}photo")
        }
    }

    /**
     * 照片保存文件夹路径,只能用于PictureSelector，/storage/emulated/0/包名/photo
     */
    val DIR_IMAGE_PICTURESELECTOR by lazy {
        "/${AppUtils.getAppPackageName()}${File.separator}image".also {
            FileUtils.createOrExistsDir("$DIR_APP${File.separator}image")
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

    /* -------------------------------- /storage/emulated/0/Android/data/包名 相关路径---------------------------------*/

    /**
     *okhttp缓存文件夹路径，/storage/emulated/0/Android/data/包名/cache/okhttp
     */
    val DIR_CACHE_OKHTTP by lazy {
        "${Utils.getApp().externalCacheDir.absolutePath}${File.separator}okhttp".also {
            FileUtils.createOrExistsDir(it)
        }
    }

    /**
     *glide缓存文件夹路径，/storage/emulated/0/Android/data/包名/cache/glide
     */
    val DIR_CACHE_GLIDE by lazy {
        "glide".also {
            FileUtils.createOrExistsDir("${Utils.getApp().externalCacheDir.absolutePath}${File.separator}$it")
        }
    }

}