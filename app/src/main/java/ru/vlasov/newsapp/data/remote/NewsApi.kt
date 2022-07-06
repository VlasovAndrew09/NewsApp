package ru.vlasov.newsapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.vlasov.newsapp.BuildConfig
import ru.vlasov.newsapp.data.remote.model.NewsResponseDto

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): NewsResponseDto

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): NewsResponseDto
}