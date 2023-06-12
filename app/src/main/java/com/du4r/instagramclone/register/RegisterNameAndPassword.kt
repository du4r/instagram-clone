package com.du4r.instagramclone.register

import androidx.annotation.StringRes
import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface RegisterNameAndPassword {
    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSuccess(name: String)
    }
}