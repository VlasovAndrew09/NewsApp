package ru.vlasov.newsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val newsArticles: List<NewsArticleDto>?
)
