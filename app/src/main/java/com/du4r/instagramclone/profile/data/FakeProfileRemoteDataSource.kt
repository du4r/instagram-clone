package com.du4r.instagramclone.profile.data

import android.os.Handler
import android.os.Looper
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class FakeProfileRemoteDataSource: ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth,Boolean?>>) {

        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = DataBase.usersAuth.firstOrNull{userUUID == it.UUID}

            if (userAuth != null){
                if (userAuth == DataBase.sessionAuth){
                    callback.onSuccess(Pair(userAuth, null))
                } else{
                    val followings = DataBase.followers[DataBase.sessionAuth?.UUID]

                    val destUser = followings?.firstOrNull{ it == userUUID}

                    callback.onSuccess(Pair(userAuth,destUser != null))
                }
            }else{
                callback.onFailure("Usuario nao encontrado")
            }

            callback.onComplete()
        }, 2000)

    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {

        Handler(Looper.getMainLooper()).postDelayed({
            val posts = DataBase.posts[userUUID]

            callback.onSuccess(posts?.toList() ?: emptyList())

            callback.onComplete()
        }, 2000)

    }

    override fun followUser(
        userUUID: String,
        isFollow: Boolean,
        callback: RequestCallback<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            var followers = DataBase.followers[DataBase.sessionAuth!!.UUID]

            if(followers == null){
                followers = mutableSetOf()
                DataBase.followers[DataBase.sessionAuth!!.UUID]!!.add(userUUID)
            }else{
                DataBase.followers[DataBase.sessionAuth!!.UUID]!!.remove(userUUID)
            }
        },500)
    }

}