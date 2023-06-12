package com.du4r.instagramclone.add.presentation

import android.net.Uri
import com.du4r.instagramclone.add.Add
import com.du4r.instagramclone.add.data.AddRepository
import com.du4r.instagramclone.common.base.RequestCallback

class AddPresenter(private var view: Add.View?,private var repository: AddRepository): Add.Presenter {

    override fun createPost(uri: Uri, caption: String) {
        view?.showProgress(true)

        repository.createPost(uri,caption, object : RequestCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
               if (data){
                   view?.displayRequestSuccess()
               }else{
                   view?.displayRequestFailure("internal Error")
               }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
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