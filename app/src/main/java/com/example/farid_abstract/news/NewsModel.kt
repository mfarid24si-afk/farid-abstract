package com.example.farid_abstract.news

import com.google.gson.annotations.SerializedName

// Root response dari NewsAPI
data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)

data class Article(
    @SerializedName("source") val source: Source?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("url") val url: String?
)

data class Source(
    @SerializedName("name") val name: String?
)