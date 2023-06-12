package com.du4r.instagramclone.profile.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class ProfileLocalDataSource(
    private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
    private val postsCache: Cache<List<Post>>,
): ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth,Boolean?>>) {
       val userAuth = profileCache.get(userUUID)
        if (userAuth != null){
            callback.onSuccess(userAuth)
        }else{
            callback.onFailure("usuario nao encontrado")
        }
        callback.onComplete()
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = postsCache.get(userUUID)

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


    override fun putUser(response:Pair<UserAuth,Boolean?>) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }
}