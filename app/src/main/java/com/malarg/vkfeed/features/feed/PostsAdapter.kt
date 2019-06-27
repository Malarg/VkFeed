package com.malarg.vkfeed.features.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.malarg.vkfeed.R
import com.malarg.vkfeed.core.common.LoadingPageAdapter
import com.malarg.vkfeed.core.utils.DateUtil
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostsAdapter(val context: Context) : LoadingPageAdapter<FeedApi.Post>(context) {

    companion object {
        private const val POST_TYPE = 1
    }

    class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val postDate: TextView = view.postDate
        val likesCount: TextView = view.likesCountTV
        val commentsCount: TextView = view.commentsCountTV
        val mainTextTextVew: TextView = view.mainTextTextView
        val sliderIndicator: CirclePageIndicator = view.indicator
        val blocksContainer: View = view.blocksContainer
        val linksRecyclerView: RecyclerView = view.linksRecyclerView
        val blocks: ViewPager = view.blocks
        lateinit var photosAdapter: PhotosAdapter
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?: 0L
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == POST_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            return PostViewHolder(view)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getCustomItemViewType(position: Int): Int {
        return POST_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItemInternal(position)
        if (holder is PostViewHolder && post != null)
            onBindPostViewHolder(post, holder)
    }

    private fun onBindPostViewHolder(post: FeedApi.Post, holder: PostViewHolder) {
        holder.mainTextTextVew.text = post.text
        holder.likesCount.text = post.likes.count.toString()
        holder.commentsCount.text = post.comments.count.toString()
        holder.postDate.text = parseDate(post.date)

        buildPhotosBlock(post, holder)
        buildLinks(post, holder)
    }

    private fun buildPhotosBlock(
        post: FeedApi.Post,
        holder: PostViewHolder
    ) {
        val photosBlock = post.attachments?.filter { it.photo != null }?.map { it.photo } ?: listOf()

        holder.blocksContainer.visibility = if (photosBlock.isNotEmpty()) View.VISIBLE else View.GONE
        holder.sliderIndicator.visibility =
            if (photosBlock.isNotEmpty() && photosBlock.size >= 2) View.VISIBLE else View.GONE

        if (photosBlock.isEmpty()) return

        val photosAdapter = PhotosAdapter(photosBlock)
        holder.photosAdapter = photosAdapter
        for (media in photosBlock.indices) {
            val mediaLayout = holder.photosAdapter.buildMediaLayout(inflater, media)
            if (mediaLayout != null) {
                holder.photosAdapter.addView(mediaLayout)
            }
        }
        holder.blocks.adapter = photosAdapter
        holder.sliderIndicator.setViewPager(holder.blocks)
        holder.photosAdapter.notifyDataSetChanged()

    }

    private fun buildLinks(
        post: FeedApi.Post,
        holder: PostViewHolder
    ) {
        val links = post.attachments?.filter { it.link != null }?.map { it.link } ?: listOf()
        if (links.isNotEmpty()) {
            holder.linksRecyclerView.visibility = View.VISIBLE
            holder.linksRecyclerView.layoutManager = LinearLayoutManager(context)
            val linksAdapter = LinksAdapter()
            linksAdapter.links = links as List<FeedApi.Link>
            holder.linksRecyclerView.adapter = linksAdapter
        } else {
            holder.linksRecyclerView.visibility = View.GONE
        }
    }

    private fun parseDate(longDate: Long): String {
        val yesterdayTime = buildDate(-1)
        val todayTime = buildDate()

        val tempCal = Calendar.getInstance()
        tempCal.time = Date(longDate * 1000)
        val currentDate = tempCal.time

        if (currentDate.after(yesterdayTime) && currentDate.before(todayTime)) {
            val timeStr = DateUtil().parseDate("HH:mm", longDate)
            return context.getString(R.string.yesterday_in, timeStr)
        }

        if (currentDate.after(todayTime)) {
            val timeStr = DateUtil().parseDate("HH:mm", longDate)
            return context.getString(R.string.today_in, timeStr)
        }

        return DateUtil().parseDate("d MMMM в HH:mm", longDate)
    }

    private fun buildDate(dayOffset: Int = 0): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, dayOffset)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }
}