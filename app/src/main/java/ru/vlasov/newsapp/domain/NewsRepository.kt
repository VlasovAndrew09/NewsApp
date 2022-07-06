package ru.vlasov.newsapp.domain

import kotlinx.coroutines.flow.Flow
import ru.vlasov.newsapp.domain.util.NewsResponse
import ru.vlasov.newsapp.domain.util.Resource

interface NewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Flow<Resource<NewsResponse>?>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Flow<Resource<NewsResponse>>

    suspend fun saveArticle(newsArticle: NewsArticle)

    fun getSavedNews(): Flow<NewsResponse>

    suspend fun deleteArticle(newsArticle: NewsArticle)
}