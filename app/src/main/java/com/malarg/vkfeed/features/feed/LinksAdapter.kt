package com.malarg.vkfeed.features.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.malarg.vkfeed.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_link.view.*

class LinksAdapter : RecyclerView.Adapter<LinksAdapter.LinkViewHolder>() {

    var links: List<FeedApi.Link> = listOf()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    class LinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val textView: TextView = view.textView
        val urlTextView: TextView = view.urlTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_link, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount() = links.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val link = links[position]
        holder.textView.text = link.title
        holder.urlTextView.text = link.url
        val imageLink = link.photo.photo_604 ?: link.photo.photo_130 ?: link.photo.photo_75
        if (imageLink != null) {
            Picasso.get().load(imageLink).into(holder.imageView)
            holder.imageView.visibility = View.VISIBLE
        } else {
            holder.imageView.visibility = View.GONE
        }
    }
}