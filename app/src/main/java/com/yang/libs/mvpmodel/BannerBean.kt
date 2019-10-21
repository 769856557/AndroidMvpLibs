package com.yang.libs.mvpmodel

/*
* 
* 2019/6/15 15:33
* yangyong
*/
data class BannerBean(
    val app: List<App>
) {
    data class App(
        val app_name: String,
        val city_name: String,
        val id: String,
        val letter: String,
        val site_url: String
    )
}

