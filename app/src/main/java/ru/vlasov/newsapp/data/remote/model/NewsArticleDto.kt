package ru.vlasov.newsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewsArticleDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: SourceDto?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)
