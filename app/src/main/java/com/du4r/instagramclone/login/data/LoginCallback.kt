package com.du4r.instagramclone.login.data

interface LoginCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}