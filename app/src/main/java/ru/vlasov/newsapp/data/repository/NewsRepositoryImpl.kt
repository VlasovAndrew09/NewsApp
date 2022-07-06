package ru.vlasov.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.vlasov.newsapp.data.mapper.NewsMapper
import ru.vlasov.newsapp.data.remote.NewsApi
import ru.vlasov.newsapp.domain.NewsArticle
import ru.vlasov.newsapp.domain.NewsRepository
import ru.vlasov.newsapp.domain.util.NewsResponse
import ru.vlasov.newsapp.domain.util.Resource
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val mapper: NewsMapper
): NewsRepository {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Flow<Resource<NewsResponse>?> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = api.getBreakingNews()
                emit(Resource.Success(mapper.mapNewsResponseDtoToNewsResponse(result)))
            } catch (e: UnknownHostException) {
                emit(Resource.Error( "Отсутствует интернет-соединение", null))
            } catch (e: Throwable) {
                emit(Resource.Error( "Упс! Что-то пошло не так", null))
            }
        }
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Flow<Resource<NewsResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveArticle(newsArticle: NewsArticle) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(newsArticle: NewsArticle) {
        TODO("Not yet implemented")
    }


}