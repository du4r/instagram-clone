package com.du4r.instagramclone.post.data

import android.net.Uri

interface PostDataSource {

    suspend fun fetchPictures(): List<Uri>

}