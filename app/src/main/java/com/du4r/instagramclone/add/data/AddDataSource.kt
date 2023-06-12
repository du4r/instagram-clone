package com.du4r.instagramclone.add.data

import android.net.Uri
import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.UserAuth

interface AddDataSource {
    fun createPost(userUUID: String, uri: Uri,caption: String, callback: RequestCallback<Boolean>){
        throw UnsupportedOperationException()
    }

    fun fetchSession(): UserAuth { throw UnsupportedOperationException()}
}