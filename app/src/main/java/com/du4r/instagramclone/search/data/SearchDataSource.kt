package com.du4r.instagramclone.search.data

import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.UserAuth

interface SearchDataSource {
    fun fetchUsers(name: String,callback: RequestCallback<List<UserAuth>>)
}