package com.du4r.instagramclone.post.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.du4r.instagramclone.R

class AddAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    val tabs = arrayOf(R.string.photo, R.string.gallery)

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return when(tabs[position]){
            R.string.photo -> CameraFragment()
            R.string.gallery -> GalleryFragment()
            else -> throw IllegalArgumentException("Fragment not found")
        }
    }
}