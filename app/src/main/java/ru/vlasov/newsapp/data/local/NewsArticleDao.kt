package ru.vlasov.newsapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Query("SELECT * FROM news_articles")
    fun getSavedNews(): Flow<List<NewsArticleDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: NewsArticleDbModel)

    @Delete
    suspend fun deleteArticle(article: NewsArticleDbModel)
}