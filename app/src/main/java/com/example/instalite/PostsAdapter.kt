package com.example.instalite

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instalite.databinding.ItemPostBinding
import com.example.instalite.models.Post

class PostsAdapter(val context: Context, val posts:List<Post>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(layoutInflater,parent,false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostsViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvUsername.text = post.user?.username
            binding.tvDescription.text = post.description
            Glide.with(context).load(post.imageUrl).into(binding.ivPost)
            binding.tvRelativeTime.text = DateUtils.getRelativeTimeSpanString(post.currentTimeMs)

        }
    }

}