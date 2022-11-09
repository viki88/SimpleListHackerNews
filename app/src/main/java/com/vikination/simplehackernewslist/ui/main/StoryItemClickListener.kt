package com.vikination.simplehackernewslist.ui.main

import com.vikination.simplehackernewslist.models.NewsDetail

interface StoryItemClickListener {
    fun onStoryItemClick(newsDetail: NewsDetail)
}