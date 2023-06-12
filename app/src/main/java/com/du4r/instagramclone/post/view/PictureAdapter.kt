package com.du4r.instagramclone.post.view

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.model.Post
import kotlinx.coroutines.withContext


class PictureAdapter(private val onClick: (Uri) -> Unit) : RecyclerView.Adapter<PictureAdapter.PostViewHolder>() {

    var items: List<Uri> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


   inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.Q)
        fun bind(image: Uri) {
            val bitmap = itemView.context.contentResolver.loadThumbnail(image, Size(200,200),null)
            itemView.findViewById<ImageView>(R.id.item_profile_img_grid).setImageBitmap(bitmap)
            itemView.setOnClickListener{
                onClick.invoke(image)
            }
        }
    }

}