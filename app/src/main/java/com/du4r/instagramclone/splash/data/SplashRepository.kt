package com.du4r.instagramclone.splash.data

class SplashRepository(private val dataSource: SplashDataSource){

    fun session(callback: SplashCallback){
        dataSource.session(callback)
    }
}