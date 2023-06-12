package com.du4r.instagramclone.splash.data

import com.du4r.instagramclone.common.model.DataBase

class FakeLocalDataSource: SplashDataSource {
    override fun session(callback: SplashCallback) {
        if(DataBase.sessionAuth != null){
            callback.onSuccess()
        }else{
            callback.onFailure()
        }
    }
}