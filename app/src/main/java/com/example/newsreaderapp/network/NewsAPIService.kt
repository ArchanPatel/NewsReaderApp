package com.example.newsreaderapp.network

import com.example.newsreaderapp.model.NewsArticle
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

object NewsApi {
    private const val BASE_URL = "https://newsapi.org/"

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}

data class NewsResponse(
    @Json(name = "articles") val articles: List<NewsArticle>
)
