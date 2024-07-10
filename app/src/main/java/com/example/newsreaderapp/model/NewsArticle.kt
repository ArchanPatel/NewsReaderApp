package com.example.newsreaderapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.Date

@JsonClass(generateAdapter = true)
data class NewsArticle(
    val source: ArticleSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String?
)

data class ArticleSource(
    val id: String?,
    val name: String
)
