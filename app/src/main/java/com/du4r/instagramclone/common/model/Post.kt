package com.du4r.instagramclone.common.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long,
    val publisher: UserAuth
    )
