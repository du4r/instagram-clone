package com.du4r.instagramclone.register.presentation

import com.du4r.instagramclone.R
import com.du4r.instagramclone.register.RegisterNameAndPassword
import com.du4r.instagramclone.register.data.RegisterCallBack
import com.du4r.instagramclone.register.data.RegisterRepository


class RegisterNameAndPasswordPresenter(
   private var view: RegisterNameAndPassword.View?,
   private val repository: RegisterRepository
): RegisterNameAndPassword.Presenter {

    override fun create(email: String, name: String, password: String, confirm: String) {
        val isNamesValid = name.length > 3
        val isPasswordValid = password.length >= 8
        val isConfirmValid = password == confirm

        if (!isNamesValid){
            view?.displayNameFailure(R.string.invalid_name)
        }else{
            view?.displayNameFailure(null)
        }


        if(!isConfirmValid){
            view?.displayPasswordFailure(R.string.password_not_equal)
        }else{
            if(!isPasswordValid){
                view?.displayPasswordFailure(R.string.invalid_password)
            }else{
                view?.displayPasswordFailure(null)
            }
        }

        if (isNamesValid && isPasswordValid && isConfirmValid){
            view?.showProgress(true)

            repository.create(email,name,password, object : RegisterCallBack {
                override fun onSuccess() {
                    view?.onCreateSuccess(name)
                }

                override fun onFailure(message: String) {
                   view?.onCreateFailure(message)
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