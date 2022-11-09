package com.vikination.simplehackernewslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikination.simplehackernewslist.databinding.ActivityMainBinding
import com.vikination.simplehackernewslist.models.NewsDetail
import com.vikination.simplehackernewslist.ui.detailstory.DetailStoryActivity
import com.vikination.simplehackernewslist.ui.main.adapter.StoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoryItemClickListener {

    private lateinit var binding :ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: StoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        initObservation()

        mainViewModel.loadAllTopStories()
        mainViewModel.loadCurrentListNews()

        binding.buttonLoadMore.setOnClickListener {
            mainViewModel.loadNextListPage()
        }
    }

    private fun initObservation(){
        mainViewModel.observeLoadingProgressLiveData().observe(this){
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.observeNewsDetailsLiveData().observe(this){
            if (mainViewModel.allDataLoadedCount() == it.size) adapter.submitList(it.toList())
            binding.buttonLoadMore.visibility = if (mainViewModel.getListIdSize() == it.size) View.GONE else View.VISIBLE
        }

        mainViewModel.observeTopStoriesLiveData().observe(this){
            mainViewModel.loadNextListPage()
        }
    }

    private fun setupList(){
        adapter = StoryListAdapter(this)
        binding.rvStoryList.adapter = adapter
        binding.rvStoryList.layoutManager = LinearLayoutManager(this)
    }

    override fun onStoryItemClick(newsDetail: NewsDetail) {
        val intent = Intent(this, DetailStoryActivity::class.java)
        intent.putExtra(DetailStoryActivity.DETAIL_STORY_KEY, newsDetail)
        startActivity(intent)
    }
}