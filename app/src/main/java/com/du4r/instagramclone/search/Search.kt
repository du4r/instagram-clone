package com.du4r.instagramclone.search

import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView
import com.du4r.instagramclone.common.model.UserAuth

interface Search {

    interface Presenter: BasePresenter{
        fun fetchUsers(name: String)
    }

    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayFullUsers(users: List<UserAuth>)
        fun displayEmptyUsers()
    }
}