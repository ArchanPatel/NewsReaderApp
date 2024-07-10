package com.example.newsreaderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreaderapp.model.NewsArticle
import com.example.newsreaderapp.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsArticles = MutableStateFlow<List<NewsArticle>>(emptyList())
    val newsArticles: StateFlow<List<NewsArticle>> = _newsArticles

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getTopHeadlinesFlow(country, apiKey)
                .catch { e ->
                    e.printStackTrace()
                    _newsArticles.value = emptyList()
                }
                .collect { articles ->
                    _newsArticles.value = articles
                }
            _isLoading.value = false
        }
    }
}