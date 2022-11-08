package com.vikination.simplehackernewslist.di

import com.vikination.simplehackernewslist.network.ApiService
import com.vikination.simplehackernewslist.repo.MainRepository
import com.vikination.simplehackernewslist.repo.MainRepositoryImpl
import com.vikination.simplehackernewslist.utils.helper.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService() :ApiService = NetworkHelper.getApiService()

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService) :MainRepository = MainRepositoryImpl(apiService)
}