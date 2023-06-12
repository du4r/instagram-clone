package com.du4r.instagramclone.home.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
    ) {

    fun createLocalDataSource() : HomeDataSource{
        return HomeLocalDataSource(feedCache)
    }

    fun createFromFeed(): HomeDataSource{
        if (feedCache.isCached()){
            return HomeLocalDataSource(feedCache)
        }
        return FakeHomeRemoteDataSource()
    }
}