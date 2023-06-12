package com.du4r.instagramclone.register.data

interface RegisterCallBack {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}