package ru.vlasov.newsapp.data.mapper

import ru.vlasov.newsapp.data.local.NewsArticleDbModel
import ru.vlasov.newsapp.data.remote.model.NewsArticleDto
import ru.vlasov.newsapp.data.remote.model.NewsResponseDto
import ru.vlasov.newsapp.domain.NewsArticle
import ru.vlasov.newsapp.domain.util.NewsResponse

import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapNewsArticleDtoToNewsArticle(newsArticleDto: NewsArticleDto) = NewsArticle(
        author = newsArticleDto.author,
        description = newsArticleDto.description,
        publishedAt = newsArticleDto.publishedAt,
        source = newsArticleDto.source?.name,
        title = newsArticleDto.title,
        url = newsArticleDto.url,
        urlToImage = newsArticleDto.urlToImage
    )

    fun mapNewsResponseDtoToNewsResponse(newsResponseDto: NewsResponseDto): NewsResponse {
        return newsResponseDto.newsArticles?.map {
            mapNewsArticleDtoToNewsArticle(it)
        }?.let { NewsResponse(it) } ?: NewsResponse(listOf())
    }

    fun mapNewsArticleToNewsArticleDbModel(newsArticle: NewsArticle) = NewsArticleDbModel (
        author = newsArticle.author,
        description = newsArticle.description,
        publishedAt = newsArticle.publishedAt,
        source = newsArticle.source,
        title = newsArticle.title,
        url = newsArticle.url,
        urlToImage = newsArticle.urlToImage
    )

    fun mapNewsArticleDbModelToNewsArticle(newsArticleDbModel: NewsArticleDbModel) = NewsArticle (
        author = newsArticleDbModel.author,
        description = newsArticleDbModel.description,
        publishedAt = newsArticleDbModel.publishedAt,
        source = newsArticleDbModel.source,
        title = newsArticleDbModel.title,
        url = newsArticleDbModel.url,
        urlToImage = newsArticleDbModel.urlToImage
    )

    fun mapListNewsArticleDbModelToListNewsArticle(list: List<NewsArticleDbModel>) = list.map {
        mapNewsArticleDbModelToNewsArticle(it)
    }
}