package com.du4r.instagramclone.splash.presentation

import com.du4r.instagramclone.splash.Splash
import com.du4r.instagramclone.splash.data.SplashCallback
import com.du4r.instagramclone.splash.data.SplashRepository

class SplashPresenter(
    private var view: Splash.View?,
    private var repository: SplashRepository
): Splash.Presenter {
    override fun authenticated() {
        repository.session(object :SplashCallback{
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })
    }

    override fun onDestroy() {
       view = null
    }
}