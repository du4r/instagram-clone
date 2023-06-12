package com.du4r.instagramclone.common.base

import android.content.Context
import com.du4r.instagramclone.add.data.AddFakeLocalDataSource
import com.du4r.instagramclone.add.data.AddFakeRemoteDataSource
import com.du4r.instagramclone.add.data.AddRepository
import com.du4r.instagramclone.home.data.FeedMemoryCache
import com.du4r.instagramclone.home.data.HomeDataSourceFactory
import com.du4r.instagramclone.home.data.HomeRepository
import com.du4r.instagramclone.login.data.FakeDataSource
import com.du4r.instagramclone.login.data.LoginRepository
import com.du4r.instagramclone.post.data.PostLocalDataSource
import com.du4r.instagramclone.post.data.PostRepository
import com.du4r.instagramclone.profile.data.*
import com.du4r.instagramclone.register.data.FakeRegisterDataSource
import com.du4r.instagramclone.register.data.RegisterRepository
import com.du4r.instagramclone.search.data.SearchFakeRemoteDataSource
import com.du4r.instagramclone.search.data.SearchRepository
import com.du4r.instagramclone.splash.data.FakeLocalDataSource
import com.du4r.instagramclone.splash.data.SplashRepository

object DependencyInjector {

    fun SplashRepository(): SplashRepository{
        return SplashRepository(FakeLocalDataSource())
    }

    fun loginRepository(): LoginRepository{
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileRepository(): ProfileRepository{
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostsMemoryCache))
    }

    fun homeRepository(): HomeRepository{
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }

    fun addRepository(): AddRepository{
        return AddRepository(AddFakeRemoteDataSource(), AddFakeLocalDataSource())
    }

    fun postRepository(context: Context): PostRepository{
        return PostRepository(PostLocalDataSource(context))
    }

    fun searchRepository(context: Context): SearchRepository{
        return SearchRepository(SearchFakeRemoteDataSource())
    }

}
