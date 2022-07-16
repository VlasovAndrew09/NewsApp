package ru.vlasov.newsapp.presentation.newsarticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.vlasov.newsapp.domain.NewsArticle
import ru.vlasov.newsapp.domain.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    fun saveArticle(newsArticle: NewsArticle) {
        viewModelScope.launch {
            newsRepository.saveArticle(newsArticle)
        }
    }
}