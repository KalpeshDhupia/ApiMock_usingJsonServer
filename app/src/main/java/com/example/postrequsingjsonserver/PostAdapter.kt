package com.example.postrequsingjsonserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class PostAdapter(private val post: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val model = post[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return post.size
    }
    class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(post: Post) {
            view.apply {

                tvId.text = post.id.toString()
                tvTitle.text = post.title
                tvAuthor.text = post.author
            }
        }
    }
}