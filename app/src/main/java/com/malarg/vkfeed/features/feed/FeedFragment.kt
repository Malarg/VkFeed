package com.malarg.vkfeed.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malarg.vkfeed.R
import com.malarg.vkfeed.core.common.EndlessRecyclerViewScrollListener
import com.malarg.vkfeed.core.platform.App
import com.malarg.vkfeed.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_feed.view.*

class FeedFragment : BaseFragment() {

    lateinit var viewModel: FeedViewModel
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    init {
        App.appComponent.inject(this)
    }

    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = getViewModel()
        viewModel.newPosts.observe(this, Observer { wall ->
            wall.response?.items?.let {
                adapter.addItems(it, true)
            }
        })
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        adapter = PostsAdapter(context ?: return view)
        adapter.setHasStableIds(true)
        view.postsRecyclerView.isNestedScrollingEnabled = false
        view.postsRecyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        view.postsRecyclerView.layoutManager = linearLayoutManager
        viewModel.loadWall()

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onScroll(view: RecyclerView?) {}

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.pageNumber = page
                viewModel.loadWall()
            }
        }

        view.postsRecyclerView.addOnScrollListener(scrollListener)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FeedFragment().apply {}
    }
}
