package com.du4r.instagramclone.add

import android.net.Uri
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface Add {

    interface Presenter: BasePresenter{
        fun createPost(uri: Uri, caption: String)
    }
    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFailure(message: String)
    }
}