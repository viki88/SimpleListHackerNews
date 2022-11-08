package com.vikination.simplehackernewslist.network

import com.vikination.simplehackernewslist.models.NewsDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v0/topstories.json")
    suspend fun getTopStories() :List<Int>

    @GET("/v0/item/{id}.json")
    suspend fun getDetailStories(
        @Path("id") id:Int
    ) :NewsDetail
}