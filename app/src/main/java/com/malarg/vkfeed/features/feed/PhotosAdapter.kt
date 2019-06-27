package com.malarg.vkfeed.features.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.malarg.vkfeed.R
import com.squareup.picasso.Picasso

class PhotosAdapter(private val photos: List<FeedApi.Photo?>) : PagerAdapter() {

    private val views: ArrayList<View> = arrayListOf()

    override fun isViewFromObject(view: View, input: Any): Boolean {
        return view == input
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val v = views[position]
        view.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position])
    }

    override fun getCount(): Int = views.size

    fun buildMediaLayout(inflater: LayoutInflater, position: Int): View? {
        return buildImageBlock(inflater, position)
    }

    private fun buildImageBlock(inflater: LayoutInflater, position: Int): View? {
        val photo = photos[position]
        val mediaLayout = inflater.inflate(R.layout.item_image, null)
        val imageView = mediaLayout.findViewById<ImageView>(R.id.imageView)

        val data = photo?.photo_1280 ?: photo?.photo_807 ?: photo?.photo_604 ?: photo?.photo_130 ?: photo?.photo_75
        if (data != null && data != "") {
                Picasso.get().load(data).into(imageView)
        }
        return mediaLayout
    }

    fun addView(v: View) = addView(v, views.size)

    private fun addView(v: View, position: Int): Int {
        views.add(position, v)
        return position
    }
}