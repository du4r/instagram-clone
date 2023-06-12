package com.du4r.instagramclone.gallery.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.du4r.instagramclone.R

class GalleryFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

}