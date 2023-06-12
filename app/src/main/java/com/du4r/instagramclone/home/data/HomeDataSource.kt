package com.du4r.instagramclone.home.data

import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

interface HomeDataSource {
    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession(): UserAuth { throw UnsupportedOperationException()}

    fun putFeed(response: List<Post>?) { throw UnsupportedOperationException()}
}