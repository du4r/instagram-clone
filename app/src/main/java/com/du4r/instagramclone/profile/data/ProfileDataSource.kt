package com.du4r.instagramclone.profile.data

import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth,Boolean?>>)

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession(): UserAuth { throw UnsupportedOperationException()}

    fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>){throw UnsupportedOperationException()}

    fun putUser(response:Pair<UserAuth, Boolean?>) { throw UnsupportedOperationException()}

    fun putPosts(response: List<Post>?) { throw UnsupportedOperationException()}
}