package com.xxx.lib.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blankj.utilcode.util.Utils
import com.xxx.lib.R

/**
 * 通知工具类
 * →_→
 *  2021/03/17 12:22
 * 769856557@qq.com
 * yangyong
 */
object NotificationUtils {
    private const val CHANNEL_ID = "default"
    private const val CHANNEL_NAME = "默认"

    /**
     * 发送通知
     * @param id       通知id
     * @param title    通知标题
     * @param content  通知内容
     * @param defaults 通知设置，声音，震动，闪光灯
     * @param priority 优先级
     * @param intent   跳转intent
     * @param channelId 渠道ID
     * @param channelName 渠道名称
     */
    fun sendNotification(
        id: Int,
        title: String?,
        content: String?,
        defaults: Int = NotificationCompat.DEFAULT_ALL,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        intent: Intent? = null,
        channelId: String = CHANNEL_ID,
        channelName: String = CHANNEL_NAME
    ) {
        if (title.isNullOrBlank() || content.isNullOrBlank()) return
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(Utils.getApp(), channelId)
        builder.setLargeIcon(
            BitmapFactory.decodeResource(
                Utils.getApp().resources,
                R.mipmap.ic_launcher
            )
        )
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.setDefaults(defaults)
        builder.setAutoCancel(true)
        builder.priority = priority
        if (intent != null) {
            val pendingIntent = PendingIntent.getActivity(
                Utils.getApp(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            builder.setContentIntent(pendingIntent)
        }
        val notificationManager =
            Utils.getApp().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(id, builder.build())
    }

    /**
     * 检查通知设置
     */
    fun checkNotificationSetting(): Boolean {
        return NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled()
    }
}