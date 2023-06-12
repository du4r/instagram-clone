package com.du4r.instagramclone.home.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

object FeedMemoryCache: Cache<List<Post>> {

    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>?) {
        posts = data
    }
}