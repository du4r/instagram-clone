package com.du4r.instagramclone.profile

import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

interface Profile {

    interface Presenter: BasePresenter{
        fun fetchUserPosts(uuid: String?)
        fun fetchUserProfile(uuid: String?)
        fun followUser(uuid: String?,follow: Boolean)
        fun clear()
    }

    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(user: Pair<UserAuth,Boolean?>)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}