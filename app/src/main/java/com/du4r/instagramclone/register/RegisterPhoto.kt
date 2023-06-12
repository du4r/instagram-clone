package com.du4r.instagramclone.register

import android.net.Uri
import androidx.annotation.StringRes
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter{
        fun updateUser(photoUri: Uri)
    }

    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun onUpdateFailure(message: String)
        fun onUpdateSuccess()
    }

}