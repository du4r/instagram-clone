package com.du4r.instagramclone.splash

import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView

interface Splash {

    interface Presenter: BasePresenter{
        fun authenticated()
    }

    interface View: BaseView<Presenter>{
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}