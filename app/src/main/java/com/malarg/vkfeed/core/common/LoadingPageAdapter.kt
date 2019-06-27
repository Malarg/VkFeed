package com.malarg.vkfeed.core.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malarg.vkfeed.R

abstract class LoadingPageAdapter<T : Any>(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val inflater = LayoutInflater.from(context);

    val TYPE_PROGRESS = 0

    private var isReachEnd: Boolean = false

    protected abstract fun getCustomItemViewType(position: Int): Int

    var dataList = mutableListOf<Item<T>>()

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(itemList: List<T>) {
        addItems(itemList, false)
    }

    fun addItems(itemList: List<T>, isMore: Boolean) {
        if (itemCount > 0 && isMore) {
            removeLoading()
        }
        val currentSize = dataList.size
        if (itemList.isNotEmpty()) {
            dataList.addAll(itemList.map { it -> Item(it) })
            if (isMore) {
                // loader item
                dataList.add(Item(null))
            }
            notifyItemRangeInserted(currentSize, itemList.size)
        }
    }

    fun setItems(itemList: List<T>) {
        if (itemList.isNotEmpty()) {
            dataList.addAll(itemList.map { it -> Item(it) })
            notifyDataSetChanged()
        }
    }

    fun getItem(position: Int): T? {
        return dataList[position].item
    }

    fun getItemInternal(position: Int): T? {
        return dataList[position].item
    }

    fun getItemPosition(item: T): Int {
        return dataList.indexOfFirst { it.item == item }
    }

    private fun removeLoading() {
        if (dataList[bottomItemPosition()].item == null) {
            dataList.removeAt(bottomItemPosition())
            notifyItemRemoved(dataList.size)
        }
    }

    fun getData(): List<T> {
        return dataList.asSequence().mapNotNull { item -> item.item }.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_PROGRESS -> {
                val view = inflater.inflate(R.layout.item_bottom, parent, false)
                return LoadingViewHolder(view)
            }
        }
        throw RuntimeException("LoadingPageAdapter: ViewHolder = null")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadingViewHolder) {
            if (isReachEnd) {
                holder.progressBar.visibility = View.GONE
            } else {
                holder.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val internal = dataList[position]
        return if (internal.item == null)
            TYPE_PROGRESS
        else getCustomItemViewType(position)
    }

    private fun bottomItemPosition(): Int {
        return itemCount - 1
    }

    fun onReachEnd() {
        isReachEnd = true
        notifyItemChanged(bottomItemPosition())
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<View>(R.id.progress)
    }

    class Item<T : Any>(val item: T?)
}