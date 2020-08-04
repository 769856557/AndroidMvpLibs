package com.xxx.lib

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * Glide配置，需使用GlideApp配置才有效果
 * →_→
 * 2019/12/5 10:29
 * 769856557@qq.com
 * yangyong
 */
@GlideModule
class GlideConfig : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder
            .setDiskCache(
                ExternalPreferredCacheDiskCacheFactory(
                    context,
                    PathConfig.DIR_CACHE_GLIDE,
                    DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE.toLong()
                )
            )
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.mipmap.ic_default_img)
                    .error(R.mipmap.ic_default_img)
            )
    }
}