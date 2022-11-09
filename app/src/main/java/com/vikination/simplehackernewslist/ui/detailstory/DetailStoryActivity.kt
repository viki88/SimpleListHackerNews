package com.vikination.simplehackernewslist.ui.detailstory

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vikination.simplehackernewslist.databinding.ActivityDetailStoryBinding
import com.vikination.simplehackernewslist.models.NewsDetail

class DetailStoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailStoryBinding
    private var newsDetail :NewsDetail? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.settings.javaScriptEnabled = true

        newsDetail = intent.getParcelableExtra(DETAIL_STORY_KEY)
        Log.i("TAG", "onCreate: $newsDetail")

        newsDetail?.let {
            binding.webview.loadUrl(it.url)
        }

    }

    companion object{
        const val DETAIL_STORY_KEY = "detail_key_story"
    }
}