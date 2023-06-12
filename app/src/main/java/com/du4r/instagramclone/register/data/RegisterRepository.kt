package com.du4r.instagramclone.register.data

import android.net.Uri

class RegisterRepository(private val dataSource: RegisterDataSource) {

    fun create(email:String, callBack: RegisterCallBack){
        dataSource.create(email,callBack)
    }

    fun create(email:String,name:String, password: String, callBack: RegisterCallBack){
        dataSource.create(email,name,password,callBack)
    }

    fun updateUser(photoUri: Uri, callBack: RegisterCallBack){
        dataSource.updateUser(photoUri,callBack)
    }
}