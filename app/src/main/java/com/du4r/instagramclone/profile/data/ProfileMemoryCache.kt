package com.du4r.instagramclone.profile.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.model.UserAuth

object ProfileMemoryCache: Cache<Pair<UserAuth,Boolean?>> {
    private var userAuth: Pair<UserAuth, Boolean?>? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<UserAuth,Boolean?>? {
        if (userAuth?.first?.UUID == key){
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth,Boolean?>?) {
        userAuth = data
    }
}