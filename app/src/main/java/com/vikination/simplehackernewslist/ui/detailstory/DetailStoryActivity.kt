package com.vikination.simplehackernewslist.ui.detailstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vikination.simplehackernewslist.databinding.ActivityDetailStoryBinding

class DetailStoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}