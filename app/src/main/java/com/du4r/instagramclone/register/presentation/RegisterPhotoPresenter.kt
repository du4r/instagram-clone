package com.du4r.instagramclone.register.presentation

import android.net.Uri
import android.util.Patterns
import com.du4r.instagramclone.R
import com.du4r.instagramclone.register.RegisterEmail
import com.du4r.instagramclone.register.RegisterPhoto
import com.du4r.instagramclone.register.data.RegisterCallBack
import com.du4r.instagramclone.register.data.RegisterRepository


class RegisterPhotoPresenter(
   private var view: RegisterPhoto.View?,
   private val repository: RegisterRepository
): RegisterPhoto.Presenter {

    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)

        repository.updateUser(photoUri,object : RegisterCallBack{
            override fun onSuccess() {
                view?.onUpdateSuccess()
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}