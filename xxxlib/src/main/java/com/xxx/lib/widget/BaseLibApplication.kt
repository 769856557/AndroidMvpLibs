package com.xxx.lib.widget

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.tools.PictureFileUtils
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import java.util.*

/**
 * →_→
 * 2020/8/17 11:24
 * 769856557@qq.com
 * yangyong
 */
open class BaseLibApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //通用工具库初始化
        Utils.init(this)
        // 腾讯tbs初始化
        val map = HashMap<String, Any>().also {
            it[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
            it[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        }
        QbSdk.initTbsSettings(map as Map<String, Any>)
        QbSdk.initX5Environment(this, null)
        // 清除垃圾文件
        claerRubbish()
    }

    /**
     * 清除垃圾文件
     */
    private fun claerRubbish() {
        Thread(Runnable {
            //图片选择相关
            //包括裁剪和压缩后的缓存，要在上传成功后调用，type 指的是图片or视频缓存取决于你设置的ofImage或ofVideo 注意：需要系统sd卡权限
            PictureFileUtils.deleteCacheDirFile(this, PictureMimeType.ofImage())
            // 清除所有缓存 例如：压缩、裁剪、视频、音频所生成的临时文件
            PictureFileUtils.deleteAllCacheDirFile(this)
        }).start()
    }
}