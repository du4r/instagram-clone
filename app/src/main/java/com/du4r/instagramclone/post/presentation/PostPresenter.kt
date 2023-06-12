
package com.du4r.instagramclone.post.presentation

import android.net.Uri
import com.du4r.instagramclone.post.Post
import com.du4r.instagramclone.post.data.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostPresenter(
    private var view: Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {
    private var uri: Uri? = null
    val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO


    override fun fetchPictures() {

        view?.showProgress(true)
        launch {
            val pictures = repository.fetchPictures()

            withContext(Dispatchers.Main){
                if (pictures.isEmpty()){
                    view?.displayEmptyPictures()
                }else{
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }
        }
    }

    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
       return uri
    }

    override fun onDestroy() {
        job.cancel()
        view = null
    }

}