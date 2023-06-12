package com.du4r.instagramclone.login.data

interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)
}