package com.du4r.instagramclone.search.presentation

import com.du4r.instagramclone.common.base.RequestCallback
import com.du4r.instagramclone.common.model.UserAuth
import com.du4r.instagramclone.search.Search
import com.du4r.instagramclone.search.data.SearchRepository

class SearchPresenter(
    private var view: Search.View?,
    private val repository: SearchRepository
) : Search.Presenter {

    override fun fetchUsers(name: String) {
        view?.showProgress(true)

        repository.fetchUserPosts(name, object : RequestCallback<List<UserAuth>> {
            override fun onSuccess(data: List<UserAuth>) {
                if (data.isEmpty()) {
                    view?.displayEmptyUsers()
                } else {
                    view?.displayFullUsers(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayEmptyUsers()
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