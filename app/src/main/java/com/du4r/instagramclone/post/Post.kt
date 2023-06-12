package com.du4r.instagramclone.post

import android.net.Uri
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface Post {

    interface Presenter: BasePresenter{
        fun fetchPictures()
        fun selectUri(uri:Uri)
        fun getSelectedUri(): Uri?
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayFullPictures(posts: List<Uri>)
        fun displayEmptyPictures()
        fun displayRequestFailure(message: String)
    }
}