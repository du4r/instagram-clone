package com.du4r.instagramclone.home.data

import android.os.Handler
import android.os.Looper
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.Post

class FakeHomeRemoteDataSource : HomeDataSource {

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {

        Handler(Looper.getMainLooper()).postDelayed({
            val feed = DataBase.feeds[userUUID]

            callback.onSuccess(feed?.toList() ?: emptyList())

            callback.onComplete()
        }, 2000)

    }

}