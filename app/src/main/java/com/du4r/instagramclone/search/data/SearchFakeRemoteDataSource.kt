package com.du4r.instagramclone.search.data

import android.os.Handler
import android.os.Looper
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.DataBase
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

class SearchFakeRemoteDataSource: SearchDataSource {


    override fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>) {

        Handler(Looper.getMainLooper()).postDelayed({
            val users = DataBase.usersAuth.filter {
                it.name.lowercase().startsWith(name.lowercase()) && it.UUID != DataBase.sessionAuth!!.UUID
            }
            callback.onSuccess(users.toList())
            callback.onComplete()
        }, 2000)

    }

}