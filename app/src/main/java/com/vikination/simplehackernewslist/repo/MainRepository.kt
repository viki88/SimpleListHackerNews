package com.vikination.simplehackernewslist.repo

import com.vikination.simplehackernewslist.models.NewsDetail

interface MainRepository {
    suspend fun getTopStories() :List<Int>
    suspend fun getStoryById(id :Int) :NewsDetail
}