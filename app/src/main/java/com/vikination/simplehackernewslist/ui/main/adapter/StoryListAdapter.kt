package com.vikination.simplehackernewslist.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vikination.simplehackernewslist.R
import com.vikination.simplehackernewslist.databinding.ItemStoryBinding
import com.vikination.simplehackernewslist.models.NewsDetail
import com.vikination.simplehackernewslist.ui.main.StoryItemClickListener

class StoryListAdapter(var storyItemClickListener: StoryItemClickListener) : ListAdapter<NewsDetail, StoryListAdapter.StoryListViewHolder>(DIFF_CALLBACK) {

    inner class StoryListViewHolder(private val itemBinding :ItemStoryBinding) :RecyclerView.ViewHolder(itemBinding.root){
        fun bind(newsDetail: NewsDetail){
            itemBinding.tvTitle.text = newsDetail.title
            itemBinding.tvBy.text = String.format(itemBinding.root.context.resources.getString(R.string.by_template), newsDetail.by)
            itemBinding.root.setOnClickListener { storyItemClickListener.onStoryItemClick(newsDetail) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryListViewHolder {
        val itemBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StoryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsDetail>(){
            override fun areItemsTheSame(oldItem: NewsDetail, newItem: NewsDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsDetail, newItem: NewsDetail): Boolean {
                return oldItem == newItem
            }

        }
    }
}