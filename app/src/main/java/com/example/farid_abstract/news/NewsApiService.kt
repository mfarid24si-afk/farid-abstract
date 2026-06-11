package com.example.farid_abstract.news

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything")
    suspend fun getDesaNews(
        @Query("q") query: String = "desa indonesia pemberdayaan",
        @Query("language") language: String = "id",
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "40fcf81831cc4cc99cb9627b5828d791"
    ): Response<NewsResponse>
}