package com.du4r.instagramclone.common.model

import android.net.Uri

data class UserAuth(
    val UUID: String,
    val name: String,
    val email: String,
    val password: String,
    val photoUri: Uri?,
    val postCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0,
    val bio: String = ""
)