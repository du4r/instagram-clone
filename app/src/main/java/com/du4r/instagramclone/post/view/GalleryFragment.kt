package com.du4r.instagramclone.post.view

import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.BaseFragment
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.databinding.FragmentGalleryBinding
import com.du4r.instagramclone.post.Post
import com.du4r.instagramclone.post.presentation.PostPresenter

class GalleryFragment : BaseFragment<FragmentGalleryBinding, Post.Presenter>
    (R.layout.fragment_gallery, FragmentGalleryBinding::bind), Post.View {

    private val adapter = PictureAdapter(){ uri ->
        binding?.galleryImgSelected?.setImageURI(uri)
        binding?.galleryNested?.smoothScrollTo(0, 0)
        presenter.selectUri(uri )
    }
    override lateinit var presenter: Post.Presenter

    override fun setupPresenter() {
        presenter = PostPresenter(this, DependencyInjector.postRepository(requireContext()))
    }

    override fun getMenu(): Int = R.menu.menu_send

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_send -> {
                setFragmentResult("takePhoto", bundleOf("uri" to presenter.getSelectedUri()))
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun setupViews() {
        binding?.galleryRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.galleryRv?.adapter = adapter

        presenter.fetchPictures()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.galleryProgress?.visibility =  if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayFullPictures(posts: List<Uri>) {
        binding?.galleryTxtEmpty?.visibility = View.GONE
        binding?.galleryRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
        binding?.galleryImgSelected?.setImageURI(posts[0])
        binding?.galleryNested?.smoothScrollTo(0,0)
    }

    override fun displayEmptyPictures() {
        binding?.galleryTxtEmpty?.visibility = View.VISIBLE
        binding?.galleryRv?.visibility = View.GONE
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}