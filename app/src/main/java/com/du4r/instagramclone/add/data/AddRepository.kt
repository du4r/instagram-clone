package com.du4r.instagramclone.add.data

import android.net.Uri
import com.du4r.instagramclone.common.base.RequestCallback

class AddRepository(
    private val remoteDataSource: AddFakeRemoteDataSource,
    private val localDataSource: AddFakeLocalDataSource){

        fun createPost(uri: Uri,caption: String,callback: RequestCallback<Boolean>){
            val userAuth = localDataSource.fetchSession()

            remoteDataSource.createPost(userAuth.UUID,uri,caption,object : RequestCallback<Boolean>{
                override fun onSuccess(data: Boolean) {
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