package com.du4r.instagramclone.register.presentation

import android.util.Patterns
import com.du4r.instagramclone.R
import com.du4r.instagramclone.register.RegisterEmail
import com.du4r.instagramclone.register.data.RegisterCallBack
import com.du4r.instagramclone.register.data.RegisterRepository


class RegisterEmailPresenter(
   private var view: RegisterEmail.View?,
   private val repository: RegisterRepository
): RegisterEmail.Presenter {

    override fun create(email: String) {
        //verifica se o email segue os padroes validos
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if(!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        }else{
            view?.displayEmailFailure(null)
        }

        if (isEmailValid){
            view?.showProgress(true)

            repository.create(email, object : RegisterCallBack{
                override fun onSuccess() {
                   view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }

    }

    override fun onDestroy() {
        view = null
    }
}