package com.du4r.instagramclone.home

import com.du4r.instagramclone.common.base.BasePresenter
import com.du4r.instagramclone.common.base.BaseView
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.common.model.UserAuth

interface Home {

    interface Presenter: BasePresenter{
        fun fetchFeed()
        fun clear()
    }

    interface View: BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}