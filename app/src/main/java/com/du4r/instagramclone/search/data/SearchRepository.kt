package com.du4r.instagramclone.search.data

import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class SearchRepository(private val dataSource: SearchDataSource) {


    fun fetchUserPosts(name: String, callback: RequestCallback<List<UserAuth>>) {
        dataSource.fetchUsers(name, object : RequestCallback<List<UserAuth>> {

            override fun onSuccess(data: List<UserAuth>) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }

}