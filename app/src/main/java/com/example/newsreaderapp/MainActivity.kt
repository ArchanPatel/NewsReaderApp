package com.example.newsreaderapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsreaderapp.adapter.NewsAdapter
import com.example.newsreaderapp.databinding.ActivityMainBinding
import com.example.newsreaderapp.repository.NewsRepository
import com.example.newsreaderapp.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ...
        val repository = NewsRepository() // Initialize your repository
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchTopHeadlines("us", "56aa49575cf049128641d260d01cfa09")
        }

        lifecycleScope.launch {
            viewModel.newsArticles.collect { articles ->
                adapter.apply {
                    notifyDataSetChanged()
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        // Fetch initial news
        viewModel.fetchTopHeadlines("us", "56aa49575cf049128641d260d01cfa09")
    }

    class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
