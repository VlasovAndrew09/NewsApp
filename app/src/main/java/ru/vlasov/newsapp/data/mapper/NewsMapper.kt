package ru.vlasov.newsapp.data.mapper

import ru.vlasov.newsapp.data.remote.model.NewsArticleDto
import ru.vlasov.newsapp.data.remote.model.NewsResponseDto
import ru.vlasov.newsapp.domain.NewsArticle
import ru.vlasov.newsapp.domain.util.NewsResponse

import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapArticleDtoToArticleEntity(newsArticleDto: NewsArticleDto) = NewsArticle(
        author = newsArticleDto.author,
        content = newsArticleDto.content,
        description = newsArticleDto.description,
        publishedAt = newsArticleDto.publishedAt,
        source = newsArticleDto.source?.name,
        title = newsArticleDto.title,
        url = newsArticleDto.url,
        urlToImage = newsArticleDto.urlToImage
    )

    fun mapNewsResponseDtoToNewsResponse(newsResponseDto: NewsResponseDto): NewsResponse {
        return newsResponseDto.newsArticles?.map {
            mapArticleDtoToArticleEntity(it)
        }?.let { NewsResponse(it) } ?: NewsResponse(listOf())
    }
}