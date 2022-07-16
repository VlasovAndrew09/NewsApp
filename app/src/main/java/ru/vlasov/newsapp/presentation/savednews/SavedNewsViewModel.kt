package ru.vlasov.newsapp.presentation.savednews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import ru.vlasov.newsapp.domain.NewsArticle
import ru.vlasov.newsapp.domain.NewsRepository
import ru.vlasov.newsapp.domain.util.NewsResponse
import ru.vlasov.newsapp.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _savedNews = MutableLiveData<List<NewsArticle>>()
    val savedNews = _savedNews

    init {
        getSavedNews()
    }

    private fun getSavedNews() {
        viewModelScope.launch {
            newsRepository.getSavedNews().collect {
                _savedNews.value = it
            }
        }
    }

    fun saveArticle(newsArticle: NewsArticle) {
        viewModelScope.launch {
            newsRepository.saveArticle(newsArticle)
        }
    }

    fun deleteArticle(newsArticle: NewsArticle) {
        viewModelScope.launch {
            newsRepository.deleteArticle(newsArticle)
        }
    }
}