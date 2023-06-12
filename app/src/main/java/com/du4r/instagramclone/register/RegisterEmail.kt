package com.du4r.instagramclone.register

import androidx.annotation.StringRes
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter{
        fun create(email: String)
    }

    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun onEmailFailure(message: String?)
        fun goToNameAndPasswordScreen(email: String)
    }

}