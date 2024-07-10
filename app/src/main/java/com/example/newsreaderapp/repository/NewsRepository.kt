package com.example.newsreaderapp.repository

import com.example.newsreaderapp.model.NewsArticle
import com.example.newsreaderapp.network.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {
    fun getTopHeadlinesFlow(country: String, apiKey: String): Flow<List<NewsArticle>> {
        return flow {
            val response = NewsApi.service.getTopHeadlines(country, apiKey)
            emit(response.articles)
        }
    }
}
