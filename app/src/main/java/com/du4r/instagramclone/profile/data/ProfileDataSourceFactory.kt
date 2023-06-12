package com.du4r.instagramclone.profile.data

import com.du4r.instagramclone.common.base.Cache
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: Cache<Pair<UserAuth,Boolean?>>,
    private val postCache: Cache<List<Post>>,
    ) {

    fun createLocalDataSource() : ProfileDataSource{
        return ProfileLocalDataSource(profileCache, postCache)
    }

    fun createRemoteDataSource() : ProfileDataSource{
        return FakeProfileRemoteDataSource()
    }

    fun createFromUser(uuid: String?): ProfileDataSource{
        if(uuid != null){
            return FakeProfileRemoteDataSource()
        }

        if (profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postCache)
        }
        return FakeProfileRemoteDataSource()
    }

    fun createFromPosts(uuid: String?): ProfileDataSource{
        if (uuid != null){
            return FakeProfileRemoteDataSource()
        }

        if(postCache.isCached()){
            return ProfileLocalDataSource(profileCache, postCache)
        }
        return FakeProfileRemoteDataSource()
    }

}