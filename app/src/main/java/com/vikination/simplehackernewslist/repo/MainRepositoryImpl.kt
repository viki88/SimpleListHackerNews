package com.vikination.simplehackernewslist.repo

import com.vikination.simplehackernewslist.models.NewsDetail
import com.vikination.simplehackernewslist.network.ApiService

class MainRepositoryImpl (var apiService: ApiService) :MainRepository {

    override suspend fun getTopStories(): List<Int> = apiService.getTopStories()
    override suspend fun getStoryById(id: Int): NewsDetail = apiService.getDetailStories(id)
}