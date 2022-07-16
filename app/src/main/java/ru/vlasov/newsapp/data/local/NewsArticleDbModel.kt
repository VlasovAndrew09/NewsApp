package ru.vlasov.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles")
data class NewsArticleDbModel(
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val source: String?,
    val title: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?
)
