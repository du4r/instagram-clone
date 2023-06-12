package com.du4r.instagramclone.home.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class HomeLocalDataSource(
    private val feedCache: Cache<List<Post>>): HomeDataSource {

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {

        val posts = feedCache.get(userUUID)

        if (posts != null){
            callback.onSuccess(posts)
        }else{
            callback.onFailure("posts nao existem")
        }
        callback.onComplete()
    }

    override fun fetchSession(): UserAuth {
       return DataBase.sessionAuth ?: throw RuntimeException("usuario nao logado")
    }

    override fun putFeed(response: List<Post>?) {
        feedCache.put(response)
    }
}