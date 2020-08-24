package com.xxx.lib.service

import android.app.IntentService
import android.content.Intent
import com.blankj.utilcode.util.CleanUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.PathUtils

/**
 * 垃圾清理服务
 * →_→
 * 2020/8/19 16:27
 * 769856557@qq.com
 * yangyong
 */
class ClearService : IntentService(ClearService::class.java.simpleName) {

    override fun onHandleIntent(intent: Intent?) {
        val path = PathUtils.getExternalAppFilesPath()
        if (FileUtils.getLength(path) > 1024L * 1024 * 200) {
            //大于200M，执行清理
            CleanUtils.deleteFilesInDir(path)
        }
    }

}