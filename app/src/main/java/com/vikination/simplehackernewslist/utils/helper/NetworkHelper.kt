package com.vikination.simplehackernewslist.utils.helper

import com.vikination.simplehackernewslist.network.ApiService
import com.vikination.simplehackernewslist.utils.helper.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService() = getRetrofit().create(ApiService::class.java)
}