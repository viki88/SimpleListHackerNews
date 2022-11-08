package com.vikination.simplehackernewslist.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikination.simplehackernewslist.models.NewsDetail
import com.vikination.simplehackernewslist.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var mainRepository: MainRepository) :ViewModel() {

    private val topStoriesIdLiveData = MutableLiveData<List<Int>>()
    private val newsDetailsLiveData = MutableLiveData<ArrayList<NewsDetail>>()
    private val newsDetails = arrayListOf<NewsDetail>()
    private var listNewsId = listOf<Int>()
    private val loadingProgressLiveData = MutableLiveData<Boolean>()
    var currentPage = 0
    var countPerPage = 20

    fun loadAllTopStories() = viewModelScope.launch(Dispatchers.IO) {
        listNewsId = mainRepository.getTopStories()
        topStoriesIdLiveData.postValue(listNewsId)
    }

    private fun loadDetailStoryById(id :Int) = viewModelScope.launch(Dispatchers.IO){
        val newsDetail = mainRepository.getStoryById(id)
        val isNewsExist = newsDetails.contains(newsDetail)
        if (!isNewsExist) {
            newsDetails.add(newsDetail)
            loadCurrentListNews()
        }
    }

    fun loadNextListPage(){
        loadingProgressLiveData.postValue(true)
        runBlocking {
            val startId = (currentPage*countPerPage)
            val endId = startId + countPerPage
            for (id in startId until endId){
                loadDetailStoryById(listNewsId[id])
            }
            currentPage++
        }
    }

    fun allDataLoadedCount() = countPerPage * currentPage

    fun loadCurrentListNews(){
        newsDetailsLiveData.postValue(newsDetails)
        if (allDataLoadedCount() == newsDetails.size) loadingProgressLiveData.postValue(false)
    }

    fun getListIdSize() = listNewsId.size

    fun observeTopStoriesLiveData() = topStoriesIdLiveData
    fun observeNewsDetailsLiveData() = newsDetailsLiveData
    fun observeLoadingProgressLiveData() = loadingProgressLiveData

}