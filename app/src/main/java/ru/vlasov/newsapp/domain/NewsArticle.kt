package ru.vlasov.newsapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticle(
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val source: String?,
    val title: String?,
    val url: String,
    val urlToImage: String?
) : Parcelable