package com.codebaron.mvvmpattern.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codebaron.mvvmpattern.R
import com.codebaron.mvvmpattern.model.PostDataItem
import org.w3c.dom.Text

class PostAdapter(private val context: Context, private val postDataItem: List<PostDataItem>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_items, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val posts = postDataItem[position]
        holder.title.text = posts.title
        holder.body.text = posts.body
    }

    override fun getItemCount(): Int {
        return postDataItem.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)
    }
}